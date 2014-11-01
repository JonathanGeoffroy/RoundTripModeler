package opl.processors.writers;

import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
/**
 * Processor which refactor a method by changing its name & type
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class MethodRefactorer extends TypeReferenceProcessor {
	private CtMethod<?> method;
	public MethodRefactorer(CtMethod<?> method, String type, String name) {
		super(name, type);
		this.method = method;
	}
	
	public void process() {
		CtTypeReference typeReference = getTypeReference(type);

		method.setSimpleName(name);
		method.setType(typeReference);
	}
}
