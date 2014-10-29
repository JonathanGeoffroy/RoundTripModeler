package opl.processors;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opl.modeler.model.Uml;

import org.junit.Test;

import spoon.Launcher;
import spoon.reflect.declaration.CtType;
import spoon.support.compiler.FileSystemFolder;

public class UmlFinderTest {

	@Test
	public void testRunSpoonProcessors() throws Exception {
		String[] classesNames = {"ClassInDefaultPackage", "test.ClassInOtherPackage"};
		String[] interfacesNames = {"InterfaceInDefaultPackage", "test.InterfaceInOtherPackage"};
		String[] enumsNames = {"EnumInDefaultPackage", "test.EnumInOtherPackage"};
		
		String projectPath = "src/test/resources/TestProject/src/";
		
		// Create spoon factory instance
		Launcher spoon = new Launcher();
		spoon.addInputResource(new FileSystemFolder(new File(projectPath)));
		spoon.run();
		Uml uml = new Uml(spoon);
		
		UmlFinder.runSpoonProcessors(spoon, uml);
		
		assertContainsAll(classesNames, uml.getClasses());
		assertContainsAll(interfacesNames, uml.getInterfaces());
		assertContainsAll(enumsNames, uml.getEnumerations());
	}

	private void assertContainsAll(String[] expectedNames,
			List<? extends CtType<?>> types) {
		assertEquals("not the right number of classes", expectedNames.length, types.size());
		Map<String, CtType<?>> mappedTypes = new HashMap<String, CtType<?>>();
		for(CtType<?> t : types) {
			mappedTypes.put(t.getQualifiedName(), t);
		}
		
		for(String name : expectedNames) {
			assertNotNull(name + " class expected but not found", mappedTypes.get(name));
		}
	}

}
