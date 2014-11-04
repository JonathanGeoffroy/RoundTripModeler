package opl.processors.writers;

import spoon.reflect.declaration.CtType;

/**
 * Processor which creates an element and add it to its parent
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class ComponentCreator extends TypeReferenceProcessor {
	protected CtType<?> parent;
	
	public ComponentCreator(String name, String type, CtType<?> parent) {
		super(name, type);
		this.parent = parent;
	}
}
