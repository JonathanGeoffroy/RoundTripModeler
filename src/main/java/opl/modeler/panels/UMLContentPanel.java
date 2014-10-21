package opl.modeler.panels;

import opl.modeler.UmlModeler;

import javax.swing.*;
import java.awt.*;

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

	public UMLContentPanel(UmlModeler umlModeler) {
		setPreferredSize(new Dimension(200, 600));

		JTextField name = new JTextField();
		name.setPreferredSize(new Dimension(200, 20));

		JPanel attributes = new JPanel();
		BoxLayout attrsLayout = new BoxLayout(attributes, BoxLayout.Y_AXIS);
		attributes.setLayout(attrsLayout);
		attributes.add(new JLabel("attr 1"));
		attributes.add(new JLabel("attr 2"));

		JPanel methods = new JPanel();
		BoxLayout methodsLayout = new BoxLayout(methods, BoxLayout.Y_AXIS);
		methods.setLayout(methodsLayout);
		methods.add(new JLabel("method 1"));
		methods.add(new JLabel("method 2"));

		JPanel components = new JPanel();
		BoxLayout componentsLayout = new BoxLayout(components, BoxLayout.Y_AXIS);
		components.setLayout(componentsLayout);
		components.add(attributes);
		components.add(methods);

		this.add(name);
		this.add(components);
	}
}