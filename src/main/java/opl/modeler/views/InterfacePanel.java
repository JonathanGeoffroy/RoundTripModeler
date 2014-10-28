package opl.modeler.views;

import java.awt.Font;
import java.awt.Graphics;

import spoon.reflect.declaration.CtInterface;

/**
 * Draw an Interface
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class InterfacePanel extends ElementPanel<CtInterface<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8459706834704756568L;

	public InterfacePanel(CtInterface<?> ctElement) {
		super(ctElement);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawRectangle(g);
		drawName(g);
		drawFields(g);
		drawMethods(g);

		this.setSize(width, height);
	}

	@Override
	protected void drawName(Graphics g) {
		String className = ctElement.getSimpleName();
		Font previousFont = g.getFont();
		Font italicFont = previousFont.deriveFont(Font.ITALIC,
				previousFont.getSize());
		g.setFont(italicFont);
		g.drawString(className, 0, HEIGHT_BEFORE_ELEMENT_NAME);
		g.setFont(previousFont);
		g.drawLine(0, HEIGHT_BEFORE_ELEMENT_NAME + 20, getWidth() - 1,
				HEIGHT_BEFORE_ELEMENT_NAME + 20);
		width = g.getFontMetrics().stringWidth(className);
		height = HEIGHT_BEFORE_ELEMENT_NAME + 30;
	}

}
