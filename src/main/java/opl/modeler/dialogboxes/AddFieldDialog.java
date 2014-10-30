package opl.modeler.dialogboxes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;

public class AddFieldDialog {

	public static void showInputDialog(UmlModeler modeler) {
		JLabel nameLabel = new JLabel("name:");
		JTextField nameField = new JTextField();
		JLabel typeLabel = new JLabel("type:");
		JTextField typeField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(typeLabel);
		panel.add(typeField);
		
		JOptionPane optionPane = new JOptionPane();
		optionPane.add(panel);
		optionPane.createDialog("Add new Field").setVisible(true);
		
		String nameString = nameField.getText();
		String typeString = typeField.getText();
		if(nameString != null && typeString != null) {
			try {
				modeler.addField(nameString, typeString);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The specified type doesn't exists", "Type not found", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
