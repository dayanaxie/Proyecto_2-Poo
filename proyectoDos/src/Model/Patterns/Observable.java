package Model.Patterns;

import java.util.*;

public abstract class Observable {
    private ArrayList<IObserver> observers;

    public Observable() {
		observers = new ArrayList<IObserver>();
	}

    public void addObserver(IObserver pObservable) {
		observers.add(pObservable);
	}

    public void removeObserver(IObserver pObservable) {
		observers.remove(pObservable);
	}
	
	public void notifyObservers(Object pValue) {
		for(IObserver observer : observers) {
			observer.update(this, pValue);
		}
	}
    
}
