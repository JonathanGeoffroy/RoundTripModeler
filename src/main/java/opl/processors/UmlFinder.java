package opl.processors;

import java.io.File;
import java.util.List;

import opl.modeler.model.Uml;
import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;
import spoon.support.compiler.FileSystemFolder;

/**
 * Run Spoon library to find all CtType (i.e. classes, interfaces, enumerations)
 * of the project
 * 
 * @link{ClassesProcessor, EnumsProcessor, InterfacesProcessor}
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public abstract class UmlFinder {

	/**
	 * Run Spoon library to find all CtType (i.e. classes, interfaces,
	 * enumerations) of the project
	 * 
	 * @return uml containing each CtType of the project
	 * @throws Exception
	 *             Spoon exceptions
	 */
	public static Uml runSpoonProcessors(String projectPath) throws Exception {
		// Create spoon factory instance
		Launcher spoon = new Launcher();
		spoon.addInputResource(new FileSystemFolder(new File(projectPath)));
		spoon.run();
		Factory factory = spoon.getFactory();

		// Create a queue containing all processors to run
		ProcessingManager processingManager = new QueueProcessingManager(
				factory);
		ClassesProcessor classesProcessor = new ClassesProcessor();
		InterfacesProcessor interfacesProcessor = new InterfacesProcessor();
		EnumsProcessor enumProcessor = new EnumsProcessor();
		processingManager.addProcessor(classesProcessor);
		processingManager.addProcessor(interfacesProcessor);
		processingManager.addProcessor(enumProcessor);
		processingManager.process();

		// Get all CtType
		List<CtClass<?>> classes = classesProcessor.getElements();
		List<CtInterface<?>> interfaces = interfacesProcessor.getElements();
		List<CtEnum<?>> enums = enumProcessor.getElements();

		/*
		 * Little Hack here: remove all enumerations contained by classes list
		 * Reason: CtEnum spoon implementation directly extends from CtClass,
		 * So when you want to find all CtClass, you also find all CtEnum.
		 * FIXME: Does a better filter exist to find Classes but not Enumerations
		 */
		classes.removeAll(enums);

		return new Uml(spoon, classes, interfaces, enums);
	}

}
