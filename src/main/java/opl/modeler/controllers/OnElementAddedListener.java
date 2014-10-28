package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import opl.modeler.model.Uml;

public abstract class OnElementAddedListener  implements ActionListener {
	protected Uml uml;
	private String title;
	private String message;
	
	public OnElementAddedListener(Uml uml, String title, String message) {
		super();
		this.uml = uml;
		this.title = title;
		this.message = message;
	}

	public void actionPerformed(ActionEvent event) {
		String response = (String) JOptionPane.showInputDialog(
				null, message, title, JOptionPane.QUESTION_MESSAGE);
		if(response != null && !response.trim().equals("")) {
			addElement(response);
		}
	}
	
	public abstract void addElement(String elementName);
}
