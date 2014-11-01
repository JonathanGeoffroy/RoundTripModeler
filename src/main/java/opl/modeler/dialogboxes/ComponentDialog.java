package opl.modeler.dialogboxes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;

/**
 * Show a dialog where user can enter a Component {Field, Method}
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class ComponentDialog {
	protected JTextField nameTextField;
	protected JTextField typeTextField;
	private JPanel panel;
	protected UmlModeler modeler;
	
	public ComponentDialog(UmlModeler modeler) {
		this.modeler = modeler;
		
		JLabel nameLabel = new JLabel("name:");
		nameTextField = new JTextField();
		JLabel typeLabel = new JLabel("return type:");
		typeTextField = new JTextField();

		panel = new JPanel(new GridLayout(0, 2));
		panel.add(nameLabel);
		panel.add(nameTextField);
		panel.add(typeLabel);
		panel.add(typeTextField);
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
