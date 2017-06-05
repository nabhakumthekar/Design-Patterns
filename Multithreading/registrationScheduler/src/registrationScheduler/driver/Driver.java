
package registrationScheduler.driver;

import java.io.File;

import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.CourseAllocation;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

public class Driver{

	public static void main(String args[]) {
		File preferenceTxt = null;
		File addDropTxt = null;
		File outputTxt = null;
		int debugLevel = 0;
		int threadNum = 0;
		if(!args[0].equals("${arg0}") && !args[1].equals("${arg1}") && 
		    	!args[2].equals("${arg2}") && !args[3].equals("${arg3}") && !args[4].equals("${arg4}") ){
			preferenceTxt = new File(args[0]);
			addDropTxt = new File(args[1]);
			outputTxt = new File(args[2]);
			threadNum = Integer.parseInt(args[3]);
			if(threadNum > 3 || threadNum < 1){
				System.err.println("Number of threads should be between 1 & 3\nentered thread : " + threadNum);
	            System.exit(1);
			}
			debugLevel = Integer.parseInt(args[4]);
			if(debugLevel > 4 || debugLevel < 0){
				System.err.println("Debug level should be between 0 & 4\nentered debug level : " + debugLevel);
	            System.exit(1);
			}else{
				Logger.setDebugValue(debugLevel);
			}
        } else {
            System.err.println("Invalid arguments");
            System.exit(1);
       }
		FileProcessor fileProcessor = new FileProcessor(preferenceTxt,addDropTxt);
		StdoutDisplayInterface result = new Results();
		CourseAllocation courseAllocation = new CourseAllocation();
		CreateWorkers createWorker = new CreateWorkers(fileProcessor,result,courseAllocation);
		createWorker.startWorkers(threadNum);
		result.writeScheduleToScreen();
		((Results)result).writeScheduleToFile(outputTxt);
	} 
} 
