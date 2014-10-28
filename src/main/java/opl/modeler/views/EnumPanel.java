package opl.modeler.views;

import java.awt.Graphics;

import spoon.reflect.declaration.CtEnum;

/**
 * Draw an Enumeration
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class EnumPanel extends ClassPanel {

	private static final String ENUMERATION = "<<Enumeration>>";
	/**
	 * 
	 */
	private static final long serialVersionUID = 8839333492404879799L;

	public EnumPanel(CtEnum<?> ctElement) {
		super(ctElement);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(ENUMERATION, 0, 10);
	}
}
