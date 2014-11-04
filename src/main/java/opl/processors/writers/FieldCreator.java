package opl.processors.writers;

import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

/**
 * Processor which creates a field and adds it to its parent Compilation Unit
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class FieldCreator extends ComponentCreator {

	public FieldCreator(String name, String type, CtType<?> parent) {
		super(name, type, parent);
	}

	public void process() {
		CtTypeReference typeReference = getTypeReference(type);
		Factory factory = getFactory();

		factory.getEnvironment().setAutoImports(true);
		CtField<?> field = factory.Core().createField();
		field.setSimpleName(name);
		field.setType(typeReference);
		field.setVisibility(ModifierKind.PRIVATE);
		field.setParent(parent);
		parent.addField(field);
	}
}
