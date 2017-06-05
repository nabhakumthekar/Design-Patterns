package registrationScheduler.threadMgmt;

import java.util.ArrayList;
import java.util.List;

import registrationScheduler.util.CourseAllocation;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;
/*
 * This class creates threads.
 * @author  Nabha Kumthekar.
 * @see     Threads
 */
public class CreateWorkers {

	private FileProcessor fileProcessor = null;
	private StdoutDisplayInterface result = null;
	private CourseAllocation courseAllocation = null;
	
	/*
	 * CreateWorkers constructor initializes fileProcessor, result & courseAllocation.
	 */
	public CreateWorkers(FileProcessor fileProcessor_In,
			StdoutDisplayInterface result_In, CourseAllocation courseAllocation_In) {
		Logger.writeMessage("\n ---- In Create Workers Constructor ---- "
				, Logger.DebugLevel.CONSTRUCTOR);
		fileProcessor = fileProcessor_In;
		result = result_In;
		courseAllocation = courseAllocation_In;
	}
	
	/*
	 * This method creates thread according to number of threads requested.
	 * starts threads and joins them.
	 * finally closes scanner.
	 * @param Integer number of threads.
	 * @see     Threads
	 */
	
	public void startWorkers(int NUM_THREADS) {
		List<Thread> threadset = new ArrayList<Thread>();
		WorkerThread workerThread = new WorkerThread(fileProcessor,result,courseAllocation);
		try{
			for (int i = 0; i < NUM_THREADS; i++) {
				threadset.add(new Thread(workerThread));			
			}
			
			for(Thread t : threadset){
				try {
					t.start();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			for(Thread t : threadset){
				try {
					t.join();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			for(Thread t : threadset){
				if(t.isAlive())
					continue;
				fileProcessor.scannerClosing();
			}
		}
	}
}
