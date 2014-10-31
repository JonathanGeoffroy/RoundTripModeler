package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import opl.modeler.views.ElementPanel;
import spoon.reflect.declaration.CtField;

public class OnFieldRemovedListener implements ActionListener {

	private UmlModeler modeler;
	private CtField<?> field;

	public OnFieldRemovedListener(UmlModeler modeler, CtField<?> field) {
		super();
		this.modeler = modeler;
		this.field = field;
	}
	
	public void actionPerformed(ActionEvent event) {
		ElementPanel<?> selectedElement = modeler.getSelectedElement();
		selectedElement.getCtElement().removeField(field);
		
		File classFile = field.getParent().getPosition().getFile();
		classFile.delete();
		
		try {
			modeler.getSpoon().run();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enable to remove this field", "Field not removed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		modeler.notifySelectionChanged(selectedElement);
	}

}
