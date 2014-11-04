package opl.modeler.controllers;

import opl.modeler.UmlModeler;

/**
 * Listener called when user click on "add Interface" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnInterfaceAddedListener extends OnElementAddedListener {

	public OnInterfaceAddedListener(UmlModeler umlModeler, String title, String message) {
		super(umlModeler, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		umlModeler.addInterface(elementName);
	}

}
