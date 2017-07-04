package spreadsheetUpdates.driver;

import java.io.File;

import spreadsheetUpdates.store.Result;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.StdoutDisplayInterface;
import spreadsheetUpdates.store.Result;
import spreadsheetUpdates.util.FileProcessor;
public class Driver {
	public static void main(String args[]) {
		File inputFile = null;
		File outputTxt = null;
		int debugLevel = 0;
		FileProcessor fileProcessor = new FileProcessor();
		if (!args[0].equals("${arg0}") && !args[1].equals("${arg1}")
		&& !args[2].equals("${arg2}")){
			inputFile = new File(args[0]);
			outputTxt = new File(args[1]);
			debugLevel = Integer.parseInt(args[2]);
			if (debugLevel > 4 || debugLevel < 0) {
				System.err
						.println("Debug level should be between 0 & 4\nentered debug level : "
								+ debugLevel);
				System.exit(1);
			} else {
				 Logger.setDebugValue(debugLevel);
			}
			fileProcessor.readFile(inputFile);
			StdoutDisplayInterface result = new Result();
			((Result) result).writeUpdateToFile(outputTxt);
		} else {
			System.err.println("Invalid arguments");
			System.exit(1);
		}
		
	}
} 

