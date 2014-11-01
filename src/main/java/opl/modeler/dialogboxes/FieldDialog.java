package opl.modeler.dialogboxes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;

/**
 * Show a dialog where user can enter a  Field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class FieldDialog {
	protected JTextField nameField;
	protected JTextField typeField;
	private JPanel panel;
	protected UmlModeler modeler;
	
	public FieldDialog(UmlModeler modeler) {
		this.modeler = modeler;
		
		JLabel nameLabel = new JLabel("name:");
		nameField = new JTextField();
		JLabel typeLabel = new JLabel("return type:");
		typeField = new JTextField();

		panel = new JPanel(new GridLayout(0, 2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(typeLabel);
		panel.add(typeField);
	}
	
	public void showInputDialog(String title) {
		int result = JOptionPane.showConfirmDialog(null, panel, 
				title, JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			onResultOk();
		}
	}

	protected abstract void onResultOk();
}
