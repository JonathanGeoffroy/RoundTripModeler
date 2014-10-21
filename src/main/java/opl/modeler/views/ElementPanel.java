package opl.modeler.views;

import java.awt.Graphics;

import javax.swing.JPanel;

import spoon.reflect.declaration.CtType;

/**
 * Draw an uml component
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 * @param <T>
 */
public class ElementPanel<T extends CtType<?>> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3864276811483965605L;
	private CtType<?> ctType;

	public ElementPanel(CtType<?> ctType) {
		super();
		this.ctType = ctType;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(getX() + " - " + getY() + " : " + getWidth() + " - "
				+ getHeight());
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		String className = ctType.getSimpleName();
		g.drawChars(className.toCharArray(), 0, className.length(), 10, 10);
		g.drawLine(0, 20, getWidth() - 1, 20);
	}
}
