package registrationScheduler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import registrationScheduler.studentData.*;
import registrationScheduler.allocation.courseAllocation;

/*
 * This Class contains fileReading and fileWriting methods.
 * @author  Nabha Kumthekar.
 * @see     File
 * @see     Scanner
 */
public class fileHandling {

    /*
     * This method is used to read input from files.
     * @throws  FileNotFoundException.
     * @return  nothing.
     */
	public void fileReading(File inputFile1, File inputFile2, File outputFile) {
		Scanner sc_time = null;
		Scanner sc_pref = null;
		String preference = inputFile1.toString();
		String regTime = inputFile2.toString();
		courseAllocation ca = new courseAllocation();

		try {
			sc_time = new Scanner(new FileReader(regTime));
			sc_pref = new Scanner(new FileReader(preference));
			int read50 = 0;
			if (sc_time.hasNext() && sc_pref.hasNext()) {
				while (sc_time.hasNext() && sc_pref.hasNext()) {
					ca.scheduleFile(sc_time, sc_pref);
					read50++;
				}
				if (read50 == 50) {
					ca.sortedScheduleFile(outputFile);
				} else {
					sc_time.close();
					sc_pref.close();
					System.err.println("File has less/more that 50 entries...");
					System.exit(1);
				}
			} else {
				sc_time.close();
				sc_pref.close();
				System.err.println("File is empty. Exiting...");
				System.exit(1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc_time.close();
			sc_pref.close();
			System.exit(0);
		}
	}
    /*
     * This method is used to write output tp files.
     * @param   Map for student information.
     * @param   Float average preference score.
     * @param   File output file name.
     * @throws  FileNotFoundException.
     * @return  nothing.
     */
	public void fileWriting(Map<String, Student> map, float avf_pref_score,
			File outputFile) {
		String oFile = outputFile.toString();
        PrintWriter pr = null;
		try {
            pr = new PrintWriter(new FileOutputStream(new File(oFile),false));
			
			for (Map.Entry entry : map.entrySet()) {
				pr.println(map.get(entry.getKey()).getStudent_id_In() + "\t"
						+ printArray(map, entry.getKey().toString()) + " "
						+ "\t" + map.get(entry.getKey()).getPref_score());
			}
			pr.println("Average preference_score is:" + avf_pref_score);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }finally{
            pr.close();
        }
	}
    /*
     * This method is used to convert array of allocated courses to string form.
     * @param  Map for student allocated courses.
     * @param   String studeny_id
     * @return  courses.
     */
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
