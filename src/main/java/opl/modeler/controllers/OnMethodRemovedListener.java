package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import opl.modeler.views.ElementPanel;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;

public class OnMethodRemovedListener implements ActionListener {

	private UmlModeler modeler;
	private CtMethod<?> method;

	public OnMethodRemovedListener(UmlModeler modeler, CtMethod<?> method) {
		super();
		this.modeler = modeler;
		this.method = method;
	}
	
	public void actionPerformed(ActionEvent event) {
		ElementPanel<?> selectedElement = modeler.getSelectedElement();
		selectedElement.getCtElement().removeMethod(method);
		
		File classFile = method.getParent().getPosition().getFile();
		classFile.delete();
		
		try {
			modeler.getSpoon().run();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enable to remove this method", "Method not removed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		modeler.notifySelectionChanged(selectedElement);
	}

}
