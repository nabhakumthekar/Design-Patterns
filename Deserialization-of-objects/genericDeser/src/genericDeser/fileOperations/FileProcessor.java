package genericDeser.fileOperations;

import genericDeser.util.Logger;
import genericDeser.util.PopulateObjects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class FileProcessor {
    private ArrayList<Object> objectTypes = null;
    private Scanner sc_input = null;
    private PopulateObjects populateObjects = null;
    
    
    public FileProcessor() {
        Logger.writeMessage("In file processor class", Logger.DebugLevel.CONSTRUCTOR);
        populateObjects = new PopulateObjects();
        objectTypes = new ArrayList<Object>();
    }
    
   public ArrayList<Object> readFile(File inputFile) {
        try {
            sc_input = new Scanner(new FileReader(inputFile));
            if (!sc_input.hasNext()) {
                System.err.println("file is empty. Exiting...");
                sc_input.close();
                System.exit(1);
            } else {
                while (sc_input.hasNext()) {
                    objectTypes =	populateObjects.deserObjects(sc_input);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Exiting...");
            e.printStackTrace();
            System.exit(1);
        }finally{
            sc_input.close();
        }
        return objectTypes;
    }
}
