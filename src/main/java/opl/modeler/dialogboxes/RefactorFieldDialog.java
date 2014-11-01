package opl.modeler.dialogboxes;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import spoon.reflect.declaration.CtField;

/**
 * Show a dialog where user can refactor an existing Field
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class RefactorFieldDialog extends ComponentDialog {

	private CtField<?> field;

	public RefactorFieldDialog(UmlModeler modeler, CtField<?> field) {
		super(modeler);
		this.field = field;
		nameTextField.setText(field.getSimpleName());
		typeTextField.setText(field.getType().getQualifiedName());
	}

	@Override
	protected void onResultOk() {
		try {
			modeler.refactorField(field, typeTextField.getText(), nameTextField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internal Error",
					"Unable to refactor field", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
