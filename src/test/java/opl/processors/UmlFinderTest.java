package opl.processors;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opl.modeler.model.Uml;

import org.junit.Test;

import spoon.reflect.declaration.CtType;

public class UmlFinderTest {

	@Test
	public void testRunSpoonProcessors() throws Exception {
		String projectName = "src/test/resources/TestProject/src/";
		String[] classesNames = {"ClassInDefaultPackage", "test.ClassInOtherPackage"};
		String[] interfacesNames = {"InterfaceInDefaultPackage", "test.InterfaceInOtherPackage"};
		String[] enumsNames = {"EnumInDefaultPackage", "test.EnumInOtherPackage"};
		
		Uml uml = UmlFinder.runSpoonProcessors(projectName);
		
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
