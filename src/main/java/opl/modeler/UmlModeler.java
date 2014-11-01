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
import opl.processors.writers.FieldCreator;
import opl.processors.writers.FieldRefactorer;
import opl.processors.writers.MethodCreator;
import opl.processors.writers.TypeReferenceProcessor;
import spoon.Launcher;
import spoon.OutputType;
import spoon.compiler.SpoonCompiler;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

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

	public void notifySelectionRenamed(String oldName, String newName) {
		umlPanel.notifySelectionRenamed(oldName, newName);
		umlContentPanel.notifySelectionChanged(getSelectedElement());
	}

	public void notifySelectionChanged(ElementPanel<?> selected) {
		umlPanel.notifySelectionChanged(selected);
		umlContentPanel.notifySelectionChanged(selected);
	}

	public void notifyElementRemoved(ElementPanel<?> removed) {
		umlPanel.onElementRemoved(removed);
	}

	public void addField(String name, String type) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedCtType = selectedPanel.getCtElement();

		FieldCreator fieldCreator = new FieldCreator(name, type, selectedCtType);
		addComponent(fieldCreator);
	}

	public void addMethod(String name, String returnType) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedType = selectedPanel.getCtElement();

		MethodCreator methodCreator = new MethodCreator(name, returnType, selectedType);
		addComponent(methodCreator);

	}

	/**
	 * Convenience method for add{Class/Interface/Enumeration}<br>
	 * process the creator processor and refresh the view part
	 * 
	 * @param creator
	 *            the processor to run
	 * @throws Exception
	 *             processor's exception
	 */
	private void addComponent(TypeReferenceProcessor creator) throws Exception {
		creator.setFactory(spoon.getFactory());
		creator.process();

		regenerateProject();
		notifySelectionChanged(getSelectedElement());
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

	public void refactorField(CtField<?> field, String type, String name) throws Exception {
		FieldRefactorer refactorerProcessor = new FieldRefactorer(field, type, name);
		refactorerProcessor.setFactory(spoon.getFactory());
		refactorerProcessor.process();

		regenerateProject();
		notifySelectionChanged(getSelectedElement());
	}

	/**
	 * Regenerate the project source code by using a new SpoonCompiler
	 * 
	 * @throws Exception
	 */
	private void regenerateProject() throws Exception {
		SpoonCompiler compiler = spoon.createCompiler(spoon.getFactory());
		File outputDirectory = spoon.getFactory().getEnvironment().getDefaultFileGenerator().getOutputDirectory();
		compiler.setOutputDirectory(outputDirectory);
		compiler.setDestinationDirectory(outputDirectory);
		compiler.generateProcessedSourceFiles(OutputType.COMPILATION_UNITS);
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
