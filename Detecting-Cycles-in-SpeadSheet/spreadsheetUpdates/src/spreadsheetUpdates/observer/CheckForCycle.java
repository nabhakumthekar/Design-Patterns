package spreadsheetUpdates.observer;

import spreadsheetUpdates.observer.SpreadSheet;
import spreadsheetUpdates.util.Logger;

/*
 * This class implements algorithm to check cycles.
 * @author  Nabha Kumthekar.
 */
public class CheckForCycle {
	private boolean status;
	private boolean cycleDetected = false;
	public CheckForCycle(){
		Logger.writeMessage("\n ---- In Check For Cycle Constructor ---- "
				, Logger.DebugLevel.CONSTRUCTOR);
	}
	
	/*
	 * This method checks if current operand is a cell and call insert set method.
	 * if cell is not present it creates one and inserts into set.
	 * checkThisCell method is called recursively until cycle is detected
	 * if cycle is not present algorithm terminates and no cycle is present.
	 * @see     Set
	 */
	public void checkThisCell(Cell a) {
		String[] ops = a.getOperand();
		for (String o : ops) {
			if (Character.isLetter(o.charAt(0))) {
				Cell c = SpreadSheet.getCell(o);
				if (c != null) {
					status = DetectCycle.insertSet(c);
					if(status == false){
						this.cycleDetected = true;
					}
					if (status) {
						checkThisCell(c);
					}
				}else{
					status = DetectCycle.insertSet(new Cell(o,"0","0"));
					if(status == false){
						this.cycleDetected = true;
					}
				}
			}
		}
	}
	/*
	 * This methods returns true id cycle is present
	 * @returns true/false
	 */
	public boolean getStatus(){
		return this.cycleDetected;
	}
}
