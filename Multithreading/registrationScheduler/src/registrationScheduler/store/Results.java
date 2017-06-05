
package registrationScheduler.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;
import registrationScheduler.util.Student;
/*
 * This class displays result on screen and writes to file.
 * Implements StdoutDisplayInterface and FileDisplayInterface.
 * @author  Nabha Kumthekar.
 * @see     Map
 */
public class Results implements StdoutDisplayInterface,FileDisplayInterface {
	private Map<String,Student> scheduleMap = new HashMap<>();
	
	public Results() {
		Logger.writeMessage("\n ---- In Result Constructor ---- ", Logger.DebugLevel.CONSTRUCTOR);
	}

	/*
	 * This method stores result to map data structure.
	 * @param Map of students schedule result.
	 * @see     Map
	 */
	@Override
	public synchronized void setScheduleTofile(Map<String, Student> map) {
		Logger.writeMessage("\n ----Entry is added to result data structure. ---- "
				, Logger.DebugLevel.RESULT_DATA);
		scheduleMap.putAll(map);
	}	
	/*
	 * This method calculates average preference score and writes result to screen.
	 * @param Map of students schedule result.
	 */
	
	@Override
    public void writeScheduleToScreen() {
		float avf_pref_score = 0;
		int pref_score = 0;
		Logger.writeMessage("\nAllocation Result \n " , Logger.DebugLevel.STORE_DATA);
		for (Map.Entry entry : scheduleMap.entrySet()) {
			pref_score+=scheduleMap.get(entry.getKey()).getPref_score();
			Logger.writeMessage(scheduleMap.get(entry.getKey()).getStudent_id_In() + "\t"
					+ printArray(scheduleMap, entry.getKey().toString()) + " "
					+ "\t\t" + scheduleMap.get(entry.getKey()).getPref_score(),Logger.DebugLevel.STORE_DATA);
		}
		avf_pref_score = pref_score/ scheduleMap.size();
		Logger.writeMessage("Average preference_score is:"+ avf_pref_score, Logger.DebugLevel.NO_OUTPUT);
    }
	/*
	 * This method calculates average preference score and writes result to file.
	 * @param Map of students schedule result.
	 */

	@Override
	public void writeScheduleToFile(File outputTxt_In) {
		String outputTxt = outputTxt_In.toString();
		float avf_pref_score = 0;
		int pref_score = 0;
		try {
			PrintWriter pr = new PrintWriter(new FileOutputStream(new File(
					outputTxt), false));

			for (Map.Entry entry : scheduleMap.entrySet()) {
				pref_score+=scheduleMap.get(entry.getKey()).getPref_score();
				
				pr.println(scheduleMap.get(entry.getKey()).getStudent_id_In() + "\t"
						+ printArray(scheduleMap, entry.getKey().toString()) + " "
						+ "\t\t" + scheduleMap.get(entry.getKey()).getPref_score());
			}
			avf_pref_score = pref_score/ scheduleMap.size();
			pr.println("Average preference_score is:" + avf_pref_score);
			pr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	private String printArray(Map<String, Student> map, String student_id) {
		String courses = null;
		for (int i = 0; i < map.get(student_id).getAllocated_courses().size(); i++) {
			courses = map.get(student_id).getAllocated_courses().toString();
			courses = courses.replaceAll("\\[", "").replaceAll(",", "")
					.replaceAll("\\]", "");
		}
		return courses;
	}
} 


