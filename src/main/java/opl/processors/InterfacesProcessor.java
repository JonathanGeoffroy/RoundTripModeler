package opl.processors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtInterface;

/**
 * use Spoon library to find all interfaces of the project<br>
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class InterfacesProcessor  extends AbstractProcessor<CtInterface<?>> {
    private List<CtInterface<?>> elements;

    public InterfacesProcessor() {
        this.elements = new ArrayList<CtInterface<?>>();
    }

    public void process(CtInterface<?> element) {
        elements.add(element);
    }

    public List<CtInterface<?>> getElements() {
        return elements;
    }
}