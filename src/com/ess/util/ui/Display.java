package com.ess.util.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.ess.util.mw.Unit;
import com.ess.util.mw.WeaponMod;
import com.ess.util.mw.rw.Environment;
import com.ess.util.mw.rw.GeometryUtils;
import com.ess.util.mw.rw.Location;

public class Display extends JPanel {

	private static final long serialVersionUID = 6222201093159541300L;
	public final static double PIXELS_IN_MM = 47.0 / 40.0;
	public final static double PIXELS_IN_INCH = PIXELS_IN_MM * 25.4;
	
	ImageCache cache = new ImageCache();
	
	Environment environment;
	Logger log = Logger.getLogger(Display.class);
	Unit selected;
	UnitBox unitBox;
	boolean first = true;
	Location draggedLocation;
	

	public Display(final Environment environment, final UnitBox unitBox) {
		this.environment = environment;
		this.unitBox = unitBox;
		setPreferredSize(new Dimension(1000, 800));
		
		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println(e);
				if(selected != null){
					Location origin = environment.getLocation(selected);
					int angle = GeometryUtils.getAngle(origin, new Location(e.getX(), e.getY()));
					draggedLocation = new Location(e.getX(), e.getY(), 180-angle);
					repaint();
				}
			}
		});
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clicked(environment, e);
			}
			
			private void clicked(final Environment environment, MouseEvent e) {
				Location loc = new Location(e.getX(), e.getY());
				//log.debug("Clicked: "+loc);
				Unit u = environment.getUnitAt(loc);
				if (u != null) {
					log.debug(u);
					selected = u;
					unitBox.setSelectedUnit(selected);
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(draggedLocation != null){
					environment.place(selected, draggedLocation);
					draggedLocation = null;
					repaint();
				}
			}
			
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				switch(e.getKeyCode()){
					case 39:  turnSelectionRight(); break;
					case 37:  turnSelectionLeft(); break;
					case 38:  moveSelectedForward(); break;
					case 40:  moveSelectedBackward(); break;
				}
				repaint();
			}
		});
		setFocusable(true);
	}

	protected void moveSelectedBackward() {
		if(selected != null){
			Location location = environment.getLocation(selected);
			Location newLocation = GeometryUtils.calculateNewLocation(new Location(location.x, location.y, location.direction-180), 8);
			environment.place(selected, newLocation);
		}
	}

	protected void moveSelectedForward() {
		if(selected != null){
			Location location = environment.getLocation(selected);
			Location newLocation = GeometryUtils.calculateNewLocation(location, 8);
			environment.place(selected, newLocation);
		}
	}

	protected void turnSelectionRight() {
		if(selected != null){
			Location location = environment.getLocation(selected);
			environment.place(selected, new Location(location.x, location.y, location.direction+15));
		}
	}

	protected void turnSelectionLeft() {
		if(selected != null){
			Location location = environment.getLocation(selected);
			environment.place(selected, new Location(location.x, location.y, location.direction-15));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		//painBackground
		if(first){
			drawBackground(g);
			//first = false;
		}
		drawSelected(g);
		drawUnits(g);
	}

	private void drawBackground(Graphics g) {
		Image image = getImage("Map_Desert_Waste.jpg");
		int iw = image.getWidth(this);
        int ih = image.getHeight(this);
        if (iw > 0 && ih > 0) {
            for (int x = 0; x < getWidth(); x += iw) {
                for (int y = 0; y < getHeight(); y += ih) {
                    g.drawImage(image, x, y, iw, ih, this);
                }
            }
        }
	}

	private void drawUnits(Graphics g) {
		for (Entry<Unit, Location> entry : environment.getUnitEntries().entrySet()) {
			drawUnit(g, entry.getKey(), entry.getValue(), false);
		}
	}

	private void drawUnit(Graphics g, Unit u, Location l, boolean transp) {
		Image sprite = getImage(u.imagePath);
		int w = sprite.getWidth(null);
		int drawX = l.intX() - w / 2;
		int drawY = l.intY() - w / 2;
		double rotationRequired = Math.toRadians(l.direction);
		double locationX = w / 2;
		double locationY = w / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		// Drawing the rotated image at the required drawing locations
		if(transp){
			((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		}
		g.drawImage(op.filter((BufferedImage)sprite, null), drawX, drawY, null);
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}
	
	public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
		cg.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}

	private void drawSelected(Graphics g) {
		if (selected != null) {
			Location loc = environment.getLocation(selected);
			String auraPath = selected.base + "mm_Aura_Green.png";
			Image aura = getImage(auraPath);
			int w = aura.getWidth(null);
			int drawX = loc.intX() - w / 2;
			int drawY = loc.intY() - w / 2;
			String reachImage = selected.hasWeaponMod(WeaponMod.REACH) ? "mmReach.png" : "mmMelee.png";
			Image reach = getImage(selected.base + reachImage);
			double locationX = w / 2;
			double locationY = w / 2;
			double rotationRequired = Math.toRadians(loc.direction);
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			// Drawing the rotated image at the required drawing locations
			g.drawImage(op.filter((BufferedImage)reach, null), drawX, drawY, null);
			g.drawImage(aura, drawX, drawY, null);
			int maxRange = selected.getMaxRange();
			int dist = (int)Math.round(PIXELS_IN_INCH * maxRange);
			drawCircle(g, loc.intX(), loc.intY(), dist);
			
			if(draggedLocation != null){
			      
				drawUnit(g, selected, draggedLocation, true);
				g.drawLine(loc.intX(), loc.intY(), draggedLocation.intX(), draggedLocation.intY());
			}
		}
	}

	private Image getImage(String auraPath) {
		return cache.getImage(auraPath);
	}

}
