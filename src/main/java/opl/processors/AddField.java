package opl.processors;

import spoon.processing.AbstractManualProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

public class AddField extends AbstractManualProcessor {
	private String fieldType;
	private String fieldName;
	private String parentName;

	public AddField(String fieldType, String fieldName, String parentName) {
		super();
		this.fieldType = fieldType;
		this.fieldName = fieldName;
		this.parentName = parentName;
	}
	
	public void process() {
		// adding a class with a snippet
		CtCodeSnippetStatement snippet = getFactory().Core()
				.createCodeSnippetStatement();
		snippet.setValue("private " + fieldType + " " + fieldName + "");
		CtField<?> field = snippet.compile();

		// adding a class with a metamodel element
		CtClass<?> classMM = getFactory().Class().get(parentName);
		field.setParent(classMM);

	}
}