package opl.processors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

/**
 * use Spoon library to find all classes of the project<br>
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ClassesProcessor extends AbstractProcessor<CtClass<?>> {
	private List<CtClass<?>> elements;

	public ClassesProcessor() {
		this.elements = new ArrayList<CtClass<?>>();
	}

	public void process(CtClass<?> element) {
		elements.add(element);
	}

	public List<CtClass<?>> getElements() {
		return elements;
	}
}
