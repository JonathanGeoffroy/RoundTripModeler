package opl.modeler.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import opl.modeler.model.Uml;
import opl.modeler.views.ClassPanel;
import opl.modeler.views.ElementPanel;
import opl.modeler.views.EnumPanel;
import opl.modeler.views.InterfacePanel;
import opl.observer.Observer;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;

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
	
	public UmlPanel(Uml uml) {
		super();
		this.uml = uml;
		
		setPreferredSize(new Dimension(800, 600));

		addAllPanels();

		// Attach this panel to Uml model
		uml.addObserver(this);
	}

	private void addPanel(ElementPanel<?> elementPanel) {
		elementPanel.setPreferredSize(new Dimension(100, 100));
		this.add(elementPanel);
		cm.registerComponent(elementPanel);
	}

	private void addAllPanels() {
		cm = new ComponentMover();
		ElementPanel<?> elementPanel;
		for (CtClass<?> c : uml.getClasses()) {
			elementPanel = new ClassPanel(c);
			addPanel(elementPanel);
		}

		for (CtInterface<?> i : uml.getInterfaces()) {
			elementPanel = new InterfacePanel(i);
			addPanel(elementPanel);
		}

		for (CtEnum<?> e : uml.getEnumerations()) {
			elementPanel = new EnumPanel(e);
			addPanel(elementPanel);
		}
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

	public void onCodeReloaded() {
		this.removeAll();
		addAllPanels();
		revalidate();
		repaint();
	}
}