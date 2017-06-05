package registrationScheduler.allocation;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import registrationScheduler.studentData.Student;
import registrationScheduler.util.fileHandling;

/*
 * This class creates sorted list of students according to their registration time 
 * and allocates courses according to availability.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     Map
 * @see 	Priority Queue
 */
public class courseAllocation {
	String[] reg_time = null;
	String[] stud_pref = null;

	PriorityQueue<Student> priorityQueue = new PriorityQueue<>(idComparator);

	private static Comparator<Student> idComparator = new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			return s1.getReg_time_In() - s2.getReg_time_In();
		}
	};

	/*
	 * This Method is used to sort student_id according to time stamp using
	 * priority queue.
	 * 
	 * @param Scanner input from scanned files.
	 * 
	 * @throws NumberFormatException
	 * 
	 * @return nothing.
	 */
	public void scheduleFile(Scanner sc_time, Scanner sc_pref) {
		Student s = new Student();
		reg_time = sc_time.nextLine().split("\\s+");
		stud_pref = sc_pref.nextLine().split("\\s+");
		s.setStudent_id_In(reg_time[0]);
		try {
			s.setReg_time_In(Integer.parseInt(reg_time[1]));
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			System.exit(1);
		} finally {
		}
		s.setCourse_1(stud_pref[1]);
		s.setCourse_2(stud_pref[2]);
		s.setCourse_3(stud_pref[3]);
		s.setCourse_4(stud_pref[4]);
		priorityQueue.add(s);
	}

	/*
	 * This Method is used to create map of with key student_id and value
	 * preferred courses,time stamp and map of courses with their capacity.
	 * 
	 * @param Output File to write output.
	 * 
	 * @throws Exception.
	 * 
	 * @return nothing.
	 */
	public void sortedScheduleFile(File outputFile) {
		Map<String, Student> map = new LinkedHashMap<String, Student>();
		Student student = new Student();
		try {
			while (!priorityQueue.isEmpty()) {
				student = priorityQueue.poll();
				List<String> list = new ArrayList<String>();
				list.add(student.getCourse_1());
				list.add(student.getCourse_2());
				list.add(student.getCourse_3());
				list.add(student.getCourse_4());
				student.setCourse_pref_In(list);
				map.put(student.getStudent_id_In(), student);
			}
			int i;
			Map<String, Integer> counterMap = new HashMap<String, Integer>();
			Boolean first_iti = true;
			counterMap.put("A", 0);
			counterMap.put("B", 0);
			counterMap.put("C", 0);
			counterMap.put("D", 0);
			counterMap.put("E", 0);
			counterMap.put("F", 0);
			counterMap.put("G", 0);
			counterMap.put("H", 0);

			for (i = 0; i < 4; i++) {
				allocateCourse(student, map, counterMap, i, first_iti);
			}
			first_iti = false;
			for (i = 0; i < 4; i++) {
				allocateCourse(student, map, counterMap, i, first_iti);
			}
			float avf_pref_score = calculateAvgPrefScore(map);
			fileHandling fh = new fileHandling();
			fh.fileWriting(map, avf_pref_score, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method is used to calculate preference score according to allocation
	 * of courses.
	 * 
	 * @param Map map of student_id and preferences.
	 * 
	 * @param String of student_id.
	 * 
	 * @param String of student preference.
	 * 
	 * @throws Exception.
	 * 
	 * @return nothing.
	 */

	private void calculatePrefScore(String get_preference,
			Map<String, Student> map, String student_id) {
		int index = map.get(student_id).getCourse_pref_In()
				.indexOf(get_preference);
		try {
			if (index == 0) {
				map.get(student_id).setPref_score(index + 1);
			} else if (index == 1) {
				int inc_index = map.get(student_id).getPref_score();
				map.get(student_id).setPref_score(inc_index + 2);
			} else if (index == 2) {
				int inc_index = map.get(student_id).getPref_score();
				map.get(student_id).setPref_score(inc_index + 3);
			} else if (index == 3) {
				int inc_index = map.get(student_id).getPref_score();
				map.get(student_id).setPref_score(inc_index + 4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method is used to calculate average preference score from preference
	 * score. It allocates score if any course is not provided.
	 * 
	 * @param Map map of student_id and preferences.
	 * 
	 * @return average preference score.
	 */
	private float calculateAvgPrefScore(Map<String, Student> map) {
		float avg_pref_score = 0;
		float total_pref_score = 0;
		for (Map.Entry entry : map.entrySet()) {
			if ((map.get(entry.getKey()).getAllocated_courses()).contains("-")) {
				int count = java.util.Collections.frequency(
						map.get(entry.getKey()).getAllocated_courses(), "-");
				int score = map.get(entry.getKey()).getPref_score()
						+ (6 * count);
				map.get(entry.getKey()).setPref_score(score);
			}

			total_pref_score += map.get(entry.getKey()).getPref_score();
		}
		avg_pref_score = total_pref_score / 50;
		return avg_pref_score;
	}

	/*
	 * This Method makes use of allocation method.
	 * 
	 * @param Student object with student information.
	 * 
	 * @param Map of student_id and course preference.
	 * 
	 * @param Interger flag to keep track of iterations.
	 * 
	 * @param Map of available courses and their count.
	 * 
	 * @return nothing.
	 * 
	 * @throws Exception.
	 */
	private void allocateCourse(Student student, Map<String, Student> map,
			Map<String, Integer> counterMap, int i, Boolean first_iti) {
		String get_preference = null;
		try {
			for (Map.Entry entry : map.entrySet()) {
				get_preference = map.get(entry.getKey()).getCourse_pref_In()
						.get(i);
				String student_id = map.get(entry.getKey()).getStudent_id_In();
				switch (get_preference) {
				case "A":
					allocation("A", student_id, map, counterMap, first_iti, i);
					break;
				case "B":
					allocation("B", student_id, map, counterMap, first_iti, i);
					break;
				case "C":
					allocation("C", student_id, map, counterMap, first_iti, i);
					break;
				case "D":
					allocation("D", student_id, map, counterMap, first_iti, i);
					break;
				case "E":
					allocation("E", student_id, map, counterMap, first_iti, i);
					break;
				case "F":
					allocation("F", student_id, map, counterMap, first_iti, i);
					break;
				case "G":
					allocation("G", student_id, map, counterMap, first_iti, i);
					break;
				case "H":
					allocation("H", student_id, map, counterMap, first_iti, i);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method is used to allocate courses according to preference of
	 * student. If course is not available at the moment then it allocates '-'
	 * for first allocation iteration. In second allocation iteration is student
	 * has '-' in its allocated courses it uses allocateAlternateCourse method.
	 * 
	 * @param String of course preference.
	 * 
	 * @param Map of student_id and course preference.
	 * 
	 * @param Interger flag to keep track of iterations.
	 * 
	 * @param Map of available courses and their count.
	 * 
	 * @param Integer index i.
	 * 
	 * @return nothing.
	 * 
	 * @throws Exception.
	 */
	private void allocation(String course_pref, String student_id,
			Map<String, Student> map, Map<String, Integer> counterMap,
			Boolean first_iti, int i) {
		try {
			if (counterMap.get(course_pref) < 20
					&& map.get(student_id).getAllocated_courses().size() < 4) {
				int courseCounter = counterMap.get(course_pref);
				counterMap.replace(course_pref, ++courseCounter);
				map.get(student_id).setAllocated_courses(course_pref);
				calculatePrefScore(course_pref, map, student_id);
			} else {
				if (i < 4 && first_iti) {
					map.get(student_id).setAllocated_courses("-");
				} else if (map.get(student_id).getAllocated_courses()
						.indexOf("-") == i) {
					allocateAlternateCourse(student_id, map, counterMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method is used to allocate alternate course if preferred course is
	 * full. This method uses calculateAltPrefScore to calculate score for
	 * alternate course.
	 * 
	 * @param String of Student_id.
	 * 
	 * @param Map of student_id and course preference.
	 * 
	 * @param Map of available courses and their count.
	 * 
	 * @return nothing.
	 * 
	 * @throws Exception.
	 */
	private void allocateAlternateCourse(String Student_id,
			Map<String, Student> map, Map<String, Integer> counterMap) {
		int score = 0;
		try {
			for (Map.Entry entry : counterMap.entrySet()) {
				if (Integer.parseInt(entry.getValue().toString()) < 20
						&& map.get(Student_id)
								.getAllocated_courses().contains("-")) {
					switch (entry.getKey().toString()) {
					case "A":
						calculateAltPrefScore(counterMap, map, Student_id, "A");
						break;
					case "B":
						calculateAltPrefScore(counterMap, map, Student_id, "B");
						break;
					case "C":
						calculateAltPrefScore(counterMap, map, Student_id, "C");
						break;
					case "D":
						calculateAltPrefScore(counterMap, map, Student_id, "D");
						break;

					case "E":
						calculateAltPrefScore(counterMap, map, Student_id, "E");
						break;

					case "F":
						calculateAltPrefScore(counterMap, map, Student_id, "F");
						break;

					case "G":
						calculateAltPrefScore(counterMap, map, Student_id, "G");
						break;

					case "H":
						calculateAltPrefScore(counterMap, map, Student_id, "H");
						break;

					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method is used to allocate calculate score for alternate course.
	 * 
	 * @param String of Student_id.
	 * 
	 * @param String of Course name.
	 * 
	 * @param Map of student_id and course preference.
	 * 
	 * @param Map of available courses and their count.
	 * 
	 * @return nothing.
	 * 
	 * @throws Exception.
	 */
	private void calculateAltPrefScore(Map<String, Integer> counterMap,
			Map<String, Student> map, String student_id, String course_name) {
		int score = map.get(student_id).getPref_score();
		int index = (map.get(student_id).getAllocated_courses().indexOf("-"));
		int counter = counterMap.get(course_name);
		try {
			counterMap.replace(course_name, ++counter);
			map.get(student_id).getAllocated_courses().set(index, course_name);
			map.get(student_id).setPref_score(score + 5);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}