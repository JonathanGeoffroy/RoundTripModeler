package opl.observer;

import opl.modeler.views.ElementPanel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;

/**
 * A special implementation of <strong>Observer</strong> able to receive when:
 * <ul>
 * <li>a new class is created</li>
 * <li>a new interface is created</li>
 * <li>a new enumeration is created</li>
 * <li>the source code is reloaded</li>
 * </ul>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public interface Observer {
	/**
	 * Called by Observable when a class is added
	 * 
	 * @param ctClass
	 *            the added class
	 */
	public void onClassAdded(CtClass<?> ctClass);

	/**
	 * Called by Observable when a interface is added
	 * 
	 * @param ctInterface
	 *            the added interface
	 */
	public void onInterfaceAdded(CtInterface<?> ctInterface);

	/**
	 * Called by Observable when a enumeration is added
	 * 
	 * @param ctEnum
	 *            the added enumeration
	 */
	public void onEnumAdded(CtEnum<?> ctEnum);

	/**
	 * Called by Observable when a the source code is reloaded
	 */
	public void onCodeReloaded();

	/**
	 * Callback when an element is removed from AST
	 * 
	 * @param removed
	 *            the removed graphical element
	 */
	public void onElementRemoved(ElementPanel<?> removed);
}
