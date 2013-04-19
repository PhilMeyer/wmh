package org.pnm.wmh.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.pnm.wmh.Constants;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.WeaponMod;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.Location;
import org.pnm.wmh.ui.handlers.KeyHandler;
import org.pnm.wmh.ui.handlers.MouseHandler;


public class Display extends JPanel {

	private static final long serialVersionUID = 6222201093159541300L;
	static Logger log = Logger.getLogger(Display.class);

	ImageCache cache = new ImageCache();
	final Environment environment;
	final GameFrame frame;
	
	UserState state = new UserState();

	public Display(Environment environment, GameFrame frame) {
		this.environment = environment;
		this.frame = frame;
		setPreferredSize(new Dimension(1000, 800));
		MouseHandler handler = new MouseHandler(this);
		addMouseMotionListener(handler);
		addMouseListener(handler);
		setFocusable(true);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		drawBackground(g);
		drawUnits(g);
		drawSelected(g);
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
			if (!entry.getKey().equals(state.getSelected())) {
				drawUnit(g, entry.getKey(), entry.getValue(), false);
			}
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
		if (transp) {
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		}
		g.drawImage(op.filter((BufferedImage) sprite, null), drawX, drawY, null);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
		cg.drawOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}

	private void drawSelected(Graphics g) {
		Unit selected = state.getSelected();
		if (selected != null) {
			Location loc = environment.getLocation(selected);
			String color = friendlySelected() ? "Green" : "Red";
			String auraPath = selected.base.size + "mm_Aura_"+color+".png";
			Image aura = getImage(auraPath);
			int w = aura.getWidth(null);
			int drawX = loc.intX() - w / 2;
			int drawY = loc.intY() - w / 2;
			String reachImage = selected.hasWeaponMod(WeaponMod.REACH) ? "mmReach.png" : "mmMelee.png";
			Image reach = getImage(selected.base.size + reachImage);
			double locationX = w / 2;
			double locationY = w / 2;
			double rotationRequired = Math.toRadians(loc.direction);
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			// Drawing the rotated image at the required drawing locations
			g.drawImage(op.filter((BufferedImage) reach, null), drawX, drawY, null);
			g.drawImage(aura, drawX, drawY, null);
			int maxRange = selected.getMaxRange();
			int dist = (int) Math.round(Constants.INCHES_IN_PIXELS * maxRange);
			drawCircle(g, loc.intX(), loc.intY(), dist);
			Location draggedLocation = state.getDragged();
			if (draggedLocation != null) {
				drawUnit(g, selected, draggedLocation, true);
				g.drawLine(loc.intX(), loc.intY(), draggedLocation.intX(), draggedLocation.intY());
				if(state.isIllegalMove()){
					Image no = getImage("no.png");
					w = no.getWidth(null);
					g.drawImage(no, draggedLocation.intX() - w / 2, draggedLocation.intY() - w / 2, null);
				}
			}
			
			if(friendlySelected()){
				double remainingMove = environment.getRemainingMove(selected);
				dist = (int) Math.round(Constants.INCHES_IN_PIXELS * remainingMove + (selected.base.scaled()/2));
				g.setColor(Color.BLUE);
				drawCircle(g, loc.intX(), loc.intY(), dist);
			}

			drawUnit(g, selected, loc, false);
		}
	}


	public boolean friendlySelected() {
		Unit selected = state.getSelected();
		return selected != null && selected.getSide() == environment.getCurrentSide();
	}

	private Image getImage(String auraPath) {
		return cache.getImage(auraPath);
	}

	public Unit getSelected() {
		return state.getSelected();
	}

	public void setSelected(Unit u) {
		state.setSelected(u);
		repaint();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setDraggedLocation(Location draggedLocation) {
		state.setDragged(draggedLocation);
		repaint();
	}

	public Location getDraggedLocation() {
		return state.getDragged();
	}

	public UnitBox getUnitBox() {
		return frame.getUnitBox();
	}


	public void setIllegalMove(boolean illegal) {
		state.setIllegalMove(illegal);
	}


	public GameFrame getFrame() {
		return frame;
	}

}
