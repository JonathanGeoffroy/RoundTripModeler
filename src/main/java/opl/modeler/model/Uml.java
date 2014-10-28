package opl.modeler.model;

import java.util.List;
import java.util.Observable;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;

/**
 * Model of a UML<br>
 * Contains: * all classes of the project, * all interfaces of the project, *
 * all enumerations of the project
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

	public Uml(List<CtClass<?>> classes, List<CtInterface<?>> interfaces,
			List<CtEnum<?>> enumerations) {
		this.classes = classes;
		this.interfaces = interfaces;
		this.enumerations = enumerations;
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
}
