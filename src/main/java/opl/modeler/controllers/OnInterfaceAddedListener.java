package opl.modeler.controllers;

import opl.modeler.model.Uml;

public class OnInterfaceAddedListener extends OnElementAddedListener {

	public OnInterfaceAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) {
		uml.addInterface(elementName);
	}

}
