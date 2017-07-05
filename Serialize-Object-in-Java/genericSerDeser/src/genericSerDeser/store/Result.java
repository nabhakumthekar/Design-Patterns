package genericSerDeser.store;

import genericSerDeser.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Result implements StdoutDisplayInterface,FileDisplayInterface {

	String outputFile = null;
	public Result(File outputFile_In) {
		Logger.writeMessage("In Result class", Logger.DebugLevel.CONSTRUCTOR);
		outputFile = outputFile_In.toString();
	}
	

	@Override
	public void writeDataToFile(ArrayList<String> serialisedObj_In) {
		try {
			PrintWriter pr = new PrintWriter(new FileOutputStream(new File(outputFile), false));
			
			for(int i=0;i<serialisedObj_In.size();i++){
				pr.println(serialisedObj_In.get(i));
			}
			pr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	@Override
	public void writeUpdateToScreen() {
		// TODO Auto-generated method stub
		
	}
}
