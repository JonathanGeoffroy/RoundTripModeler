package opl.modeler.panels;

import opl.modeler.UmlModeler;

import javax.swing.*;

/**
 * Add buttons to add:
 * <ol>
 * <ul>
 * a new Class to uml, * a new Interface to uml,
 * </ul>
 * <ul>
 * a newEnumeration to uml,
 * </ul>
 * <ul>
 * a new attribute to selected class,
 * </ul>
 * <ul>
 * a new method to selected class / interface
 * </ul>
 * </ol>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class ButtonsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -897261829968443656L;
	private static final String ADD_CLASS = "Add Class";
	private static final String ADD_INTERFACE = "Add Interface";
	private static final String ADD_ATTRIBUTE = "Add Attribute";
	private static final String ADD_METHOD = "Add Method";

	public ButtonsPanel(UmlModeler umlModeler) {
		super();
		JButton addClass = new JButton(ADD_CLASS);
		JButton addInterface = new JButton(ADD_INTERFACE);
		JButton addAttribute = new JButton(ADD_ATTRIBUTE);
		JButton addMethod = new JButton(ADD_METHOD);

		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);

		add(addClass);
		add(addInterface);
		add(addAttribute);
		add(addMethod);
	}
}
