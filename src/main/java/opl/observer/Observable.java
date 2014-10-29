package opl.observer;

import java.util.ArrayList;
import java.util.List;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtInterface;

/**
 * A special implementation of <strong>Observable</strong>, able to call
 * observers when:
 * <ul>
 * <li>a new class is created</li>
 * <li>a new interface is created</li>
 * <li>a new enumeration is created</li>
 * <li>the source code is reloaded</li>
 * </ul>
 * 
 * @author Célia Cacciatore, Jonathan Geoffroy
 *
 */
public class Observable {
	private List<Observer> observers;

	public Observable() {
		observers = new ArrayList<Observer>();
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	protected void notifyClassAdded(CtClass<?> ctClass) {
		for (Observer o : observers) {
			o.onClassAdded(ctClass);
		}
	}

	protected void notifyInterfaceAdded(CtInterface<?> ctInterface) {
		for (Observer o : observers) {
			o.onInterfaceAdded(ctInterface);
		}
	}

	protected void notifyEnumAdded(CtEnum<?> ctEnum) {
		for (Observer o : observers) {
			o.onEnumAdded(ctEnum);
		}
	}

	public void onCodeReloaded() {
		for (Observer o : observers) {
			o.onCodeReloaded();
		}
	}
}
