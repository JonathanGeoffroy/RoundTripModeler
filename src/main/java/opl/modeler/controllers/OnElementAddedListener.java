package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import opl.modeler.model.Uml;

/**
 * Listener called when user click on "add" button<br>
 * Open a dialog box to ask the name of the new element (class, interface,
 * enumeration)<br>
 * If this name isn't empty, delegates the creation of this new element to
 * <code>uml</code>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class OnElementAddedListener implements ActionListener {
	/**
	 * The uml model where to add an element
	 */
	protected Uml uml;
	
	/**
	 * The title of the dialog box opened when user click on button
	 */
	private String title;
	
	/**
	 * The message of the dialog box opened when user click on button
	 */
	private String message;

	public OnElementAddedListener(Uml uml, String title, String message) {
		super();
		this.uml = uml;
		this.title = title;
		this.message = message;
	}

	public void actionPerformed(ActionEvent event) {
		String response = (String) JOptionPane.showInputDialog(null, message,
				title, JOptionPane.QUESTION_MESSAGE);
		if (response != null && !response.trim().isEmpty()) {
			try {
				addElement(response);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"An internal error occurred. Please try again",
						"Internal Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public abstract void addElement(String elementName) throws Exception;
}
