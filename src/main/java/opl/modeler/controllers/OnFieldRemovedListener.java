package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import spoon.reflect.declaration.CtField;

/**
 * Listener called when user click on "remove field" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnFieldRemovedListener implements ActionListener {

	private UmlModeler modeler;
	
	/**
	 * The field to remove
	 */
	private CtField<?> field;

	public OnFieldRemovedListener(UmlModeler modeler, CtField<?> field) {
		super();
		this.modeler = modeler;
		this.field = field;
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			modeler.removeField(field);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enable to remove this field", "Field not removed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

}
