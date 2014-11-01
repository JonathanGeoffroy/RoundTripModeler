package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
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
		try {
			modeler.rename(element.getCtElement(), name.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to rename component", "Unable to Rename", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
