package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.List;

import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.observer.ObserverInter;
import spreadsheetUpdates.observer.SubjectInter;
import spreadsheetUpdates.observer.SpreadSheet;

/*
 * This class implements observer pattern.
 * @author  Nabha Kumthekar.
 * @see     Observer Pattern
 * @see     List
 */
public class Cell implements SubjectInter, ObserverInter {
	private List<Cell> observers = new ArrayList<Cell>();
	private String variable;
	private String[] operand = new String[2];
	private int value;

	public Cell(String variable_In, String operand1_In, String operand2_In) {
		Logger.writeMessage("\n ---- In Cell Constructor ---- ",
				Logger.DebugLevel.CONSTRUCTOR);
		this.variable = variable_In;
		this.operand[0] = operand1_In;
		this.operand[1] = operand2_In;
	}

	/*
	 * This method gives operand on request.
	 * 
	 * @return String array or operands
	 */
	public String[] getOperand() {
		return this.operand;
	}

	/*
	 * This method gives currents cell's name.
	 * 
	 * @return cell name
	 */
	public String getName() {
		return this.variable;
	}

	/*
	 * This method gives currents cell's value.
	 * 
	 * @return cell value
	 */
	public int getValue() {
		return this.value;
	}

	/*
	 * This method gives list of observers.
	 * 
	 * @return observers
	 */
	public List<Cell> getObservers() {
		return this.observers;
	}

	/*
	 * This method calculates value of cell and adds call registerObserver
	 * method to set itself as an observer. Also it inserts value into
	 * spreadsheet map and calls notify observer for broadcasting result
	 */
	public void findValue() {
		try {
			int[] intOps = new int[2];
			for (int i = 0; i < operand.length; i++) {
				if (isCell(operand[i])) {
					if (SpreadSheet.getCell(operand[i]) != null) {
						Cell c = SpreadSheet.getCell(operand[i]);
						intOps[i] = c.getValue();
						if (!c.observers.contains(this)) {
							c.registerObserver(this);
						}
					} else {
						Cell c = new Cell(operand[i], "0", "0");
						c.findValue();

						if (!c.observers.contains(this)) {
							c.registerObserver(this);
						}
					}
				} else {
					intOps[i] = Integer.parseInt(operand[i]);
				}
			}
			this.value = 0;
			for (int a : intOps) {
				this.value += a;
			}
			SpreadSheet.insertCell(this.variable, this);
			this.notifyObservers();
		} catch (NullPointerException ne) {
			System.err.println("cell is empty");
			ne.printStackTrace();
			System.exit(1);
		} finally {

		}
	}

	/*
	 * This method checks if the given operand is cell or not
	 * 
	 * @return true if it is a cell else false
	 */
	private boolean isCell(String operand_In) {
		if (Character.isLetter(operand_In.charAt(0))) {
			return true;
		}
		return false;
	}

	/*
	 * This method calls find value to update observers.
	 */
	public void update() {
		this.findValue();
	}

	/*
	 * This method adds observer to this list.i.e register observers.
	 */
	public void registerObserver(Cell o) {
		this.observers.add(o);
	}

	/*
	 * This method removes observer from current cell.
	 */
	public void removeObserver(Cell o) {
		this.observers.remove(o);
	}

	/*
	 * This method on change notifies observer by calling update method.
	 */
	public void notifyObservers() {
		if (SpreadSheet.getCell(this.getName()) != null) {
			for (Cell c : this.observers) {
				c.update();
			}
		}
	}

	/*
	 * This method updates the operand if same cell with new operand values is
	 * found also it removes it itself as an observer from old operands list by
	 * calling removeObserver.
	 */
	public void updateCell(String operand1, String operand2) {
		try {
			for (int i = 0; i < this.operand.length; i++) {
				if (isCell(this.operand[i])) {
					if (SpreadSheet.getCell(this.operand[i]) != null) {
						Cell c1 = SpreadSheet.getCell(this.operand[i]);
						if (c1.observers.contains(this)) {
							c1.removeObserver(this);
						}
					}
				}
			}
			this.operand[0] = operand1;
			this.operand[1] = operand2;
			findValue();
		} catch (NullPointerException ne) {
			System.err.println("cell is null");
			ne.printStackTrace();
			System.exit(1);
		}
	}
}
