package opl.processors.readers;

import spoon.reflect.declaration.CtEnum;

/**
 * Use Spoon library to find all enumerations of the project<br>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class EnumsProcessor extends DisplayProcessor<CtEnum<?>> {
    
    public void process(CtEnum<?> element) {
        elements.add(element);
    }
    
}
