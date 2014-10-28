package opl.modeler.controllers;

import opl.modeler.model.Uml;


public class OnClassAddedListener extends OnElementAddedListener {

	public OnClassAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) {
		uml.addClass(elementName);
	}

}
