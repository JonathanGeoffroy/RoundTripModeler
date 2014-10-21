package opl;

import opl.modeler.UmlModeler;
import opl.modeler.model.Uml;
import opl.processors.UmlFinder;

/**
 * Entry Point of the software<br>
 * Run Spoon library to find all CtType (i.e. classes, interfaces, enumerations)
 * of the project
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		Uml uml = UmlFinder.runSpoonProcessors("src/main/java");
		new UmlModeler("Round Trip Modeling", uml).setVisible(true);
	}
}
