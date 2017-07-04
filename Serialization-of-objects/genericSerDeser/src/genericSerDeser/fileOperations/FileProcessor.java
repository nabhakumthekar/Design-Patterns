package genericSerDeser.fileOperations;



import genericSerDeser.util.Logger;
import genericSerDeser.util.PopulateObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
	
	File input_file = null;
	Scanner sc_input = null;
	PopulateObjects populateObjects = null;
	
	public FileProcessor(Scanner scanner) {
		Logger.writeMessage("In file processor class", Logger.DebugLevel.CONSTRUCTOR);
		sc_input = scanner;
		if(!sc_input.hasNextLine()){
			System.err.println("File is empty.. Exiting..");
            Logger.writeMessage("File is empty", Logger.DebugLevel.FILE);
			System.exit(1);
		}
	}

	public String readFile() {
		String record = null;
		try {
			if(sc_input.hasNextLine()){
				record = sc_input.nextLine();
			}
		} catch (Exception e) {
			System.err.println("Exception in file prosessor class");
			e.printStackTrace();
			System.exit(0);
		} finally {

		}
		return record;
	}

	public void closeScanner() {
		sc_input.close();
	}
}
