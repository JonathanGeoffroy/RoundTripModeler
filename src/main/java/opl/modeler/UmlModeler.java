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
import opl.processors.writers.ElementRenamer;
import opl.processors.writers.FieldCreator;
import opl.processors.writers.FieldRefactorer;
import opl.processors.writers.MethodCreator;
import opl.processors.writers.MethodRefactorer;
import spoon.Launcher;
import spoon.OutputType;
import spoon.compiler.SpoonCompiler;
import spoon.processing.AbstractManualProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;

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

		ButtonsPanel buttonsPanel = new ButtonsPanel(spoon, this);
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

	public void notifySelectionRenamed(String oldName, String newName) throws Exception {
		regenerateProject();
		umlPanel.notifySelectionRenamed(oldName, newName);
		umlContentPanel.notifySelectionChanged(getSelectedElement());
	}

	public void notifySelectionChanged(ElementPanel<?> selected) {
		umlPanel.notifySelectionChanged(selected);
		umlContentPanel.notifySelectionChanged(selected);
	}

	public void notifyElementRemoved(ElementPanel<?> removed) throws Exception {
		regenerateProject();
		umlPanel.onElementRemoved(removed);
	}

	public void addClass(String qualifiedName) throws Exception {
		CtClass<?> createdClass = spoon.getFactory().Class().create(qualifiedName);
		createdClass.setVisibility(ModifierKind.PUBLIC);
		uml.addClass(createdClass);
		spoon.run();
		regenerateProject();
	}
	
	public void addField(String name, String type) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedCtType = selectedPanel.getCtElement();

		FieldCreator fieldCreator = new FieldCreator(name, type, selectedCtType);
		runProcessor(fieldCreator);
	}

	public void addMethod(String name, String returnType) throws Exception {
		ElementPanel<?> selectedPanel = umlPanel.getSelected();
		CtType<?> selectedType = selectedPanel.getCtElement();

		MethodCreator methodCreator = new MethodCreator(name, returnType, selectedType);
		runProcessor(methodCreator);

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
		runProcessor(refactorerProcessor);
	}

	public void refactorMethod(CtMethod<?> method, String type, String name) throws Exception {
		MethodRefactorer refactorerProcessor = new MethodRefactorer(method, type, name);
		runProcessor(refactorerProcessor);
	}

	public void rename(CtType<?> element, String newName) throws Exception {
		String oldName = element.getQualifiedName();
		
		ElementRenamer elementRenamer = new ElementRenamer(element, newName);
		runProcessor(elementRenamer);
		notifySelectionRenamed(oldName, newName);
	}
	/**
	 * run the <code>processor</code>, regenerate the code and refresh the view part
	 * 
	 * @param processor
	 *            the processor to run
	 * @throws Exception
	 *             processor's exception
	 */
	private void runProcessor(AbstractManualProcessor processor) throws Exception {
		processor.setFactory(spoon.getFactory());
		processor.process();

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

	public void addEnumeration(String elementName) {
		// TODO Auto-generated method stub
		
	}

	public void addInterface(String elementName) {
		// TODO Auto-generated method stub
		
	}
}
