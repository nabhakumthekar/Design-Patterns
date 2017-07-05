package genericSerDeser.util;


import genericSerDeser.util.Logger;
import genericSerDeser.fileOperations.FileProcessor;
import genericSerDeser.strategy.SerStrategy;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PopulateObjects {

	private ArrayList<Object> objectTypes = null;
	private Map<String, Class<?>> recordDetails = null;
    private Class<?> cls = null;
	private Object obj = null;
	private ArrayList<String> objectInfo = null;
    FileProcessor fileProcessor = null;
    File inputFile = null;
	
	class StringEmptyException extends Exception{
		private static final long serialVersionUID = 1L;
		public StringEmptyException(String msg){
			super(msg);
		}
	}

	public PopulateObjects(FileProcessor fileProcessor_In) {
		Logger.writeMessage("In populate objects class", Logger.DebugLevel.CONSTRUCTOR);
		objectTypes = new ArrayList<Object>();
		recordDetails = new HashMap<String, Class<?>>();
        objectInfo = new ArrayList<String>();
		setTypeDetailMap(recordDetails);
		fileProcessor = fileProcessor_In;
	}

	public void setTypeDetailMap(Map<String, Class<?>> recordDetails) {
		recordDetails.put("int", Integer.TYPE);
		recordDetails.put("float", Float.TYPE);
		recordDetails.put("short", Short.TYPE);
		recordDetails.put("String", String.class);
		recordDetails.put("byte", Byte.TYPE);
		recordDetails.put("long", Long.TYPE);
		recordDetails.put("double", Double.TYPE);
		recordDetails.put("boolean", Boolean.TYPE);
		recordDetails.put("char", Character.TYPE);
	}

	public ArrayList<Object> getDataFromFile(){
		String scannerInput = fileProcessor.readFile();
		try {
			while (scannerInput != null) {
					deserObjects(scannerInput);
					scannerInput = fileProcessor.readFile();
			}
			fileProcessor.closeScanner();
		} catch (Exception e) {
			System.err.println(" Exception Occured  Method populateDataStructure \n");
			e.printStackTrace();
			System.exit(0);
		}
		return objectTypes;
	}
	public ArrayList<Object> deserObjects(String scannerInput_In) {
		Logger.writeMessage("In deser object method", Logger.DebugLevel.DESEROBJECT);
		String record = scannerInput_In;
		if (record.contains("<fqn:")) {
			try {
				int endIndex = record.length() - 1;
				String clsName = record.substring(5, endIndex);
				cls = Class.forName(clsName);
				obj = cls.newInstance();
				objectTypes.add(obj);
			} catch (Exception e) {
				System.err.println("Exception has occured in PopulateObject.java");
				e.printStackTrace();
				System.exit(1);
			}  finally {

			}
		} else if (record.contains("<type")) {
			try {
				String[] attribute = record.split("\\, ");
				Object[] params = new Object[1];
				String type = attribute[0].substring(6, attribute[0].length()).trim();
				String var = attribute[1].substring(4, attribute[1].length()).trim();
				String value = attribute[2].substring(6, attribute[2].length()-1).trim();
				if(type.equals("String") && value.equals("")){
					throw new StringEmptyException("String value null");
				}
				
				try {
					switch (type) {
					case "int":
						params[0] = new Integer(value);
						break;
					case "float":
						params[0] = new Float(value);
						break;
					case "short":
						params[0] = new Short(value);
						break;
					case "String":
						params[0] = new String(value);
						break;
					case "byte":
						params[0] = new Byte(value);
						break;
					case "long":
						params[0] = new Long(value);
						break;
					case "double":
						params[0] = new Double(value);
						break;
					case "boolean":
						params[0] = new Boolean(value);
						break;
					case "char":
						params[0] = new Character(value.charAt(0));
						break;
					default:
						break;
					}
				} catch (NumberFormatException ne) {
					System.err.println("In PopulateObject.java " + "value for "
							+ type + " is invalid");
					ne.printStackTrace();
					System.exit(1);
				}
				String methdName1 ="set" + var;
				Class[] signature = new Class[1];
				signature[0] = recordDetails.get(type);
				Method meth = cls.getMethod(methdName1, signature);
				Object result = meth.invoke(obj, params);
			} catch (Exception e) {
				System.err.println("Exception has occured in PopulateObject.java");
				e.printStackTrace();
				System.exit(1);
			}  finally {

			}
		}
		return objectTypes;
	}
}
