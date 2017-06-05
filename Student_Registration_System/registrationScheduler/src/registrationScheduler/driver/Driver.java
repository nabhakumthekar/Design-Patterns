package registrationScheduler.driver;

import java.io.File;

import registrationScheduler.util.Logger;
import registrationScheduler.util.fileHandling;

/*
 * This Class contains main method and file reading method.
 * @author  Nabha Kumthekar.
 * @see     File
 */
public class Driver {
	public static void main(String args[]) {
		fileHandling fh = new fileHandling();
		File inputFile1 = null;
		File inputFile2 = null;
	    File outputFile = null;
	    int logLevel = 0;

	    if(!args[0].equals("${arg0}") && !args[1].equals("${arg1}") && 
	    	!args[2].equals("${arg2}") && !args[3].equals("${arg3}")){
	    	 inputFile1 = new File(args[0]);
            //inputFile1 = new File("/Users/nabha/Documents/SEM2/DP/preference-input-a.txt");
	         inputFile2 = new File(args[1]);
	         outputFile = new File(args[2]);
	         logLevel = Integer.parseInt(args[3]);
	         Logger.setDebugValue(logLevel);
			fh.fileReading(inputFile1,inputFile2,outputFile);
	    }else{
	    	System.err.println("Invalid number of arguments\n Application exiting..");
	        System.exit(1);
	    }
	}
}
