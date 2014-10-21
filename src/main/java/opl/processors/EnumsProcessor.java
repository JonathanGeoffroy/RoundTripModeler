package opl.processors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtEnum;

/**
 * use Spoon library to find all enumerations of the project<br>
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class EnumsProcessor extends AbstractProcessor<CtEnum<?>> {
    private List<CtEnum<?>> elements;

    public EnumsProcessor() {
        this.elements = new ArrayList<CtEnum<?>>();
    }

    public void process(CtEnum<?> element) {
        elements.add(element);
    }

    public List<CtEnum<?>> getElements() {
        return elements;
    }
}
