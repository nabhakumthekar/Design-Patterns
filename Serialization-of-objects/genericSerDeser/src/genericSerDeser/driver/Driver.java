package genericSerDeser.driver;


import genericSerDeser.fileOperations.FileProcessor;
import genericSerDeser.util.DPML;
import genericSerDeser.util.Logger;
import genericSerDeser.util.PopulateObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import genericSerDeser.store.FileDisplayInterface;
import genericSerDeser.store.Result;
import genericSerDeser.strategy.SerStrategy;

public class Driver {
	public static void main(String args[]) {
		File inputFile = null;
		File outputFile = null;
		int debugLevel = 0;
		try {
			if (!args[0].equals("${arg0}") && !args[1].equals("${arg1}") && !args[2].equals("${arg2}")) {
				inputFile = new File(args[0]);
				outputFile = new File(args[1]);
				debugLevel = Integer.parseInt(args[2]);
				if (debugLevel > 4 || debugLevel < 0) {
					System.err
							.println("Debug level should be between 0 & 4\nentered debug level : "
									+ debugLevel);
					System.exit(1);
				} else {
					Logger.setDebugValue(debugLevel);
				}
				FileProcessor fileProsessor = new FileProcessor(new Scanner(new FileReader(inputFile)));
				PopulateObjects populateObjects = new PopulateObjects(fileProsessor);
				ArrayList<Object> objectTypes = populateObjects.getDataFromFile();
				FileDisplayInterface result = new Result(outputFile);
				SerStrategy dpml = new DPML(result);
				dpml.do_strategy(objectTypes);
				
			} else {
				System.err.println("Invalid arguments");
				System.exit(1);
			}
		} catch (NullPointerException ne) {
			System.err.println("Null pointer exception in main ");
			ne.printStackTrace();
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err.println("invalid logger argument");
			e.printStackTrace();
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("File not found in main class");
			e.printStackTrace();
			System.exit(1);
		}finally {

		}

	}
}
