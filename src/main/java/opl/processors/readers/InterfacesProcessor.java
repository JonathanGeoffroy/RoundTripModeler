package opl.processors.readers;

import spoon.reflect.declaration.CtInterface;

/**
 * use Spoon library to find all interfaces of the project<br>
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class InterfacesProcessor  extends DisplayProcessor<CtInterface<?>> {
    public void process(CtInterface<?> element) {
        elements.add(element);
    }
}