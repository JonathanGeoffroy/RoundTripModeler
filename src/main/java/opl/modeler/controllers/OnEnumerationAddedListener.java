package opl.modeler.controllers;

import opl.modeler.UmlModeler;

/**
 * Listener called when user click on "add Enum" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnEnumerationAddedListener extends OnElementAddedListener {

	public OnEnumerationAddedListener(UmlModeler umlModeler, String title, String message) {
		super(umlModeler, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		umlModeler.addEnumeration(elementName);
	}
}
