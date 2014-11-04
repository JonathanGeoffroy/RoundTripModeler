package opl.processors.readers;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtType;

/**
 * Use Spoon library to find all elements of type <T> of the project<br>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 * @param <T> Type of the elements found
 */
public abstract class DisplayProcessor<T extends CtType<?>> extends AbstractProcessor<T> {
	protected List<T> elements;

	public DisplayProcessor() {
		this.elements = new ArrayList<T>();
	}

	public List<T> getElements() {
		return elements;
	}
}
