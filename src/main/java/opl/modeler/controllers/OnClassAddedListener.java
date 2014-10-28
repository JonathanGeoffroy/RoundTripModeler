package opl.modeler.controllers;

import opl.modeler.model.Uml;

/**
 * Listener called when user click on "add Class" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnClassAddedListener extends OnElementAddedListener {

	public OnClassAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		uml.addClass(elementName);
	}

}
