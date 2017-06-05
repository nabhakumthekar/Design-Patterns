package registrationScheduler.util;

import java.util.ArrayList;
import java.util.List;
/*
 * This Class contains student information such as id, preferred course,preference score and allocated courses.
 * @author  Nabha Kumthekar.
 */
public class Student {
	private String student_id;
	List <String> course_pref = new ArrayList<String>();
	List <String> allocated_courses = new ArrayList<String>();
	List <String> drop_courses = new ArrayList<String>();
	List <String> add_courses = new ArrayList<String>();
	private String course_1 = null;
	private String course_2 = null;
	private String course_3 = null;
	private String course_4 = null;
	private String course_5 = null;
	int add = 0;
	int drop = 0;
	public int getAdd() {
		return add;
	}
	public void setAdd(int add) {
		this.add = add;
	}
	public int getDrop() {
		return drop;
	}
	public void setDrop(int drop) {
		this.drop = drop;
	}
	
	
	public Student() {
		Logger.writeMessage("\n ---- In Student Data Constructor ----", Logger.DebugLevel.CONSTRUCTOR);
	}
	public Student(int reg_time_In, String student_id_In, List<String> course_pre) {
		super();
		this.student_id = student_id_In;
		this.course_pref = course_pre;
	}
	
	
	public List<String> getDrop_courses() {
		return drop_courses;
	}
	public void setDrop_courses(String drop_courses_In) {
		drop_courses.add(drop_courses_In);
	}
	public List<String> getAdd_courses() {
		return add_courses;
	}
	public void setAdd_courses(String add_courses_In) {
		add_courses.add(add_courses_In);
	}

	public String getCourse_5() {
		return course_5;
	}
	public void setCourse_5(String course_5) {
		this.course_5 = course_5;
	}
	private int pref_score = 0;
	
	public List<String> getAllocated_courses() {
		return allocated_courses;
	}
	public void setAllocated_courses(String Courses) {
		this.allocated_courses.add(Courses);
	}
	public int getPref_score() {
		return pref_score;
	}
	public void setPref_score(int pref_score_In) {
		this.pref_score= pref_score_In;
	}
	public String getCourse_1() {
		return course_1;
	}
	public void setCourse_1(String course_1_In) {
		this.course_1 = course_1_In;
	}
	public String getCourse_2() {
		return course_2;
	}
	public void setCourse_2(String course_2_In) {
		this.course_2 = course_2_In;
	}
	public String getCourse_3() {
		return course_3;
	}
	public void setCourse_3(String course_3_In) {
		this.course_3 = course_3_In;
	}
	public String getCourse_4() {
		return course_4;
	}
	public void setCourse_4(String course_4_In) {
		this.course_4 = course_4_In;
	}
	public List<String> getCourse_pref_In() {
		return course_pref;
	}
	public void setCourse_pref_In(List<String> course_pref_In) {
		this.course_pref = course_pref_In;
	}
	public String getStudent_id_In() {
		return student_id;
	}
	public void setStudent_id_In(String student_id_In) {
		this.student_id = student_id_In;
	}
	
	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", course_pref="
				+ course_pref + ", allocated_courses=" + allocated_courses
				+ ", course_1=" + course_1 + ", course_2=" + course_2
				+ ", course_3=" + course_3 + ", course_4=" + course_4
				+ ", pref_score=" + pref_score + "]";
	}

	
}
