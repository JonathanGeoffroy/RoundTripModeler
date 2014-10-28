package opl.modeler.model;

import java.util.List;
import java.util.Observable;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.factory.EnumFactory;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.InterfaceFactory;

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

	private Factory factory;

	public Uml(Factory factory, List<CtClass<?>> classes, List<CtInterface<?>> interfaces,
			List<CtEnum<?>> enumerations) {
		this.factory = factory;
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

	public void addClass(String qualifiedName) {
		CtClass<?> createdClass = factory.Class().create(qualifiedName);
		classes.add(createdClass);
		setChanged(); 
		notifyObservers(createdClass);
	}

	public void addInterface(String qualifiedName) {
		CtInterface<?> createdInterface = new InterfaceFactory(factory).create(qualifiedName);
		interfaces.add(createdInterface);
		System.out.println("contains interface ? " + factory.Class().getAll().contains(createdInterface));
		setChanged(); 
		notifyObservers(createdInterface);
		
	}

	public void addEnumeration(String qualifiedName) {
		CtEnum<?> createdEnumeration = new EnumFactory(factory).create(qualifiedName);
		enumerations.add(createdEnumeration);
		System.out.println("contains enumeration ? " + factory.Class().getAll().contains(createdEnumeration));
		setChanged(); 
		notifyObservers(createdEnumeration);		
	}
}
