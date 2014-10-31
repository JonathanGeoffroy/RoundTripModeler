package opl.processors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.visitor.ReferenceFilter;

/**
 * Find all elements which reference a field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class FieldReferencesProcessor extends AbstractProcessor<CtField<?>> {
	private List<CtReference> references;

	public FieldReferencesProcessor() {
		references = new ArrayList<CtReference>();
	}
	
	public void process(CtField<?> element) {
		FieldReferenceFilter filter = new FieldReferenceFilter(element);
		CtClass<?> parent = element.getParent(CtClass.class);
		
		// Check references in each constructor
		for(CtConstructor<?> constructor : parent.getConstructors()) {
			if(!constructor.getReferences(filter).isEmpty()) {
				references.add(constructor.getReference());
			}
		}
		
		// Check references in each method
		for(CtMethod<?> method : parent.getMethods()) {
			if(!method.getReferences(filter).isEmpty()) {
				references.add(method.getReference());
			}
		}

	}

	public List<CtReference> getReferences() {
		return references;
	}
}

/**
 * A class filter which responds "true" if the reference equals the reference of
 * the field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
class FieldReferenceFilter implements ReferenceFilter<CtFieldReference> {
	private CtField<?> field;
	FieldReferenceFilter(CtField<?> field) {
		this.field = field;
	}
	
	public boolean matches(CtFieldReference reference) {
		return field.getReference().equals(reference);
	}

	public Class<CtFieldReference> getType() {
		return CtFieldReference.class;
	}

}
