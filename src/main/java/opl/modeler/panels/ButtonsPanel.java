package opl.modeler.panels;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import opl.modeler.UmlModeler;
import opl.modeler.controllers.OnClassAddedListener;
import opl.modeler.controllers.OnCodeReloadedListener;
import opl.modeler.controllers.OnEnumerationAddedListener;
import opl.modeler.controllers.OnInterfaceAddedListener;
import spoon.Launcher;

/**
 * Add buttons to add:
 * <ul>
 * <li>a new Class to uml,</li>
 * <li>a new Interface to uml,</li>
 * <li>a newEnumeration to uml,</li>
 * <li>a new attribute to selected class,</li>
 * <li>a new method to selected class / interface</li>
 * </ul>
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
	private static final String ADD_ENUMERATION = "Add Enum";
	private static final String RELOAD_CODE = "Reload Code";

	private static final String ADD_CLASS_MESSAGE = "Please enter the qualified name of the new class";
	private static final String ADD_INTERFACE_MESSAGE = "Please enter the qualified name of the new interface";
	private static final String ADD_ENUMERATION_MESSAGE = "Please enter the qualified name of the new enumeration";

	public ButtonsPanel(Launcher spoon, UmlModeler umlModeler) {
		super();
		JButton addClass = new JButton(ADD_CLASS);
		JButton addInterface = new JButton(ADD_INTERFACE);
		JButton addEnumeration = new JButton(ADD_ENUMERATION);
		JButton reloadCode = new JButton(RELOAD_CODE);

		addClass.addActionListener(new OnClassAddedListener(umlModeler, ADD_CLASS,
				ADD_CLASS_MESSAGE));
		addInterface.addActionListener(new OnInterfaceAddedListener(umlModeler,
				ADD_INTERFACE, ADD_INTERFACE_MESSAGE));
		addEnumeration.addActionListener(new OnEnumerationAddedListener(umlModeler,
				ADD_ENUMERATION, ADD_ENUMERATION_MESSAGE));
		reloadCode.addActionListener(new OnCodeReloadedListener(spoon, umlModeler.getUml()));

		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);

		add(addClass);
		add(addInterface);
		add(addEnumeration);
		add(reloadCode);
	}
}
