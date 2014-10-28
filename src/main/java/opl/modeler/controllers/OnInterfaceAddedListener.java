package opl.modeler.controllers;

import opl.modeler.model.Uml;

/**
 * Listener called when user click on "add Interface" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnInterfaceAddedListener extends OnElementAddedListener {

	public OnInterfaceAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) throws Exception {
		uml.addInterface(elementName);
	}

}
