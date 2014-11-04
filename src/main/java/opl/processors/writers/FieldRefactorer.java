package opl.processors.writers;

import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;

/**
 * Processor which refactors a field by changing its name & type
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class FieldRefactorer extends TypeReferenceProcessor {
	private CtField<?> field;
	public FieldRefactorer(CtField<?> field, String type, String name) {
		super(name, type);
		this.field = field;
	}
	
	public void process() {
		CtTypeReference typeReference = getTypeReference(type);

		field.setType(typeReference);
		field.setSimpleName(name);
	}
}
