package opl.modeler.dialogboxes;

import javax.swing.JOptionPane;

import opl.modeler.UmlModeler;
import spoon.reflect.declaration.CtMethod;

public class RefactorMethodDialog extends AddMethodDialog {
	private CtMethod<?> method;

	public RefactorMethodDialog(UmlModeler modeler, CtMethod<?> method) {
		super(modeler);
		this.method = method;
		nameTextField.setText(method.getSimpleName());
		typeTextField.setText(method.getType().getQualifiedName());
	}

	@Override
	protected void onResultOk() {
		try {
			modeler.refactorMethod(method, typeTextField.getText(), nameTextField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internal Error",
					"Unable to refactor field", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
