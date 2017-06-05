package registrationScheduler.studentData;

import java.util.ArrayList;
import java.util.List;

/*
 * This Class contains student information such as id, preferred score,preference score and allocated courses.
 * @author  Nabha Kumthekar.
 */
public class Student {
	private int reg_time;
	private String student_id;
	List <String> course_pref = new ArrayList<String>();
	List <String> allocated_courses = new ArrayList<String>();
	private String course_1 = null;
	private String course_2 = null;
	private String course_3 = null;
	private String course_4 = null;
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
	public int getReg_time_In() {
		return reg_time;
	}
	public void setReg_time_In(int reg_time_In) {
		this.reg_time = reg_time_In;
	}
	public String getStudent_id_In() {
		return student_id;
	}
	public void setStudent_id_In(String student_id_In) {
		this.student_id = student_id_In;
	}
	
	public Student() {}
	public Student(int reg_time_In, String student_id_In, List<String> course_pre) {
		super();
		this.reg_time = reg_time_In;
		this.student_id = student_id_In;
		this.course_pref = course_pre;
	}
	@Override
	public String toString() {
		return "Student [reg_time_In=" + reg_time + ", student_id_In="
				+ student_id + ", course_pref_In=" + course_pref
				+ ", course_1=" + course_1 + ", course_2=" + course_2
				+ ", course_3=" + course_3 + ", course_4=" + course_4
				+ ", pref_score=" + pref_score + "]";
	}	
}
