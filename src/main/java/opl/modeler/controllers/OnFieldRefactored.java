package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import opl.modeler.UmlModeler;
import opl.modeler.dialogboxes.RefactorFieldDialog;
import spoon.reflect.declaration.CtField;

/**
 * Listener called when user click on "Refact" button for a field.
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnFieldRefactored implements ActionListener {

		private UmlModeler modeler;
		private CtField<?> field;
		
		public OnFieldRefactored(UmlModeler modeler, CtField<?> field) {
			this.modeler = modeler;
			this.field = field;
		}

		public void actionPerformed(ActionEvent event) {
			new RefactorFieldDialog(modeler, field).showInputDialog("Refactor Field");
		}
}
