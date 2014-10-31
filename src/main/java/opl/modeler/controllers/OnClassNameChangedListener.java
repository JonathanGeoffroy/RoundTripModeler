package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import opl.modeler.UmlModeler;
import opl.modeler.views.ElementPanel;

/**
 * Listener called when user change the name of the selected element (class,
 * interface, enumeration)
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnClassNameChangedListener implements ActionListener {
	private UmlModeler modeler;
	private ElementPanel<?> element;
	private JTextField name;

	public OnClassNameChangedListener(UmlModeler modeler, ElementPanel<?> element, JTextField name) {
		this.modeler = modeler;
		this.element = element;
		this.name = name;
	}

	public void actionPerformed(ActionEvent event) {
		// Find the File of the renamed element
		File classFile = element.getCtElement().getPosition().getFile();
		
		// Rename the element
		String oldName = element.getCtElement().getQualifiedName();
		String newName = name.getText();
		element.getCtElement().setSimpleName(newName);
		
		// Remove the element from the File System
		classFile.delete();
		
		modeler.notifySelectionRenamed(oldName, element.getCtElement().getQualifiedName());
	}

}
