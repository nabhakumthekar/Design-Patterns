package spreadsheetUpdates.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spreadsheetUpdates.observer.SpreadSheet;
import spreadsheetUpdates.util.FileDisplayInterface;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.StdoutDisplayInterface;
/*
 * This class writes result to file  or prints it to screen.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     PrintWriter
 */
public class Result implements StdoutDisplayInterface,FileDisplayInterface {

	public Result() {
		Logger.writeMessage("\n ---- In Result Constructor ---- "
				, Logger.DebugLevel.CONSTRUCTOR);
	}
	/*
	 * This method writes result to file.
	 */
	public void writeUpdateToFile(File outputTxt_In) {
		List<String> cyclesList_In = new ArrayList<String>();
		cyclesList_In = SpreadSheet.calculateTotal();
		String outputTxt = outputTxt_In.toString();
		try {
			PrintWriter pr = new PrintWriter(new FileOutputStream(new File(
					outputTxt), false));
			for(int i=0;i<cyclesList_In.size();i++){
				pr.println(cyclesList_In.get(i));
			}
			pr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}		
		
	}
	/*
	 * This method prints result to screen.
	 */
	public void writeUpdateToScreen() {
		
		
	}
}
