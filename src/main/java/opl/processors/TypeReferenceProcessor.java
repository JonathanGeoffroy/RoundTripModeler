package opl.processors;

import spoon.processing.AbstractManualProcessor;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.reference.CtTypeReferenceImpl;

/**
 * Find the reference of a Type<br>
 * First search into analyzed project. If it doesn't exists, search into
 * ClassLoader<br>
 * Throw new RuntimeException if the reference can't be found
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class TypeReferenceProcessor extends AbstractManualProcessor {
	protected String name;
	protected String type;

	public TypeReferenceProcessor(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Find the reference of a type First search into analyzed project. If it
	 * doesn't exists, search into ClassLoader<br>
	 * Throw new RuntimeException if the reference can't be found
	 * 
	 * @param type
	 *            the type to find
	 * @return the CtTypeReference of <code>type</code>
	 */
	protected CtTypeReference getTypeReference(String type) {
		CtTypeReference typeReference;
		Factory factory = getFactory();

		if(type.equals("void")) {
			typeReference = new CtTypeReferenceImpl();
			typeReference.setSimpleName(CtTypeReference.NULL_TYPE_NAME);
		} else {
			// Try to find the class into project
			CtType<?> ctType = factory.Class().get(type);

			if(ctType != null) {
				typeReference = ctType.getReference();
			}
			else { // if the searched class isn't into the project, try to find it into classloader
				ClassLoader loader = factory.getClass().getClassLoader();
				Class<?> c;
				try {
					c = loader.loadClass(type);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Type not found in ClassLoader");
				}
				typeReference = factory.Class().createReference(c);
			}
		}
		return typeReference;
	}
}
