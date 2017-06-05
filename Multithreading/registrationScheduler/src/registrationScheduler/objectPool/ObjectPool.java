package registrationScheduler.objectPool;

import java.util.List;

import registrationScheduler.util.Logger;

/*
 * This class implements Object Pool Design Pattern.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     Map
 */

public class ObjectPool {
	
	private ObjectPool() {
		Logger.writeMessage("\n ---- In Object Pool Constructor ----"
				, Logger.DebugLevel.CONSTRUCTOR);
	}

	private static ObjectPool objectPool = null;
	Course course = new Course();
	
	/*
	 * Object Pool implemented as a singleton pattern. 
	 * @see     Singleton Pattern
	 * @returns     unique instance of objectPool.
	 */
	
	public synchronized static ObjectPool getInstance(){
		if( objectPool == null)
		{
			objectPool = new ObjectPool();
		}
		return objectPool;
	}
	/*
	 * This method returns counter of` availability of preferred course. 
	 * @returns     Integer course counter.
	 */
	public synchronized  int availability(String preference) {
		return course.getCounterMap().get(preference);
	}
	
	/*
	 * This method updates availability of courses according  to add-drop request.\
	 * @param String preference
	 * @param Integer status(0-drop , 1-add).
	 * @returns     Integer course counter.
	 */
	public synchronized void updateAvailability(String preference, int status){
		if(status == 1){
			int count = course.getCounterMap().get(preference);
			course.setCounterMap(preference, ++count);
		}else{
			int count = course.getCounterMap().get(preference);
			if(count != 0){
				course.setCounterMap(preference, --count);
			}
		}	
	}
	/*
	 * This method return list of available courses.
	 * @returns     list of  available_courses.
	 */
	public synchronized List<String> checkAvailability(){
		List<String> available_courses = course.getAvailableCourse();
		return available_courses;
	}
}
