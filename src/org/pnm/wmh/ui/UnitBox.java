package org.pnm.wmh.ui;

import java.awt.Dimension;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;

import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.event.SelectionEvent;
import org.pnm.wmh.event.SelectionListener;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.Weapon;
import org.pnm.wmh.model.WeaponMod;


@SuppressWarnings("serial")
public class UnitBox extends JEditorPane implements SelectionListener{
	private static final String STAT_LINE = "<tr style='color:blue'><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td></tr>";

	Unit u;

	public UnitBox() {
		setContentType("text/html");
		setSelectedUnit(null);
		EventManager.registerSelectionListener(this);
	}

	public void setSelectedUnit(Unit u) {
		this.u = u;
		setText(getHtml());
		setPreferredSize(new Dimension(300,300));
		setBorder(BorderFactory.createEtchedBorder());
	}

	public String getHtml() {
		if(u == null){
			return "";
		}
		StringBuilder sb = new StringBuilder("<html><body style='color:blue'>");
		sb.append("<h2>"+u.name+"</h2>");
		sb.append("<table><tr><td>SPD</td><td>MAT</td><td>RAT</td><td>DEF</td><td>ARM</td></tr>");
		if (u != null) {
			sb.append(MessageFormat.format(STAT_LINE, u.spd, u.mat, u.rat, u.def, u.arm));
		}
		sb.append("</table>");
		sb.append("<table>");
		if (u != null) {
			for (Weapon w : u.getWeapons()) {
				sb.append(w.name);
				sb.append("<br/>");
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				if (w.isRanged()) {
					sb.append("POW: "+ w.pow);
					sb.append("&nbsp;&nbsp;&nbsp;RNG: "+ w.rng);
					sb.append("&nbsp;&nbsp;&nbsp;ROF: "+ w.rof);
				} else {
					sb.append("P+S: "+ (w.pow + u.str));
				}
				if(w.getWeaponMods().length > 0){
					sb.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					for(WeaponMod mod : w.getWeaponMods()){
						sb.append(mod.toString().toLowerCase());
						sb.append("&NBSP;");
					}
					sb.append("<br/>");
				}
				sb.append("<br/>");
			}
		}
		sb.append("</body></html>");
		return sb.toString();
	}

	@Override
	public void handle(SelectionEvent e) {
		setSelectedUnit(e.u);
	}

}
