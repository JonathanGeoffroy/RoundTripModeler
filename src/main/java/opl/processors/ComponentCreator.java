package opl.processors;

import spoon.reflect.declaration.CtType;

public abstract class ComponentCreator extends TypeReferenceProcessor {
	protected CtType<?> parent;
	
	public ComponentCreator(String name, String type, CtType<?> parent) {
		super(name, type);
		this.parent = parent;
	}
}
