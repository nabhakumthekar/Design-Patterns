package spreadsheetUpdates.observer;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import spreadsheetUpdates.util.Logger;

/*
 * This class implements  detect cycles.
 * @author  Nabha Kumthekar.
 */
public class DetectCycle {

	 private static Set<String> set;
	 
	 public DetectCycle() {
			Logger.writeMessage("\n ---- In Detect Cycle Constructor ---- "
					, Logger.DebugLevel.CONSTRUCTOR);
	}
	 /*
	  * This method inserts cell into Linked Hash Set and checks if the upcoming value already present in set.
	  * @return status of cycle.
	  */
	public static boolean insertSet(Cell set_In){
		 boolean status = set.add(set_In.getName());
		 if(status == false){
			 Iterator it = set.iterator();
			 String me = (String) it.next();
			 if(me.equals(set_In.getName())){
				 
			 }else{
				 status = true;
			 }
		 }
		 return status;
	 }
	 public static void startDetecting(){
		 set = new LinkedHashSet<String>();
	 }
	 
	 public static void endDetecting(){
		 set = null;
	 }
}
