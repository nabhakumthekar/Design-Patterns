package spreadsheetUpdates.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import spreadsheetUpdates.observer.SpreadSheet;

/*
 * This class reads cell records files line by line
 * and call createCell method for further processing
 * @author  Nabha Kumthekar.
 * @see     Scanner
 */
public class FileProcessor {
	public FileProcessor() {
		Logger.writeMessage("\n ---- In File Processor Constructor ---- "
				, Logger.DebugLevel.CONSTRUCTOR);
	}

	public void readFile(File inputFile){
		Scanner sc_input = null;
		SpreadSheet sheet = new SpreadSheet();
		try {
			sc_input = new Scanner(new FileReader(inputFile));
			if(!sc_input.hasNext()){
				Logger.writeMessage("\n ---- In File Processor Constructor ---- "
						, Logger.DebugLevel.FILE_EMPTY);
				System.err.println("file is empty. Exiting...");
				sc_input.close();
				System.exit(1);
			}else{
				while (sc_input.hasNext()) {
					sheet.createCell(sc_input);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Exiting...");
			e.printStackTrace();
			sc_input.close();
			System.exit(1);
		}
	}
}
