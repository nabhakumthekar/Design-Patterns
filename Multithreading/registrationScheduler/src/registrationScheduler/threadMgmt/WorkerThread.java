package registrationScheduler.threadMgmt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import registrationScheduler.util.CourseAllocation;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;
import registrationScheduler.util.Student;
/*
 * This class implements Runnable to run threads.
 * @author  Nabha Kumthekar.
 * @see     Threads
 * @see		Runnable interface. 
 */
public class WorkerThread implements Runnable {

	private FileProcessor fileProcessor = null;
	private StdoutDisplayInterface result = null;
	private CourseAllocation courseAllocation = null;
	private Map<String,Student> map = new HashMap<>();
	private int  nextFile = 0;
	public WorkerThread(FileProcessor fileProcessor_In,
			StdoutDisplayInterface result_In, CourseAllocation courseAllocation_In) {
		Logger.writeMessage("\n ---- In Worker Thread Constructor ----"
				, Logger.DebugLevel.CONSTRUCTOR);
		fileProcessor = fileProcessor_In;
		result = result_In;
		courseAllocation = courseAllocation_In;
	
	}
	/*
	 * This method is called by threads to start execution.
	 * It reads both input files and calls course allocation class methods to start allocation.
	 */
	public void run() {
		Logger.writeMessage("\n ---- In WorkerThread Run Method ----", Logger.DebugLevel.RUN_CALL);
		try{
			String record = fileProcessor.fileReading1();
			while(record!=null){
				courseAllocation.scheduleFile(record);
				record = fileProcessor.fileReading1();
			}	
		
			if(record == null){
				courseAllocation.allocatNotPrefCourse();
			}
		    
			String file2 = fileProcessor.fileReading2();
			while(file2!=null){
				map = courseAllocation.addDropCourse(file2);
				result.setScheduleTofile(map);
				file2 = fileProcessor.fileReading2();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		} 
	}
}
