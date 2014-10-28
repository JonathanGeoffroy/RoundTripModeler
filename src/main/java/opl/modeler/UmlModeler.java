package opl.modeler;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import opl.modeler.model.Uml;
import opl.modeler.panels.ButtonsPanel;
import opl.modeler.panels.UMLContentPanel;
import opl.modeler.panels.UmlPanel;

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

	public UmlModeler(String title, Uml uml) throws HeadlessException {
		super(title);

		ButtonsPanel buttonsPanel = new ButtonsPanel(uml);
		UMLContentPanel umlContentPanel = new UMLContentPanel(this);
		UmlPanel umlPanel = new UmlPanel(uml);

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
}
