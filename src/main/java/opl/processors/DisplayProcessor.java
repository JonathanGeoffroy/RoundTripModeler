package opl.processors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtType;

public abstract class DisplayProcessor<T extends CtType<?>> extends AbstractProcessor<T> {
	protected List<T> elements;

	public DisplayProcessor() {
		this.elements = new ArrayList<T>();
	}

	public List<T> getElements() {
		return elements;
	}
}
