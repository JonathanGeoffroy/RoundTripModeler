package opl.modeler.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;
import opl.modeler.controllers.OnClassNameChangedListener;
import opl.modeler.controllers.OnFieldAddedListener;
import opl.modeler.controllers.OnFieldRefactored;
import opl.modeler.controllers.OnFieldRemovedListener;
import opl.modeler.controllers.OnMethodAddedListener;
import opl.modeler.controllers.OnMethodRefactored;
import opl.modeler.controllers.OnMethodRemovedListener;
import opl.modeler.views.ClassPanel;
import opl.modeler.views.ElementPanel;
import opl.modeler.views.InterfacePanel;
import opl.processors.writers.FieldReferencesProcessor;
import opl.processors.writers.MethodReferencesProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtReference;

/**
 * A JPanel able to show name, attributes and methods of the selected class of
 * the UmlPanel
 *
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class UMLContentPanel extends JPanel {

	private static final long serialVersionUID = -967498044236816796L;
	private UmlModeler modeler;

	public UMLContentPanel(UmlModeler modeler) {
		this.modeler = modeler;
		setPreferredSize(new Dimension(400, 600));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void notifySelectionChanged(ElementPanel<?> selected) {
		selected.accept(this);
	}

	/**
	 * Specific drawing method for ClassPanel <br>
	 * Show name, attributes and methods
	 *
	 * @param selected
	 *            the panel to display
	 */
	public void drawElementPanel(ClassPanel selected) {
		this.removeAll();
		CtType<?> selectedCtType = selected.getCtElement();

		JPanel name = createNamePanel(selected);
		JPanel attributes = createFieldsPanel(selectedCtType);
		JPanel methods = createMethodsPanel(selectedCtType);

		JPanel components = new JPanel();
		BoxLayout componentsLayout = new BoxLayout(components, BoxLayout.Y_AXIS);
		components.setLayout(componentsLayout);
		components.add(attributes);
		components.add(methods);

		this.add(name);
		this.add(components);

		revalidate();
		repaint();
	}

	/**
	 * Specific drawing method for InterfacePanel <br>
	 * Show name and attributes
	 *
	 * @param selected
	 *            the panel to display
	 */
	public void drawElementPanel(InterfacePanel selected) {
		this.removeAll();
		CtType<?> selectedCtType = selected.getCtElement();

		JPanel name = createNamePanel(selected);
		JPanel methods = createMethodsPanel(selectedCtType);

		JPanel components = new JPanel();
		BoxLayout componentsLayout = new BoxLayout(components, BoxLayout.Y_AXIS);
		components.setLayout(componentsLayout);
		components.add(methods);

		this.add(name);
		this.add(components);

		revalidate();
		repaint();
	}

	/**
	 * Create a JPanel which contains the name of the selected panel
	 *
	 * @param selected
	 *            the selected panel
	 * @return the JPanel
	 */
	private JPanel createNamePanel(ElementPanel<?> selected) {
		JPanel namePanel = new JPanel();
		BoxLayout layout = new BoxLayout(namePanel, BoxLayout.X_AXIS);
		namePanel.setLayout(layout);

		JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(130, 20));
		nameField.setText(selected.getCtElement().getSimpleName());

		JButton nameButton = new JButton("Ok");
		nameButton.setPreferredSize(new Dimension(70, 20));
		nameButton.addActionListener(new OnClassNameChangedListener(modeler, selected, nameField));

		namePanel.add(nameField);
		namePanel.add(nameButton);
		return namePanel;
	}

	/**
	 * Create a JPanel which contains all attributes of the selected panel
	 *
	 * @param selected
	 *            the selected panel
	 * @return the JPanel
	 */
	private JPanel createFieldsPanel(CtType<?> selected) {
		JButton removeButton;
		JButton refactorButton;
		
		List<CtReference> processorReferences;
		FieldReferencesProcessor processor;
		
		GridBagConstraints gridConstraint = new GridBagConstraints();
		gridConstraint.fill = GridBagConstraints.CENTER;
		gridConstraint.gridy = 0;
		JPanel fields = new JPanel(new GridBagLayout());
		fields.setPreferredSize(new Dimension(390, 200));
		
		for(CtField<?> field : selected.getFields()) {
			gridConstraint.gridx = 0;
			fields.add(new Label(field.getSimpleName() + " : " + field.getType().getSimpleName()), gridConstraint);
			
			// Add Refactor button
			gridConstraint.gridx = 1;
			refactorButton = new JButton("Refact");
			refactorButton.addActionListener(new OnFieldRefactored(modeler, field));
			fields.add(refactorButton, gridConstraint);
			
			// Add Remove button
			gridConstraint.gridx = 2;
			removeButton = new JButton("X");
			removeButton.addActionListener(new OnFieldRemovedListener(modeler, field));
			fields.add(removeButton, gridConstraint);

			// Check if the field is used
			// If it is, user can't remove this field
			processor = new FieldReferencesProcessor();
			processor.process(field);
			processorReferences = processor.getReferences();
			if(!processorReferences.isEmpty()) {
				removeButton.setEnabled(false);
				removeButton.setToolTipText("This field is used by: " + processorReferences);
				refactorButton.setEnabled(false);
				removeButton.setToolTipText("This field is used by: " + processorReferences);
			}
			
			gridConstraint.gridy++;
		}
		
		// Add Field button
		gridConstraint.gridx = 0;
		gridConstraint.gridy++;
		gridConstraint.gridwidth = 4;
		JButton addFieldButton = new JButton("Add Field");
		addFieldButton.addActionListener(new OnFieldAddedListener(modeler));
		fields.add(addFieldButton, gridConstraint);

		return fields;
	}

	/**
	 * Create a JPanel which contains all methods of the selected panel
	 *
	 * @param selected
	 *            the selected panel
	 * @return the JPanel
	 */
	private JPanel createMethodsPanel(CtType<?> selected) {
		JButton removeButton;
		JButton refactorButton;
		List<CtReference> processorReferences;
		MethodReferencesProcessor processor;
		
		GridBagConstraints gridConstraint = new GridBagConstraints();
		gridConstraint.fill = GridBagConstraints.CENTER;
		gridConstraint.gridy = 0;
		JPanel methods = new JPanel(new GridBagLayout());
		methods.setPreferredSize(new Dimension(390, 200));

		for(CtMethod<?> method : selected.getMethods()) {
			// Add method signature
			gridConstraint.gridx = 0;
			methods.add(new Label(method.getSignature()), gridConstraint);
			
			// Add Refactor button
			gridConstraint.gridx = 1;
			refactorButton = new JButton("Refact");
			refactorButton.addActionListener(new OnMethodRefactored(modeler, method));
			methods.add(refactorButton, gridConstraint);
			
			// Add Remove button
			gridConstraint.gridx = 2;
			removeButton = new JButton("X");
			removeButton.addActionListener(new OnMethodRemovedListener(modeler, method));
			methods.add(removeButton, gridConstraint);
			
			// Check if the field is used
			// If it is, user can't remove this field
			processor = new MethodReferencesProcessor(method);
			for(CtClass<?> c: modeler.getUml().getClasses()) {
				processor.process(c);
			}
			processorReferences = processor.getReferences();
			if(!processorReferences.isEmpty()) {
				removeButton.setEnabled(false);
				removeButton.setToolTipText("This method is used by: " + processorReferences);
				refactorButton.setEnabled(false);
				removeButton.setToolTipText("This method is used by: " + processorReferences);
			}
			
			gridConstraint.gridy++;
		}
		
		// Add Method button
		gridConstraint.gridx = 0;
		gridConstraint.gridy++;
		gridConstraint.gridwidth = 4;
		JButton addMethodButton = new JButton("Add Method");
		addMethodButton.addActionListener(new OnMethodAddedListener(modeler));
		methods.add(addMethodButton, gridConstraint);

		return methods;
	}
}