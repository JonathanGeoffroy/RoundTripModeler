package opl.processors.writers;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.visitor.ReferenceFilter;

/**
 *  Find all elements which reference a field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class MethodReferencesProcessor extends AbstractProcessor<CtClass<?>> {
	private List<CtReference> references;
	private CtMethod<?> method;

	public MethodReferencesProcessor(CtMethod<?> method) {
		this.method = method;
		references = new ArrayList<CtReference>();
	}

	public void process(CtClass<?> element) {
		if(method == null) return;
		CtExecutableReference<?> methodReference = method.getReference();
		MethodReferenceFilter filter = new MethodReferenceFilter(methodReference);

		// Check references in each constructor
		for(CtConstructor<?> constructor : element.getConstructors()) {
			if(!constructor.getReferences(filter).isEmpty()) {
				references.add(constructor.getReference());
			}
		}

		// Check references in each method
		for(CtMethod<?> method : element.getMethods()) {
			if(!method.getReferences(filter).isEmpty()) {
				references.add(method.getReference());
			}
		}
	}

	public List<CtReference> getReferences() {
		return references;
	}
}

class MethodReferenceFilter implements ReferenceFilter<CtExecutableReference> {
	private CtExecutableReference<?> methodReference;

	public MethodReferenceFilter(CtExecutableReference<?> methodReference) {
		super();
		this.methodReference = methodReference;
	}

	public boolean matches(CtExecutableReference reference) {
		return reference.equals(methodReference);
	}

	public Class<CtExecutableReference> getType() {
		return CtExecutableReference.class;
	}

}
