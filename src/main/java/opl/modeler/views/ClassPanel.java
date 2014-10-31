package opl.modeler.views;

import java.awt.Graphics;

import opl.modeler.panels.UMLContentPanel;
import spoon.reflect.declaration.CtClass;

/**
 * Draw a class
 *
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ClassPanel extends ElementPanel<CtClass<?>> {
	/**
	 *
	 */
	private static final long serialVersionUID = 2521875871223935514L;

	public ClassPanel(CtClass<?> ctClass) {
		super(ctClass);
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
	public void accept(UMLContentPanel umlContentPanel) {
		umlContentPanel.drawElementPanel(this);
	}
}
