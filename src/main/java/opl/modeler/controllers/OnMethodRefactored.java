package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import opl.modeler.UmlModeler;
import opl.modeler.dialogboxes.RefactorMethodDialog;
import spoon.reflect.declaration.CtMethod;

public class OnMethodRefactored implements ActionListener {
	private UmlModeler modeler;
	private CtMethod<?> method;
	
	public OnMethodRefactored(UmlModeler modeler, CtMethod<?> method) {
		this.modeler = modeler;
		this.method = method;
	}

	public void actionPerformed(ActionEvent event) {
		new RefactorMethodDialog(modeler, method).showInputDialog("Refactor Method");
	}
}
