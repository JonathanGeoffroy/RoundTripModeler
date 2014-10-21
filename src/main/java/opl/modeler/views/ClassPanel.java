package opl.modeler.views;

import spoon.reflect.declaration.CtClass;

/**
 * Draw a class
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ClassPanel extends ElementPanel<CtClass<?>> {

	public ClassPanel(CtClass<?> ctClass) {
		super(ctClass);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2521875871223935514L;

}
