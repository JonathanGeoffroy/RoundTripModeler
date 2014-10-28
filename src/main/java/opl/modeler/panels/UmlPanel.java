package opl.modeler.panels;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import opl.modeler.model.Uml;
import opl.modeler.views.ClassPanel;
import opl.modeler.views.ElementPanel;
import opl.modeler.views.EnumPanel;
import opl.modeler.views.InterfacePanel;
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
	private Uml uml;
	private List<ElementPanel<?>> umlPanels;

	public UmlPanel(Uml uml) {
		super();
		this.uml = uml;
		umlPanels = new ArrayList<ElementPanel<?>>();

		setPreferredSize(new Dimension(800, 600));

		ComponentMover cm = new ComponentMover();

		ElementPanel<?> elementPanel;
		for (CtClass<?> c : uml.getClasses()) {
			elementPanel = new ClassPanel(c);
			addPanel(elementPanel, cm);
		}
		
		for (CtInterface<?> i : uml.getInterfaces()) {
			elementPanel = new InterfacePanel(i);
			addPanel(elementPanel, cm);
		}
		
		for (CtEnum<?> e : uml.getEnumerations()) {
			elementPanel = new EnumPanel(e);
			addPanel(elementPanel, cm);
		}
	}

	private void addPanel(ElementPanel<?> elementPanel, ComponentMover cm) {
		elementPanel.setPreferredSize(new Dimension(100, 100));
		umlPanels.add(elementPanel);
		this.add(elementPanel);
		cm.registerComponent(elementPanel);
	}

	public void update(Observable o, Object arg) {
		repaint();
	}
}
