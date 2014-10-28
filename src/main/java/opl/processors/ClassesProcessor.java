package opl.processors;

import spoon.reflect.declaration.CtClass;

/**
 * use Spoon library to find all classes of the project<br>
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ClassesProcessor extends DisplayProcessor<CtClass<?>> {
	public void process(CtClass<?> element) {
		elements.add(element);
	}
}
