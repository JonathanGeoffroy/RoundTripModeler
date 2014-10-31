package opl.modeler.panels;

import java.awt.Dimension;
import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import opl.modeler.UmlModeler;
import opl.modeler.controllers.OnClassNameChangedListener;
import opl.modeler.controllers.OnFieldAddedListener;
import opl.modeler.controllers.OnMethodAddedListener;
import opl.modeler.views.ClassPanel;
import opl.modeler.views.ElementPanel;
import opl.modeler.views.InterfacePanel;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

/**
 * A JPanel able to show name, attributes and methods of the selected class of
 * the UmlPanel
 *
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class UMLContentPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = -967498044236816796L;
	private UmlModeler modeler;

	public UMLContentPanel(UmlModeler modeler) {
		this.modeler = modeler;
		setPreferredSize(new Dimension(200, 600));
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
		JPanel attributes = createAttributesPanel(selectedCtType);
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
	private JPanel createAttributesPanel(CtType<?> selected) {
		JPanel attributes = new JPanel();
		BoxLayout attrsLayout = new BoxLayout(attributes, BoxLayout.Y_AXIS);
		attributes.setLayout(attrsLayout);
		for(CtField<?> field : selected.getFields()) {
			attributes.add(new Label(field.getSimpleName() + " : " + field.getType().getSimpleName()));
		}
		JButton addFieldButton = new JButton("Add Field");
		addFieldButton.addActionListener(new OnFieldAddedListener(modeler));
		attributes.add(addFieldButton);
		
		return attributes;
	}
	
	/**
	 * Create a JPanel which contains all methods of the selected panel
	 * 
	 * @param selected
	 *            the selected panel
	 * @return the JPanel
	 */
	private JPanel createMethodsPanel(CtType<?> selected) {
		JPanel methods = new JPanel();
		BoxLayout methodsLayout = new BoxLayout(methods, BoxLayout.Y_AXIS);
		methods.setLayout(methodsLayout);
		for(CtMethod<?> method : selected.getMethods()) {
			methods.add(new Label(method.getSignature()));
		}
		JButton addMethodButton = new JButton("Add Method");
		addMethodButton.addActionListener(new OnMethodAddedListener(modeler));
		methods.add(addMethodButton);
		
		return methods;
	}
}