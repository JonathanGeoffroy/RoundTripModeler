package opl.modeler.panels;

import opl.modeler.controllers.OnElementAddedListener;
import opl.modeler.model.Uml;

public class OnEnumerationAddedListener extends OnElementAddedListener {

	public OnEnumerationAddedListener(Uml uml, String title, String message) {
		super(uml, title, message);
	}

	@Override
	public void addElement(String elementName) {
		uml.addEnumeration(elementName);

	}

}
