package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import opl.modeler.UmlModeler;
import opl.modeler.dialogboxes.AddMethodDialog;

public class OnMethodAddedListener implements ActionListener {

	private UmlModeler modeler;

	public OnMethodAddedListener(UmlModeler modeler) {
		this.modeler = modeler;
	}

	public void actionPerformed(ActionEvent e) {
		AddMethodDialog.showInputDialog(modeler);
	}

}
