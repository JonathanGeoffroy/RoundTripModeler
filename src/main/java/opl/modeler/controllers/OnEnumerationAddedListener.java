package opl.modeler.controllers;

import opl.modeler.model.Uml;

/**
 * Listener called when user click on "add Enum" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnEnumerationAddedListener extends OnElementAddedListener {

	public OnEnumerationAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		uml.addEnumeration(elementName);
	}
}
