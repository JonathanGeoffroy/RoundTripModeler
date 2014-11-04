package opl;

import java.io.File;

import opl.modeler.UmlModeler;
import opl.modeler.model.Uml;
import opl.processors.readers.UmlFinder;
import spoon.Launcher;
import spoon.support.compiler.FileSystemFolder;

/**
 * Entry Point of the software<br>
 * Run Spoon library to find all CtType (i.e. classes, interfaces, enumerations)
 * of the project and display them in a UML modeler.
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			usage();
		}
		String projectPath = args[0];
		
		// Create spoon instance
		Launcher spoon = new Launcher();
		// Change the spoon output directory: use the analyzed project
		String[] setOutput = {"-o", projectPath};
		spoon.setArgs(setOutput);
		spoon.addInputResource(new FileSystemFolder(new File(projectPath)));
		spoon.run();
		
		// Load the project into spoon
		Uml uml = new Uml(spoon);
		UmlFinder.runSpoonProcessors(spoon, uml);
		new UmlModeler("Round Trip Modeling", spoon, uml).setVisible(true);
	}

	/**
	 * Print the software usage and exit
	 */
	private static void usage() {
		System.out.println("usage: java -jar RoundTripModel <projectName>");
		System.exit(-1); // Exit error
	}
}
