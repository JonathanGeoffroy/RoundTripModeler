package opl.modeler.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import opl.modeler.model.Uml;
import opl.processors.UmlFinder;
import spoon.Launcher;

/**
 * Listener called when user want to reload the entire source code
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class OnCodeReloadedListener implements ActionListener {
	private Uml uml;
	private Launcher spoon;

	public OnCodeReloadedListener(Launcher spoon, Uml uml) {
		this.spoon = spoon;
		this.uml = uml;
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			UmlFinder.runSpoonProcessors(spoon, uml);
			uml.onCodeReloaded();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internal Error",
					"Unable to reload source code", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
