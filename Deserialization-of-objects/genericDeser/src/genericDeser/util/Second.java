package genericDeser.util;

public class Second {
    private int IntValue;
    private float FloatValue;
    private short ShortValue;
    private String StringValue;
    private byte ByteValue;
    private long LongValue;
    private double DoubleValue;
    private boolean BooleanValue;
    private char CharValue;
    private int IntValue2;
    private float FloatValue2;
    private short ShortValue2;
    private String StringValue2;
    private byte ByteValue2;
    private long LongValue2;
    private double DoubleValue2;
    private boolean BooleanValue2;
    private char CharValue2;
    
    public  Second() {
        Logger.writeMessage("In second class", Logger.DebugLevel.CONSTRUCTOR);
    }
    public void setIntValue(int intValue_In) {
        IntValue = intValue_In;
    }
    
    public void setFloatValue(float floatValue_In) {
        FloatValue = floatValue_In;
    }
    
    public void setShortValue(short shortValue_In) {
        ShortValue = shortValue_In;
    }
    
    public void setStringValue(String stringValue_In) {
        StringValue = stringValue_In;
    }
    
    public void setByteValue(byte byteValue_In) {
        ByteValue = byteValue_In;
    }
    
    public void setLongValue(long longValue_In) {
        LongValue = longValue_In;
    }
    
    public void setDoubleValue(double doubleValue) {
        DoubleValue = doubleValue;
    }
    
    public void setBooleanValue(boolean booleanValue) {
        BooleanValue = booleanValue;
    }
    
    public void setCharValue(char charValue) {
        CharValue = charValue;
    }
    public void setIntValue2(int intValue_In) {
        IntValue2 = intValue_In;
    }

    public void setFloatValue2(float floatValue_In) {
        FloatValue2 = floatValue_In;
    }

    public void setShortValue2(short shortValue_In) {
        ShortValue2 = shortValue_In;
    }

    public void setStringValue2(String stringValue_In) {
        StringValue2 = stringValue_In;
    }

    public void setByteValue2(byte byteValue_In) {
        ByteValue2 = byteValue_In;
    }

    public void setLongValue2(long longValue_In) {
        LongValue2 = longValue_In;
    }

    public void setDoubleValue2(double doubleValue) {
        DoubleValue2 = doubleValue;
    }

    public void setBooleanValue2(boolean booleanValue) {
        BooleanValue2 = booleanValue;
    }

    public void setCharValue2(char charValue) {
        CharValue2 = charValue;
    }

    @Override
    public boolean equals(Object second) {
        if (second instanceof Second) {
            Second secondObj = (Second) second;
            return equalRetrunVal(secondObj);
        }
        return false;
    }
    
    private boolean equalRetrunVal(Second secondObj) {
        Boolean nullString;
        Boolean nullString2;
        Boolean retrunVal = false;
        try{
        if( this.StringValue == null && secondObj.StringValue == null){
            nullString = true;
        }else{
            nullString = this.StringValue.equals(secondObj.StringValue);
        }
        if( this.StringValue2 == null && secondObj.StringValue2 == null){
            nullString2 = true;
        }else{
            nullString2 = this.StringValue2.equals(secondObj.StringValue2);
        }
        retrunVal = this.ShortValue == secondObj.ShortValue
                && nullString
                && nullString2
                && this.LongValue == secondObj.LongValue
                && this.IntValue == secondObj.IntValue
                && this.CharValue == secondObj.CharValue
                && this.ByteValue == secondObj.ByteValue
                && this.BooleanValue == secondObj.BooleanValue
                && this.DoubleValue == secondObj.DoubleValue 
                && this.FloatValue == secondObj.FloatValue
                && this.LongValue2 == secondObj.LongValue2
                && this.IntValue2 == secondObj.IntValue2
                && this.CharValue2 == secondObj.CharValue2
                && this.ByteValue2 == secondObj.ByteValue2
                && this.BooleanValue2 == secondObj.BooleanValue2
                && this.DoubleValue2 == secondObj.DoubleValue2 
                && this.FloatValue2 == secondObj.FloatValue2;
        }catch(Exception e){
            System.err.println("Exception occured in equalRetrunVal method of Second class");
            e.printStackTrace();
            System.exit(1);
        }finally{
            
        }
        return retrunVal;
    }
    @Override
    public int hashCode() {
        int stringVal;
        int stringVal2;
        int result = 0;
        try{
            if(this.StringValue == null){
                stringVal = 0;
            }else{
            stringVal = this.StringValue.hashCode();    
            }
            if(this.StringValue2 == null){
                stringVal2 = 0;
            }else{
            stringVal2 = this.StringValue2.hashCode();  
            }
            result = (int) (Integer.hashCode(this.IntValue)
                    + Float.hashCode(this.FloatValue)
                    + Byte.hashCode(this.ByteValue)
                    + Character.hashCode(this.CharValue)
                    + Double.hashCode(this.DoubleValue)
                    + Long.hashCode(this.LongValue)
                    + Short.hashCode(this.ShortValue) 
                    + stringVal 
                    + Boolean.hashCode(this.BooleanValue2)
                    +Integer.hashCode(this.IntValue2)
                    + Float.hashCode(this.FloatValue2)
                    + Byte.hashCode(this.ByteValue2)
                    + Character.hashCode(this.CharValue2)
                    + Double.hashCode(this.DoubleValue2)
                    + Long.hashCode(this.LongValue2)
                    + Short.hashCode(this.ShortValue2) 
                    + stringVal2 
                    + Boolean.hashCode(this.BooleanValue2));
        }catch(Exception e){
            System.err.println("Exception occured in hashCode method of Second class");
            e.printStackTrace();
            System.exit(1);
        }finally{
            
        }
        return result;
    }
}
