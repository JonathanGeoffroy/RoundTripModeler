package opl.processors.writers;

import java.io.File;

import spoon.processing.AbstractManualProcessor;
import spoon.reflect.declaration.CtType;

/**
 * Rename a CtType
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ElementRenamer extends AbstractManualProcessor {
	private CtType<?> element;	
	private String name;
	
	public ElementRenamer(CtType<?> element, String name) {
		super();
		this.element = element;
		this.name = name;
	}

	public void process() {
		// Find the File of the renamed element
		File classFile = element.getPosition().getFile();

		// Rename the element
		element.setSimpleName(name);
		element.replace(element);

		// Remove the element from the File System
		classFile.delete();

	}

}
