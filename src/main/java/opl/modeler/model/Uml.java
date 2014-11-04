package opl.modeler.model;

import java.util.ArrayList;
import java.util.List;

import opl.observer.Observable;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.ModifierKind;

/**
 * Model of a UML<br>
 * Contains:
 * <ol>
 * <ul>
 * all classes of the project,
 * </ul>
 * <ul>
 * all interfaces of the project,
 * </ul>
 * <ul>
 * all enumerations of the project
 * </ul>
 * </ol>
 * 
 * This class also extends Observable java util class in order to notify GUI
 * when classes, interfaces or enumerations changed
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class Uml extends Observable {
		
	/**
	 * All classes of the analyzed project
	 */
	private List<CtClass<?>> classes;

	/**
	 * All interfaces of the analyzed project
	 */
	private List<CtInterface<?>> interfaces;

	/**
	 * All enumerations of the analyzed project
	 */
	private List<CtEnum<?>> enumerations;

	/**
	 * The spoon Launcher containing the AST of the analyzed project
	 */
	private Launcher spoon;

	public Uml(Launcher spoon) {
		this.spoon = spoon;
		classes = new ArrayList<CtClass<?>>();
		interfaces = new ArrayList<CtInterface<?>>();
		enumerations = new ArrayList<CtEnum<?>>();
	}
	
	public Uml(Launcher spoon, List<CtClass<?>> classes, List<CtInterface<?>> interfaces,
			List<CtEnum<?>> enumerations) {
		this.spoon = spoon;
		this.classes = classes;
		this.interfaces = interfaces;
		this.enumerations = enumerations;
	}

	/**
	 * Create and add a class to the spoon AST
	 * 
	 * @param qualifiedName
	 *            the qualified name of the created class:
	 *            [package[.package...]].ClassName
	 * @throws Exception 
	 */
	public void addClass(CtClass<?> ctClass) {
		classes.add(ctClass);
		notifyClassAdded(ctClass);
	}

	/**
	 * Create and add an interface to the spoon AST
	 * 
	 * @param qualifiedName
	 *            the qualified name of the created interface:
	 *            [package[.package...]].InterfaceName
	 * @throws Exception 
	 */
	public void addInterface(CtInterface<?> ctInterface) throws Exception {
		interfaces.add(ctInterface);
		notifyInterfaceAdded(ctInterface);
	}

	/**
	 * Create and add an enumeration to the spoon AST
	 * 
	 * @param qualifiedName
	 *            the qualified name of the created enumeration: [package[.package...]].EnumsName
	 * @throws Exception 
	 */
	public void addEnumeration(CtEnum<?> ctEnum) throws Exception {
		enumerations.add(ctEnum);
		notifyEnumAdded(ctEnum);
	}
	
	public List<CtClass<?>> getClasses() {
		return classes;
	}

	public List<CtInterface<?>> getInterfaces() {
		return interfaces;
	}

	public List<CtEnum<?>> getEnumerations() {
		return enumerations;
	}

	public void setClasses(List<CtClass<?>> classes) {
		this.classes = classes;
	}

	public void setInterfaces(List<CtInterface<?>> interfaces) {
		this.interfaces = interfaces;
	}

	public void setEnumerations(List<CtEnum<?>> enumerations) {
		this.enumerations = enumerations;
	}
}
