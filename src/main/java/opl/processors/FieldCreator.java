package opl.processors;

import spoon.Launcher;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.declaration.CtFieldImpl;

public class FieldCreator {
	public static void addField(Launcher spoon, String name, String type, CtType<?> parent)
			throws ClassNotFoundException {
		CtTypeReference typeReference;
		Factory factory = spoon.getFactory();
		
		// Try to find the class into project
		CtType<?> ctType = factory.Class().get(type);
		
		if(ctType != null) {
			typeReference = ctType.getReference();
		}
		else { // if the searched class isn't into the project, try to find it into classloader
			ClassLoader loader = factory.getClass().getClassLoader();
			Class<?> c = loader.loadClass(type);
			typeReference = factory.Class().createReference(c);
		}
		
		CtField<?> field = new CtFieldImpl<Object>();
		field.setSimpleName(name);
		field.setType(typeReference);
		field.setParent(parent);
		field.setVisibility(ModifierKind.PRIVATE);
		factory.Field().create(parent, field);
	}
}
