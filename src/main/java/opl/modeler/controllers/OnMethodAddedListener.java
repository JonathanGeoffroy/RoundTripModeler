package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import opl.modeler.UmlModeler;
import opl.modeler.dialogboxes.AddMethodDialog;

/**
 * Listener called when user click on "add method" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnMethodAddedListener implements ActionListener {

	private UmlModeler modeler;

	public OnMethodAddedListener(UmlModeler modeler) {
		this.modeler = modeler;
	}

	public void actionPerformed(ActionEvent e) {
		new AddMethodDialog(modeler).showInputDialog("Add New Method");
	}

}
