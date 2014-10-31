package opl.modeler.dialogboxes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;

public class AddMethodDialog {

	public static void showInputDialog(UmlModeler modeler) {
		JLabel nameLabel = new JLabel("name:");
		JTextField nameMethod = new JTextField();
		JLabel typeLabel = new JLabel("return type:");
		JTextField returnTypeMethod = new JTextField();

		JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(nameLabel);
		panel.add(nameMethod);
		panel.add(typeLabel);
		panel.add(returnTypeMethod);

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Add new Method", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String nameString = nameMethod.getText();
			String typeString = returnTypeMethod.getText();
			if(nameString != null && typeString != null) {
				try {
					modeler.addMethod(nameString, typeString);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "The specified type doesn't exist", "Type not found", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
