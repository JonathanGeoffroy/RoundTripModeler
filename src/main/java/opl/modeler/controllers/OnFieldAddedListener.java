package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import opl.modeler.UmlModeler;
import opl.modeler.dialogboxes.AddFieldDialog;

public class OnFieldAddedListener implements ActionListener {

	private UmlModeler modeler;

	public OnFieldAddedListener(UmlModeler modeler) {
		this.modeler = modeler;
	}

	public void actionPerformed(ActionEvent event) {
		new AddFieldDialog(modeler).showInputDialog("Add new Field");
	}

}
