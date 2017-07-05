package genericDeser.driver;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.util.Logger;
import genericDeser.util.PopulateObjects;

import java.io.File;
import java.util.ArrayList;

public class Driver {
    public static void main(String args[]) {
        File inputFile = null;
        int debugLevel = 0;
        try{
            if (!args[0].equals("${arg0}") && !args[1].equals("${arg1}")) {
                inputFile = new File(args[0]);
                debugLevel = Integer.parseInt(args[1]);
                if (debugLevel > 4 || debugLevel < 0) {
                    System.err
                    .println("Debug level should be between 0 & 4\nentered debug level : "
                             + debugLevel);
                    System.exit(1);
                } else {
                    Logger.setDebugValue(debugLevel);
                }
                FileProcessor fileProcessor = new FileProcessor();
                PopulateObjects populateObjects = new PopulateObjects();
                ArrayList<Object> objectTypes = fileProcessor.readFile(inputFile);
                ArrayList<Integer> totalObjects = populateObjects.totalInstances(objectTypes);
                
                if (debugLevel == 0) {
                    System.out.println("Number of unique First objects: "
                                       + totalObjects.get(0));
                    System.out.println("Total Number of First objects: "
                                       + totalObjects.get(1));
                    System.out.println("Number of unique Second objects: "
                                       + totalObjects.get(2));
                    System.out.println("Total Number of Second objects: "
                                       + totalObjects.get(3));
                }
            } else {
                System.err.println("Invalid arguments");
                System.exit(1);
            }
        }catch(NullPointerException ne){
            System.err.println("Null pointer exception in main ");
            ne.printStackTrace();
            System.exit(1);
        }catch(NumberFormatException e){
            System.err.println("invalid logger argument");
            e.printStackTrace();
            System.exit(1);
        }finally{
            
        }
    }
}
