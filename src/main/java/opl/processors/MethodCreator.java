package opl.processors;

import spoon.Launcher;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.declaration.CtMethodImpl;
import spoon.support.reflect.reference.CtTypeReferenceImpl;

public class MethodCreator {

	public static void addMethod(Launcher spoon, String name, String returnType, CtType<?> parent)
			throws ClassNotFoundException {
		CtTypeReference typeReference;
		Factory factory = spoon.getFactory();

		if(returnType.equals("void")) {
			typeReference = new CtTypeReferenceImpl();
			typeReference.setSimpleName(CtTypeReference.NULL_TYPE_NAME);
		} else {
			// Try to find the class into project
			CtType<?> ctType = factory.Class().get(returnType);

			if(ctType != null) {
				typeReference = ctType.getReference();
			}
			else { // if the searched class isn't into the project, try to find it into classloader
				ClassLoader loader = factory.getClass().getClassLoader();
				Class<?> c = loader.loadClass(returnType);
				typeReference = factory.Class().createReference(c);
			}
		}

		CtMethod<?> method = new CtMethodImpl<Object>();
		method.setSimpleName(name);
		method.setType(typeReference);
		method.setParent(parent);
		method.setVisibility(ModifierKind.PUBLIC);
		factory.Method().create(parent, method, true);
	}
}
