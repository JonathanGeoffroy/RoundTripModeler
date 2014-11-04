package opl.modeler.controllers;

import opl.modeler.UmlModeler;

/**
 * Listener called when user click on "add Class" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnClassAddedListener extends OnElementAddedListener {

	public OnClassAddedListener(UmlModeler umlModeler, String title, String message) {
		super(umlModeler, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		umlModeler.addClass(elementName);
	}

}
