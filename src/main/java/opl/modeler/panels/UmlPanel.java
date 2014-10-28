package opl.modeler.panels;

import java.awt.Dimension;
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
	private ComponentMover cm;

	public UmlPanel(Uml uml) {
		super();

		setPreferredSize(new Dimension(800, 600));

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

		// Attach this panel to Uml model
		uml.addObserver(this);
	}

	private void addPanel(ElementPanel<?> elementPanel) {
		elementPanel.setPreferredSize(new Dimension(100, 100));
		this.add(elementPanel);
		cm.registerComponent(elementPanel);
	}

	public void update(Observable o, Object arg) {
		/*
		 * Argh! Very hugly code! Reason is it exists sereval kinds of Panel:
		 * ClassPanel, InterfacePanel, EnumPanel, to have differents drawing
		 * behavior. So you have to create a ClassPanel to draw a class, an
		 * InterfacePanel to draw Interface, EnumPanel to draw Enum. FIXME: how
		 * to create the good kind of graphical object, by just use CtType arg ?
		 */
		ElementPanel<?> createdPanel;
		if (arg instanceof CtClass<?>) {
			createdPanel = new ClassPanel((CtClass<?>) arg);
		} else if (arg instanceof CtInterface<?>) {
			createdPanel = new InterfacePanel((CtInterface<?>) arg);
		} else if (arg instanceof CtEnum<?>) {
			createdPanel = new EnumPanel((CtEnum<?>) arg);
		} else
			throw new RuntimeException("Cannot cast " + arg.getClass());

		addPanel(createdPanel);
		revalidate();
		repaint();
	}
}