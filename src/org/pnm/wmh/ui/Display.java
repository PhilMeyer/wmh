package org.pnm.wmh.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.pnm.wmh.Constants;
import org.pnm.wmh.event.ActivationEvent;
import org.pnm.wmh.event.ActivationListener;
import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.event.SelectionEvent;
import org.pnm.wmh.event.SelectionListener;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.WeaponMod;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.Game;
import org.pnm.wmh.rw.Location;
import org.pnm.wmh.ui.handlers.MouseHandler;


public class Display extends JPanel implements ActivationListener, SelectionListener{

	private static final long serialVersionUID = 6222201093159541300L;
	static Logger log = Logger.getLogger(Display.class);

	ImageCache cache = new ImageCache();
	final Game game;
	final GameFrame frame;
	
	UserState state = new UserState();

	public Display(Game game, GameFrame frame) {
		this.game = game;
		this.frame = frame;
		setPreferredSize(new Dimension(1000, 800));
		MouseHandler handler = new MouseHandler(this);
		addMouseMotionListener(handler);
		addMouseListener(handler);
		setFocusable(true);
		EventManager.registerSelectionListener(this);
		EventManager.registerActivationListener(this);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		drawBackground(g);
		drawActivated(g);
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
		for (Entry<Unit, Location> entry : game.getEnvironment().getUnitEntries().entrySet()) {
			Unit u = entry.getKey();
			if (!u.equals(state.getSelected())) {
				drawUnit(g, u, entry.getValue(), false);
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
	
	private void drawActivated(Graphics g) {
		Unit activated = game.getActive();
		if (activated != null) {
			Location loc = game.getEnvironment().getLocation(activated);
			String auraPath = activated.base.size + "mm_Aura_Yellow.png";
			Image aura = getImage(auraPath);
			int w = aura.getWidth(null);
			int drawX = loc.intX() - w / 2;
			int drawY = loc.intY() - w / 2;
			g.drawImage(aura, drawX, drawY, null);
		}
	}

	private void drawSelected(Graphics g) {
		Unit selected = state.getSelected();
		if (selected != null) {
			Location loc = game.getEnvironment().getLocation(selected);
			drawAura(g, selected, loc);
			drawReach(g, selected, loc);
			drawRange(g, selected, loc);
			drawDragged(g, selected, loc);
			drawRemainingMove(g, selected, loc);
			drawUnit(g, selected, loc, false);
		}
	}


	private void drawAura(Graphics g, Unit selected, Location loc) {
		if(selected != game.getActive()){
			String color = unitIsFriendly(selected) ? "Green" : "Red";
			String auraPath = selected.base.size + "mm_Aura_"+color+".png";
			Image aura = getImage(auraPath);
			int w = aura.getWidth(null);
			int drawX = loc.intX() - w / 2;
			int drawY = loc.intY() - w / 2;
			g.drawImage(aura, drawX, drawY, null);
		}
	}


	private void drawRemainingMove(Graphics g, Unit selected, Location loc) {
		if(unitIsFriendly(selected)){
			double remainingMove = game.getRemainingMove(selected);
			int dist = (int) Math.round(Constants.INCHES_IN_PIXELS * remainingMove + (selected.base.scaled()/2));
			g.setColor(Color.BLUE);
			drawCircle(g, loc.intX(), loc.intY(), dist);
		}
	}


	private void drawDragged(Graphics g, Unit selected, Location loc) {
		Location draggedLocation = state.getDragged();
		if (draggedLocation != null) {
			drawUnit(g, selected, draggedLocation, true);
			g.drawLine(loc.intX(), loc.intY(), draggedLocation.intX(), draggedLocation.intY());
			if(state.isIllegalMove()){
				Image no = getImage("no.png");
				int w = no.getWidth(null);
				g.drawImage(no, draggedLocation.intX() - w / 2, draggedLocation.intY() - w / 2, null);
			}
		}
	}


	private void drawRange(Graphics g, Unit selected, Location loc) {
		int maxRange = selected.getMaxRange();
		int dist = (int) Math.round(Constants.INCHES_IN_PIXELS * maxRange);
		drawCircle(g, loc.intX(), loc.intY(), dist);
	}


	private void drawReach(Graphics g, Unit selected, Location loc) {
		String reachImage = selected.hasWeaponMod(WeaponMod.REACH) ? "mmReach.png" : "mmMelee.png";
		Image reach = getImage(selected.base.size + reachImage);
		int w = reach.getWidth(null);
		double rotationRequired = Math.toRadians(loc.direction);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, w / 2, w / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		// Drawing the rotated image at the required drawing locations
		int drawX = loc.intX() - w / 2;
		int drawY = loc.intY() - w / 2;
		g.drawImage(op.filter((BufferedImage) reach, null), drawX, drawY, null);
	}


	public boolean unitIsFriendly(Unit u) {
		return u != null && u.getSide() == game.getCurrentSide();
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
		return game.getEnvironment();
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


	public UserState getState() {
		return state;
	}


	public void setState(UserState state) {
		this.state = state;
	}


	public boolean activatedSelected() {
		return game.getActive() == state.getSelected();
	}


	public Game getGame() {
		return game;
	}


	@Override
	public void handle(SelectionEvent e) {
		state.setSelected(e.u);
		repaint();
	}


	@Override
	public void handle(ActivationEvent e) {
		repaint();
	}

}
