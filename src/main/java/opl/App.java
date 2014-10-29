package opl;

import java.io.File;

import opl.modeler.UmlModeler;
import opl.modeler.model.Uml;
import opl.processors.UmlFinder;
import spoon.Launcher;
import spoon.support.compiler.FileSystemFolder;

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
		String projectPath = "src/test/resources/TestProject/src/";
		
		// Create spoon factory instance
		Launcher spoon = new Launcher();
		spoon.addInputResource(new FileSystemFolder(new File(projectPath)));
		spoon.run();
		Uml uml = new Uml(spoon);
		
		UmlFinder.runSpoonProcessors(spoon, uml);
		new UmlModeler("Round Trip Modeling", spoon, uml).setVisible(true);
	}
}
