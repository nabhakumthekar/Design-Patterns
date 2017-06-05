package registrationScheduler.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.objectPool.ObjectPool;

/*
 * This class creates sorted list of students according to their course preferences 
 * and allocates courses according to availability.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     Map
 */
public class CourseAllocation {
	String[] stud_pref = null;
	String[] stud_ad = null;
	Map<String, Student> map = new LinkedHashMap<String, Student>();
	List<String> preferenceList = null;
	private ObjectPool objectPoolInst = null;

	public CourseAllocation() {
		Logger.writeMessage("\n ---- In Cousre Allocation Constructor ---- ",
				Logger.DebugLevel.CONSTRUCTOR);
		objectPoolInst = ObjectPool.getInstance();
	}

	/*
	 * This Method creates map students with student_id as key.
	 * @param String of records from scanned files.
	 * @throws Exception
	 * @return nothing.
	 */
	public synchronized void scheduleFile(String record) {
		try {
			Student student = new Student();
			preferenceList = new ArrayList<String>();
			stud_pref = record.split("\\s+");
			student.setStudent_id_In(stud_pref[0]);
			student.setCourse_1(stud_pref[1]);
			student.setCourse_2(stud_pref[2]);
			student.setCourse_3(stud_pref[3]);
			student.setCourse_4(stud_pref[4]);
			student.setCourse_5(stud_pref[5]);
			preferenceList.add(student.getCourse_1());
			preferenceList.add(student.getCourse_2());
			preferenceList.add(student.getCourse_3());
			preferenceList.add(student.getCourse_4());
			preferenceList.add(student.getCourse_5());
			student.setCourse_pref_In(preferenceList);
			map.put(student.getStudent_id_In(), student);
			allocatePrefCourse(map, student);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * This Method allocates courses according to preferences.
	 * @param Map map of student_id and preferences.
	 * @param Student data object.
	 * @throws Exception
	 * @return nothing.
	 */
	private void allocatePrefCourse(Map<String, Student> map, Student student) {
		try {
			String get_preference = null;
			String student_id = map.get(student.getStudent_id_In())
					.getStudent_id_In();

			for (int i = 0; i < map.get(student_id).getCourse_pref_In().size(); i++) {
				get_preference = map.get(student_id).getCourse_pref_In().get(i);
				switch (get_preference) {
				case "A":
					allocation("A", student_id, map, i);
					break;
				case "B":
					allocation("B", student_id, map, i);
					break;
				case "C":
					allocation("C", student_id, map, i);
					break;
				case "D":
					allocation("D", student_id, map, i);
					break;
				case "E":
					allocation("E", student_id, map, i);
					break;
				case "F":
					allocation("F", student_id, map, i);
					break;
				case "G":
					allocation("G", student_id, map, i);
					break;
				case "H":
					allocation("H", student_id, map, i);
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
	 * This Method allocates courses if course is available else allocates "-" as null value.
	 * @param String preference.
	 * @param String student_id.
	 * @param Map map of student_id and preferences.
	 * @param Integer index.
	 * @throws Exception
	 * @return nothing.
	 */
    private void allocation(String get_preference, String student_id,
                            Map<String, Student> map, int index) {
        try{
            int capacity = objectPoolInst.availability(get_preference);
            if (capacity < 60) {
                if(!map.get(student_id).getAllocated_courses().contains(get_preference)){
                    map.get(student_id).setAllocated_courses(get_preference);
                    objectPoolInst.updateAvailability(get_preference, 1);
                }else{
                    map.get(student_id).setAllocated_courses("-");
                }
                calculatePrefScore(index, student_id);
                
            } else {
                map.get(student_id).setAllocated_courses("-");
                calculatePrefScore(index, student_id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
        }
    }
	/*
	 * This Method calculates preference score form 6 to 2 according to preferences.
	 * @param String student_id.
	 * @param Integer index.
	 * @throws Exception
	 * @return nothing.
	 */
	private void calculatePrefScore(int index, String student_id) {
		int pref_score = map.get(student_id).getPref_score();
		switch (index) {
		case 0:
			if (map.get(student_id).getAllocated_courses().get(index) == "-") {
				pref_score += 0;
				map.get(student_id).setPref_score(pref_score);
			} else {
				pref_score += 6;
				map.get(student_id).setPref_score(pref_score);
			}
			break;
		case 1:
			if (map.get(student_id).getAllocated_courses().get(index) == "-") {
				pref_score += 0;
				map.get(student_id).setPref_score(pref_score);
			} else {
				pref_score += 5;
				map.get(student_id).setPref_score(pref_score);
			}
			break;
		case 2:
			if (map.get(student_id).getAllocated_courses().get(index) == "-") {
				pref_score += 0;
				map.get(student_id).setPref_score(pref_score);
			} else {
				pref_score += 4;
				map.get(student_id).setPref_score(pref_score);
			}
			break;
		case 3:
			if (map.get(student_id).getAllocated_courses().get(index) == "-") {
				pref_score += 0;
				map.get(student_id).setPref_score(pref_score);
			} else {
				pref_score += 3;
				map.get(student_id).setPref_score(pref_score);
			}
			break;
		case 4:
			if (map.get(student_id).getAllocated_courses().get(index) == "-") {
				pref_score += 0;
				map.get(student_id).setPref_score(pref_score);
			} else {
				pref_score += 2;
				map.get(student_id).setPref_score(pref_score);
			}
			break;
		default:
			break;
		}

	}
	/*
	 * This Method allocates courses not preferred by students.
	 * @throws Exception
	 * @return nothing.
	 */
	public synchronized void allocatNotPrefCourse() {
		int count = 0;
		try {
			for (Map.Entry entry : map.entrySet()) {
				String student_id = map.get(entry.getKey()).getStudent_id_In();
				List available_course = objectPoolInst.checkAvailability();
				count = java.util.Collections.frequency(map.get(student_id)
						.getAllocated_courses(), "-");
				while (count != 0) {
					for (int i = 0; i < available_course.size(); i++) {
						if ((map.get(student_id).getAllocated_courses()
								.contains("-"))
								&& map.get(student_id).getAdd() == 0
								&& map.get(student_id).getDrop() == 0) {
							if (!map.get(student_id).getAllocated_courses()
									.contains(available_course.get(i))) {
								int index = (map.get(student_id)
										.getAllocated_courses().indexOf("-"));
								map.get(student_id)
										.getAllocated_courses()
										.set(index,
												available_course.get(i)
														.toString());
								objectPoolInst.updateAvailability(
										available_course.get(i).toString(), 1);
								calculateNotPrefScore(map, student_id);
							}
						}
					}
					count--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	/*
	 * This Method calculates preference score for not preferred course. 
	 * 1 is for not preferred score and 0 for null value.
	 * @param Map map of student_id and students object.
	 * @param String student_id
	 * @throws Exception
	 * @return nothing.
	 */
	private synchronized void calculateNotPrefScore(Map<String, Student> map,
			String student_id) {
		try{
			int pref_score = map.get(student_id).getPref_score();
			map.get(student_id).setPref_score(pref_score + 1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	/*
	 * This Method stores add and drop requests into map.
	 * @param String file.
	 * @throws NumberFormatException.
	 * @return map.
	 */
	public synchronized Map<String, Student> addDropCourse(String file2) {
		stud_ad = file2.split("\\s+");
		String student_id = stud_ad[0];
		int status = 0;
		Student student = map.get(student_id);
		try {
			status = Integer.parseInt(stud_ad[1]);
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			System.exit(1);
		} finally {
		}
		if (status == 1) {
			for (int i = 2; i < stud_ad.length; i++) {
				student.setAdd_courses(stud_ad[i]);
			}
		} else {
			for (int i = 2; i < stud_ad.length; i++) {
				student.setDrop_courses(stud_ad[i]);
			}
		}
		map.put(student_id, student);
		addDropNewCourses(map, student_id);
		return map;
	}
	/*
	 * This Method adds or drops courses according to student requests.	 
	 * @param String student_id.
	 * @param Map map of student_id and student object.
	 */
	private synchronized void addDropNewCourses(Map<String, Student> map,
			String student_id) {
		if (!map.get(student_id).getDrop_courses().isEmpty()
				&& map.get(student_id).getDrop() == 0) {
			for (int i = 0; i < map.get(student_id).getDrop_courses().size(); i++) {
				String course = map.get(student_id).getDrop_courses().get(i);
				if (map.get(student_id).getAllocated_courses().contains(course)) {
					int index = map.get(student_id).getAllocated_courses()
							.indexOf(course);
					map.get(student_id).getAllocated_courses().set(index, "-");
					objectPoolInst.updateAvailability(course, 0);
				}
			}
			map.get(student_id).setDrop(1);
		}

		if (!map.get(student_id).getAdd_courses().isEmpty()
				&& map.get(student_id).getAdd() == 0) {
			for (int i = 0; i < map.get(student_id).getAdd_courses().size(); i++) {
				String course = map.get(student_id).getAdd_courses().get(i);
				if (!map.get(student_id).getAllocated_courses()
						.contains(course)) {
					if (objectPoolInst.availability(course) < 60) {
						map.get(student_id).setAllocated_courses(course);
						map.get(student_id).setPref_score(
								map.get(student_id).getPref_score() + 1);
						objectPoolInst.updateAvailability(course, 1);
					}
				}
			}
			map.get(student_id).setAdd(1);
		}
	}
}
