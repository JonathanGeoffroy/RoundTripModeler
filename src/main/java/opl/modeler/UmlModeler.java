package opl.modeler;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import opl.modeler.model.Uml;
import opl.modeler.panels.ButtonsPanel;
import opl.modeler.panels.UMLContentPanel;
import opl.modeler.panels.UmlPanel;
import opl.modeler.views.ElementPanel;
import opl.processors.FieldCreator;
import opl.processors.MethodCreator;
import spoon.Launcher;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.support.reflect.declaration.CtFieldImpl;

/**
 * The frame which contains the whole UML Modeler<br>
 * Simply instantiates each components and delegates UML model.
 *
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class UmlModeler extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UMLContentPanel umlContentPanel;
	private UmlPanel umlPanel;
	private Launcher spoon;
	private Uml uml;

	public UmlModeler(String title, Launcher spoon, Uml uml) throws HeadlessException {
		super(title);
		this.spoon = spoon;
		this.uml = uml;
		
		ButtonsPanel buttonsPanel = new ButtonsPanel(spoon, uml);
		umlContentPanel = new UMLContentPanel(this);
		umlPanel = new UmlPanel(uml, this);

		BorderLayout layout = new BorderLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);

		panel.add(buttonsPanel, BorderLayout.PAGE_START);
		panel.add(umlPanel, BorderLayout.CENTER);
		panel.add(umlContentPanel, BorderLayout.EAST);
		setContentPane(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public void notifySelectionChanged(ElementPanel<?> selected) {
		umlPanel.notifySelectionChanged(selected);
		umlContentPanel.notifySelectionChanged(selected);
	}

	public void addField(String name, String type) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedCtType = selectedPanel.getCtElement();
		FieldCreator.addField(spoon, name, type, selectedCtType);
		
		spoon.run();
		notifySelectionChanged(selectedPanel);
	}

	public void addMethod(String name, String returnType) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedType = selectedPanel.getCtElement();
		MethodCreator.addMethod(spoon, name, returnType, selectedType);
		spoon.run();
		notifySelectionChanged(selectedPanel);
	}

	/**
	 * Remove a field from the AST, and notify the view part
	 * 
	 * @param field
	 *            the field to remove
	 * @throws Exception
	 *             spoon.run() exceptions
	 */
	public void removeField(CtField<?> field) throws Exception {
		ElementPanel<?> selectedElement = getSelectedElement();
		selectedElement.getCtElement().removeField(field);
		
		File classFile = field.getParent().getPosition().getFile();
		classFile.delete();
		
		spoon.run();
		notifySelectionChanged(selectedElement);
	}
	
	/**
	 * Remove a method from the AST, and notify the view part
	 * 
	 * @param method
	 *            the method to remove
	 * @throws Exception
	 *             spoon.run() exceptions
	 */
	public void removeMethod(CtMethod<?> method) throws Exception {
		ElementPanel<?> selectedElement = getSelectedElement();
		selectedElement.getCtElement().removeMethod(method);
		
		File classFile = method.getParent().getPosition().getFile();
		classFile.delete();	
		
		spoon.run();
		notifySelectionChanged(selectedElement);
	}
	
	public Launcher getSpoon() {
		return spoon;
	}

	public Uml getUml() {
		return uml;
	}
	
	public ElementPanel<?> getSelectedElement() {
		return umlPanel.getSelected();
	}
}
