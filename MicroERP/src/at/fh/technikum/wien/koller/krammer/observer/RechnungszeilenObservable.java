package at.fh.technikum.wien.koller.krammer.observer;

import java.util.ArrayList;


public abstract class RechnungszeilenObservable {
protected ArrayList<RechnungszeilenObserver> observers = new ArrayList<RechnungszeilenObserver>();
	
	public void MeldeAn(RechnungszeilenObserver ro) {
		observers.add(ro);
	}
	
	public void MeldeAb(RechnungszeilenObserver ro) {
		int pos = observers.indexOf(ro);
		
		if(pos >= 0) {
			observers.remove(pos);
		}
	}
	
	public void clear() {
		observers.clear();
		observers = new ArrayList<RechnungszeilenObserver>();
	}
	
	public abstract void Benachrichtige();
}
