package opl.processors;

import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
/**
 * Processor which refactor a field by changing its name & type
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
		CtTypeReference typeReference;
		Factory factory = getFactory();		

		// Try to find the class into project
		CtType<?> ctType = factory.Class().get(type);

		if(ctType != null) {
			typeReference = ctType.getReference();
		}
		else { // if the searched class isn't into the project, try to find it into classloader
			try {
				ClassLoader loader = factory.getClass().getClassLoader();
				Class<?> c;

				c = loader.loadClass(type);
				typeReference = factory.Class().createReference(c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}
		}

		field.setType(typeReference);
		field.setSimpleName(name);
	}
}
