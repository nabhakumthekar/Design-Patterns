package genericSerDeser.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import genericSerDeser.store.FileDisplayInterface;
import genericSerDeser.store.Result;
import genericSerDeser.strategy.SerStrategy;

public class DPML implements SerStrategy {

	private Class<?> cls = null;
	private ArrayList<String> serObj = null;
	FileDisplayInterface result;

	public DPML(FileDisplayInterface result_In) {
		Logger.writeMessage("In DPML class", Logger.DebugLevel.CONSTRUCTOR);
		serObj = new ArrayList<String>();
		result = result_In;
	}

	@Override
	public void do_strategy(ArrayList<Object> objectTypes) {
        Logger.writeMessage("Applying Strategy", Logger.DebugLevel.INSTANCES);
		try {
			for (int i = 0; i < objectTypes.size(); i++) {
				cls = objectTypes.get(i).getClass();
				String attribute = null;
				String serObjFormat = null;
				String classStartName = "<fqn:"+ cls.toString().substring(6,cls.toString().length())+">";
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					String var = fields[j].getName();
					String methdName2 ="get" + var;
					Method meth1 = cls.getMethod(methdName2);
					Object result1 = meth1.invoke(objectTypes.get(i));
					String type = fields[j].getType().toString();
					if(type.toString().contains("String")){
		               type = "String";
		            }
					String value = result1.toString();
					if(attribute == null){
		                attribute = "<type=" + type +", var=" + var
		                +", value=" + value + ">";
		            }else{
		                attribute = attribute +"\n" + "<type=" + type +", var=" + var
		                +", value=" + value + ">";
		            }
				}
				String classEndName = "</fqn:"+cls.toString().substring(6,cls.toString().length())+">";
				serObjFormat = classStartName + "\n" + attribute  + "\n" + classEndName ;
				serObj.add(serObjFormat);
			}
			result.writeDataToFile(serObj);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception occured in DPML class");
			System.exit(1);
		} finally{
			
		}
	}
}
