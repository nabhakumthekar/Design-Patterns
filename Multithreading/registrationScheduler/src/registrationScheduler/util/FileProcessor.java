package registrationScheduler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import registrationScheduler.util.Logger;
/*
 * This class reads add/drop & preferences files line by line
 * and allocates courses according to availability.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     Map
 */
public class FileProcessor {
	Scanner sc_pref = null;
	Scanner sc_ad = null;
	private String preferenceTxt = null;
	private String addDropTxt = null;
	int flag = 0;
	
	public FileProcessor(File preferenceTxt_In, File addDropTxt_In) {
		preferenceTxt = preferenceTxt_In.toString();
		addDropTxt = addDropTxt_In.toString();
		Logger.writeMessage("\n ---- In File Processor Constructor ---- "
				, Logger.DebugLevel.CONSTRUCTOR);
		try {
			sc_pref = new Scanner(new FileReader(preferenceTxt));
			sc_ad  = new Scanner(new FileReader(addDropTxt));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			sc_pref.close();
			sc_ad.close();
			System.err.println("File is empty. Exiting...");
			System.exit(1);
		}finally{
			
		}
	}
	/*
	 * This Method reads preferences file.
	 * @throws FileNotFoundException.
	 * @return string of line in scanner has next line else null.
	 */
	public synchronized String fileReading1() {
		String record = null;
		try{
			if (sc_pref.hasNext()) {
				return	record = sc_pref.nextLine();
			}	
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}finally{
			
		}	
		return null;
	}
	/*
	 * This Method reads add/drop file.
	 * @throws FileNotFoundException.
	 * @return string of line in scanner has next line else null.
	 */
	public synchronized String fileReading2() {
		String line = null;
		try{
			if (sc_ad.hasNext()) {
				return	line = sc_ad.nextLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	/*
	 * This Method closes both files.
	 */
	public void scannerClosing() {
		sc_pref.close();
		sc_ad.close();
	}	
}
