package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import spoon.reflect.declaration.CtMethod;

/**
 * Listener called when user click on "remove method" button
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnMethodRemovedListener implements ActionListener {

	private UmlModeler modeler;
	
	/**
	 * The method to remove
	 */
	private CtMethod<?> method;

	public OnMethodRemovedListener(UmlModeler modeler, CtMethod<?> method) {
		super();
		this.modeler = modeler;
		this.method = method;
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			modeler.removeMethod(method);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enable to remove this method", "Method not removed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}

}
