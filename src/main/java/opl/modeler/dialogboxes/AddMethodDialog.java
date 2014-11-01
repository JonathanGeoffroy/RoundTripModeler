package opl.modeler.dialogboxes;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;


/**
 * Show a dialog where user can enter a new Method
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class AddMethodDialog extends ComponentDialog {

	public AddMethodDialog(UmlModeler modeler) {
		super(modeler);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onResultOk() {
		try {
			modeler.addMethod(nameTextField.getText(), typeTextField.getText());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The specified type doesn't exist", "Type not found", JOptionPane.ERROR_MESSAGE);
		}		
	}

}
