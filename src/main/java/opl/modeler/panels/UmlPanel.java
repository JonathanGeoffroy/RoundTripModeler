package opl.modeler.panels;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import opl.modeler.UmlModeler;
import opl.modeler.model.Uml;
import opl.modeler.views.ClassPanel;
import opl.modeler.views.ElementPanel;
import opl.modeler.views.EnumPanel;
import opl.modeler.views.InterfacePanel;
import opl.observer.Observer;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtType;

/**
 * Draw the Uml model of the project<br>
 * Draw each class, interface and enumeration<br>
 * Each component can be moved into this Uml model
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class UmlPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1386070124268041527L;
	private ComponentMover cm;
	private Uml uml;
	private UmlModeler modeler;
	private ElementPanel<?> selected;
	/**
	 * A Map which contains all ElementPanel
	 * ctType.getQualifiedName() -> ElementPanel(ctType)
	 */
	private Map<String, ElementPanel<?>> elementPanels; 
	
	public UmlPanel(Uml uml, UmlModeler modeler) {
		super();
		this.uml = uml;
		this.modeler = modeler;
		this.elementPanels = new HashMap<String, ElementPanel<?>>();
		
		this.setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addAllPanels();

		// Attach this panel to Uml model
		uml.addObserver(this);
	}

	/**
	 * Add an element panel. If this panel is already is this UmlPanel, just
	 * refresh it by changing its CtType
	 * 
	 * @param elementPanel
	 */
	private void addPanel(ElementPanel<?> elementPanel) {
		CtType<?> elementType = elementPanel.getCtElement();
		String elementQualifiedName = elementType.getQualifiedName();
		
		if(elementPanels.containsKey(elementQualifiedName)) {
			elementPanel = elementPanels.get(elementQualifiedName); // Find the real reference of the drawn ElementPanel
			elementPanel.setCtElement(elementType);
			elementPanel.revalidate();
		}
		else {
			elementPanel.setBounds((int)(Math.random() * 700), (int)(Math.random() * 500), 100, 100);
			this.add(elementPanel);
			elementPanels.put(elementQualifiedName, elementPanel);
			cm.registerComponent(elementPanel);
		}
	}

	/**
	 * Add all elements from the uml
	 */
	private void addAllPanels() {
		cm = new ComponentMover(modeler);
		ElementPanel<?> elementPanel;
		Map<String, ElementPanel<?>> added = new HashMap<String, ElementPanel<?>>();
		for (CtClass<?> c : uml.getClasses()) {
			elementPanel = new ClassPanel(c);
			addPanel(elementPanel);
			added.put(c.getQualifiedName(), elementPanel);
		}

		for (CtInterface<?> i : uml.getInterfaces()) {
			elementPanel = new InterfacePanel(i);
			addPanel(elementPanel);
			added.put(i.getQualifiedName(), elementPanel);
		}

		for (CtEnum<?> e : uml.getEnumerations()) {
			elementPanel = new EnumPanel(e);
			addPanel(elementPanel);
			added.put(e.getQualifiedName(), elementPanel);
		}
		
		// Remove all CompilationUnits which are not in the source code anymore
		for(String panelName : elementPanels.keySet()) {
			if(! added.containsKey(panelName)) {
				this.remove(elementPanels.get(panelName));
			}
		}
		elementPanels = added; // Keep the new Map safe
	}
	
	public void onClassAdded(CtClass<?> ctClass) {
		ElementPanel<?> createdPanel = new ClassPanel(ctClass);
		addPanel(createdPanel);
		revalidate();
		repaint();
	}

	public void onInterfaceAdded(CtInterface<?> ctInterface) {
		ElementPanel<?> createdPanel = new InterfacePanel(ctInterface);
		addPanel(createdPanel);
		revalidate();
		repaint();
	}

	public void onEnumAdded(CtEnum<?> ctEnum) {
		ElementPanel<?> createdPanel = new EnumPanel(ctEnum);
		addPanel(createdPanel);
		revalidate();
		repaint();
	}

	public void onElementRemoved(ElementPanel<?> removed) {
		String removedQualifiedName = removed.getCtElement().getQualifiedName();
		ElementPanel<?> removedPanel = elementPanels.get(removedQualifiedName);
		this.remove(removedPanel);
		elementPanels.remove(removedQualifiedName);
	}
	
	/**
	 * Callback when the entire code is reloaded
	 */
	public void onCodeReloaded() {
		addAllPanels();
		revalidate();
		repaint();
	}

	public void notifySelectionRenamed(String oldName, String newName) {
		ElementPanel<?> toMove = elementPanels.get(oldName);
		elementPanels.remove(oldName);
		elementPanels.put(newName, toMove);
		
		revalidate();
		repaint();
	}
	
	public void notifySelectionChanged(ElementPanel<?> newSelection) {
		if(this.selected != null) {
			this.selected.setSelected(false);
		}
		this.selected = newSelection;
		newSelection.setSelected(true);
		
		revalidate();
		repaint();
	}

	public ElementPanel<?> getSelected() {
		return selected;
	}
}