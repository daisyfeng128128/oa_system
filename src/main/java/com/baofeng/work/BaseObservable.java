package com.baofeng.work;

import java.util.Observable;

public class BaseObservable extends Observable {

	@Override
	public synchronized void setChanged() {
		super.setChanged();
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	public void notifyObservers(Object args) {
		super.notifyObservers(args);
	}
}
