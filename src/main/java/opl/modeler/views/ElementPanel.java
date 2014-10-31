package opl.modeler.views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import opl.modeler.panels.UMLContentPanel;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

/**
 * Draw an uml component
 *
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 * @param <T>
 */
public abstract class ElementPanel<T extends CtType<?>> extends JPanel {

	private static final long serialVersionUID = 3864276811483965605L;
	protected static final int HEIGHT_BEFORE_ELEMENT_NAME = 40;

	/**
	 * The element to draw
	 */
	protected T ctElement;

	/**
	 * True if the element is selected by user in uml diagram
	 */
	protected boolean selected;

	/**
	 * The current width of the component
	 */
	protected int width;

	/**
	 * The current height of the component
	 */
	protected int height;

	public ElementPanel(T ctElement) {
		super();
		this.ctElement = ctElement;
	}

	/**
	 * Draw a rectangle around the component
	 *
	 * @param g
	 *            GUI Graphics
	 */
	protected void drawRectangle(Graphics g) {
		if(selected) {
			g.setColor(Color.BLUE);
		}
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		if(selected) {
			g.setColor(Color.BLACK);
		}
	}

	/**
	 * Draw the name of the component, followed by a line
	 *
	 * @param g
	 *            GUI Graphics
	 */
	protected void drawName(Graphics g) {
		String className = ctElement.getSimpleName();
		g.drawString(className, 0, HEIGHT_BEFORE_ELEMENT_NAME);
		g.drawLine(0, HEIGHT_BEFORE_ELEMENT_NAME + 20, getWidth() - 1,
				HEIGHT_BEFORE_ELEMENT_NAME + 20);
		width = g.getFontMetrics().stringWidth(className);
		height = HEIGHT_BEFORE_ELEMENT_NAME + 30;
	}

	/**
	 * Draw each field of the component, followed by a line
	 *
	 * @param g
	 *            GUI Graphics
	 */
	protected void drawFields(Graphics g) {
		// draw each field
		String fieldStr;
		for (CtField<?> field : ctElement.getFields()) {
			fieldStr = field.getSimpleName() + " : "
					+ field.getType().getSimpleName();
			width = Math.max(width, g.getFontMetrics().stringWidth(fieldStr));
			g.drawString(fieldStr, 0, height);
			height += 20;
		}

		// Draw line to separates fields and methods
		g.drawLine(0, height, getWidth() - 1, height);
		height += 10;
	}

	/**
	 * Draw each method of the component
	 *
	 * @param g
	 *            GUI Graphics
	 */
	protected void drawMethods(Graphics g) {
		// draw each method
		String methodStr;
		for (CtMethod<?> method : ctElement.getMethods()) {
			methodStr = method.getSignature();
			width = Math.max(width, g.getFontMetrics().stringWidth(methodStr));
			g.drawString(methodStr, 0, height);
			height += 20;
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public T getCtElement() {
		return ctElement;
	}

	public void setCtElement(CtType<?> ctElement) {
		this.ctElement = (T) ctElement;
	}
	
	/**
	 * Draw the ElementPanel in the UMLContentPanel.
	 * @param umlContentPanel panel which shows the elements of this
	 */
	public abstract void accept(UMLContentPanel umlContentPanel);
}
