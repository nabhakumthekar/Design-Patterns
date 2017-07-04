package spreadsheetUpdates.observer;

import java.util.Observer;


public interface SubjectInter {
	public void registerObserver(Cell o); 
	public void removeObserver(Cell o); 
	public void notifyObservers();
}
