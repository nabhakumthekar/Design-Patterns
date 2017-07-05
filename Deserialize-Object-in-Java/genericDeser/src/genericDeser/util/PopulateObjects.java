package genericDeser.util;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PopulateObjects {
    
    private ArrayList<Object> objectTypes = null;
    private ArrayList<Integer> finalResult = null;
    private Map<String, Class<?>> recordDetails = null;
    private Set<Object> uniqueObjs = null;
    private Class<?> cls = null;
    private Object obj = null;
    
    public PopulateObjects() {
        Logger.writeMessage("In populate objects class", Logger.DebugLevel.CONSTRUCTOR);
        objectTypes = new ArrayList<Object>();
        recordDetails = new HashMap<String, Class<?>>();
        uniqueObjs = new HashSet<Object>();
        finalResult = new ArrayList<Integer>();
        setTypeDetailMap(recordDetails);
    }
    
    class StringEmptyException extends Exception{
        private static final long serialVersionUID = 1L;
        public StringEmptyException(String msg){
            super(msg);
        }
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
    
    public ArrayList<Object> deserObjects(Scanner sc_input) {
        Logger.writeMessage("In deser object method", Logger.DebugLevel.DESEROBJECT);
        String record = sc_input.nextLine();
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
                String value = attribute[2].substring(6, attribute[2].length() - 1).trim();
                
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
                String methdName ="set" + var;
                Class[] signature = new Class[1];
                signature[0] = recordDetails.get(type);
                Method meth = cls.getMethod(methdName, signature);
                Object result = meth.invoke(obj, params);
                return objectTypes;
            } catch (Exception e) {
                System.err.println("Exception has occured in PopulateObject.java");
                e.printStackTrace();
                System.exit(1);
            }  finally {
                
            }
        }
        return objectTypes;
    }
    
    public ArrayList<Integer> totalInstances(ArrayList<Object> objectTypes_In) {
        Logger.writeMessage("Calculating total & unique instances", Logger.DebugLevel.INSTANCES);
        ArrayList<Object> calculateObj = objectTypes_In;
        int firstObjSum = 0;
        int secondObjSum = 0;
        int uniqueFirst = 0;
        int uniqueSecond = 0;
        try {
            for (Object object : calculateObj) {
                if (object instanceof First) {
                    uniqueObjs.add((First) object);
                    firstObjSum++;
                } else if (object instanceof Second) {
                    uniqueObjs.add((Second) object);
                    secondObjSum++;
                }
            }
            for (Object object1 : uniqueObjs) {
                if (object1 instanceof First) {
                    uniqueFirst++;
                } else if (object1 instanceof Second) {
                    uniqueSecond++;
                }
            }
            finalResult.add(uniqueFirst);
            finalResult.add(firstObjSum);
            finalResult.add(uniqueSecond);
            finalResult.add(secondObjSum);
        } catch (Exception e) {
            System.err.println("Exception has occured in PopulateObject class in totalInstances method");
            e.printStackTrace();
            System.exit(1);

        } finally {
            
        }
        return finalResult;
    }
}
