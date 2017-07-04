package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import spreadsheetUpdates.util.Logger;

/*
 * This class implements creates cell and checks for cycle and if no cycles are there it proceeds to observer pattern.
 * @author  Nabha Kumthekar.
 * @see     List
 * @see     Map
 */
public class SpreadSheet {
    private static Map<String, Cell> sheet = new HashMap<String, Cell>();
    private static List<String> cyclesList = new ArrayList<String>();
    private static CheckForCycle check;
    
    
    public SpreadSheet() {
        Logger.writeMessage("\n ---- In Spread Sheet Constructor ---- ",
                            Logger.DebugLevel.CONSTRUCTOR);
    }
    /*
     * This method creates cells or updates cell if no cycle is present.
     * @see     List
     * @see     Map
     * @throws  Exception is thrown is cell cannot be created.
     */
    public static void createCell(Scanner cellRecord) {
        String[] values;
        DetectCycle.startDetecting();
        try {
            values = cellRecord.nextLine().split("\\=");
            String[] operands = values[1].split("\\+");
            
            if(checkValidValues(values,operands)){
                check = new CheckForCycle();
                if (sheet.containsKey(values[0])) {
                    Cell c1 = new Cell(values[0], operands[0], operands[1]);
                    DetectCycle.insertSet(c1);
                    check.checkThisCell(c1);
                    DetectCycle.endDetecting();
                    Cell c = sheet.get(values[0]);
                    if (!check.getStatus()) {
                        c.updateCell(operands[0], operands[1]);
                    } else {
                        String cycle = "Cycle detected for record " + values[0]
                        + "=" + operands[0] + "+" + operands[1];
                        setCycle(cycle);
                    }
                } else {
                    Cell c = new Cell(values[0], operands[0], operands[1]);
                    DetectCycle.insertSet(c);
                    check.checkThisCell(c);
                    DetectCycle.endDetecting();
                    if (!check.getStatus()) {
                        c.findValue();
                    } else {
                        String cycle = "Cycle detected for record " + values[0]
                        + "=" + operands[0] + "+" + operands[1];
                        setCycle(cycle);
                    }
                }
            }else{
                System.err.println("invalid file..\nExiting...");
                System.exit(1);
            }
            
        } catch (Exception e) {
            System.out.println("Cannot create cell");
            e.printStackTrace();
            System.exit(1);
        } finally {
            
        }
    }
    /*
     * This method checks if value is greater that 10.
     */
    private static boolean checkValidValues(String[] values, String[] operands){
        if(!Character.isLetter(operands[0].charAt(0)) && !Character.isLetter(operands[1].charAt(0))){
            if(Integer.parseInt(operands[0])<10 ||Integer.parseInt(operands[1])<10){
                System.err.println("invalid operand value/n Value must be greater than 10");
                return false;
            }
        }else if(Character.isLetter(operands[0].charAt(0)) && !Character.isLetter(operands[1].charAt(0))){
            if(Integer.parseInt(operands[1])<10){
                System.err.println("invalid operand value/n Value must be greater than 10");
                return false;
            }
        }else if(!Character.isLetter(operands[0].charAt(0)) && Character.isLetter(operands[1].charAt(0))){
            if(Integer.parseInt(operands[0])<10){
                System.err.println("invalid operand value/n Value must be greater than 10");
                return false;
            }
        }else{
            return true;
        }
        return true;
    }
    /*
     * This method provides cell on request.
     */
    public static Cell getCell(String cell) {
        Cell c = sheet.get(cell);
        return c;
    }
    /*
     * This method inserts cell.
     * @return	True if cell insertion is succsessful else false.
     */
    public static boolean insertCell(String cellName, Cell c) {
        if (sheet.put(cellName, c) != null) {
            return true;
        }
        return false;
    }
    /*
     * This method simply adds cycles to cyclesList list.
     * @see		List
     */
    public static void setCycle(String cycle) {
        Logger.writeMessage("\n ---- CYCLE DETECTED  ---- ",
                            Logger.DebugLevel.PRINT_CYCLES);
        cyclesList.add(cycle);
    }
    
    /*
     * This method calculates sum of all the cells after complete execution on the is performed.
     * @see		List
     * @return  cyclesList
     */
    public static List<String> calculateTotal() {
        int sum = 0;
        for (Map.Entry entry : sheet.entrySet()) {
            sum += sheet.get(entry.getKey()).getValue();
        }
        Logger.writeMessage("\n ---- TOTAL SUM  ---- " + sum,
                            Logger.DebugLevel.PRINT_SUM);
        Integer sumInt = new Integer(sum);
        String sumString = "The sum of all cell values is:" + sumInt.toString();
        cyclesList.add(sumString);
        return cyclesList;
    }
}
