package registrationScheduler.studentData;

import java.util.HashMap;
import java.util.Map;

/*
 * This Class contains availability of courses.
 * @author  Nabha Kumthekar.
 * @see     File
 * @see     Scanner
 */
public class courseCounter {

	Map <String,Integer> counterMap = new HashMap<String,Integer>();
	
	public Map<String, Integer> getCounterMap() {
		return counterMap;
	}
	public void setCounterMap(Map<String, Integer> counterMap_In) {
		this.counterMap = counterMap_In;
	}
}
