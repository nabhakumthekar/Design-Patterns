package genericSerDeser.strategy;


import genericSerDeser.store.FileDisplayInterface;

import java.util.ArrayList;
import java.util.Map;

public interface SerStrategy {
	public void do_strategy(ArrayList<Object> objectTypes);
}
