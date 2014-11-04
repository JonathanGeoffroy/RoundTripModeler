package opl.processors.writers;

import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

/**
 * Processor which creates a method and adds it to its parent Compilation Unit<br>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class MethodCreator extends ComponentCreator {

	public MethodCreator(String name, String type, CtType<?> parent) {
		super(name, type, parent);
	}

	public void process() {
		CtTypeReference typeReference = getTypeReference(type);
		Factory factory = getFactory();

		CtMethod<?> method = factory.Core().createMethod();
		method.setSimpleName(name);
		method.setType(typeReference);
		method.setParent(parent);
		method.setVisibility(ModifierKind.PUBLIC);
		
		if(parent instanceof CtClass<?>) {
		CtCodeSnippetStatement returnSnippet = factory.Code()
				.createCodeSnippetStatement("return null");
		CtBlock methodBody = factory.Core().createBlock();
		methodBody.addStatement(returnSnippet);
		method.setBody(methodBody);
		}
		
		parent.addMethod(method);
	}
}
