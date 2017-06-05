package registrationScheduler.objectPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.util.Logger;
/*
 * This Class contains availability of courses.
 * @author  Nabha Kumthekar.
 * @see     Map`
 * @see     Scanner
 */
public class Course {
	Map <String,Integer> counterMap = new HashMap<String,Integer>();
	
	public Course() {
		Logger.writeMessage("\n ---- In Course Constructor ---- ", Logger.DebugLevel.CONSTRUCTOR);
		counterMap.put("A", 0);
		counterMap.put("B", 0);
		counterMap.put("C", 0);
		counterMap.put("D", 0);
		counterMap.put("E", 0);
		counterMap.put("F", 0);
		counterMap.put("G", 0);
		counterMap.put("H", 0);
	}	
	/*
	 * This Method return map of courses.
	 * @return counterMap.
	 */
	public Map<String, Integer> getCounterMap() {
		return counterMap;
	}
	/*
	 * This Method replaces course availability counter.
	 */
	public void setCounterMap(String preference, int counter) {
		counterMap.replace(preference,counter);
	}
	
	/*
	 * This Method checks which courses are available and returns list of them.
	 * @return list of available_courses.
	 */
	public List<String> getAvailableCourse(){
		List<String> available_courses = new ArrayList<String>();
		for (Map.Entry entry : counterMap.entrySet()) {
			if(counterMap.get(entry.getKey()) < 60 ){
				available_courses.add(entry.getKey().toString()) ;
			}
		}
		return available_courses;
	}
}
