package opl.modeler.dialogboxes;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;

/**
 * Show a dialog where user can enter a new Field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class AddFieldDialog extends ComponentDialog {

	public AddFieldDialog(UmlModeler modeler) {
		super(modeler);
	}

	@Override
	protected void onResultOk() {
		try {
			String nameString = this.nameTextField.getText();
			String typeString = this.typeTextField.getText();
			modeler.addField(nameString, typeString);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The specified type doesn't exist", "Type not found", JOptionPane.ERROR_MESSAGE);
		}
	}
}
