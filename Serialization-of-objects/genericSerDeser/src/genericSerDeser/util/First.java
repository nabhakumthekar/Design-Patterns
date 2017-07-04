package genericSerDeser.util;

import genericSerDeser.util.Logger;

public class First {
	
	private boolean BooleanValue;
	private byte ByteValue;
	private char CharValue;
	private double DoubleValue;
	private float FloatValue;
	private int IntValue;
	private long LongValue;
	private short ShortValue;
	private String StringValue;

	public boolean getBooleanValue() {
		return BooleanValue;
	}

	public byte getByteValue() {
		return ByteValue;
	}

	public char getCharValue() {
		return CharValue;
	}

	public double getDoubleValue() {
		return DoubleValue;
	}

	public float getFloatValue() {
		return FloatValue;
	}

	public int getIntValue() {
		return IntValue;
	}

	public long getLongValue() {
		return LongValue;
	}

	public short getShortValue() {
		return ShortValue;
	}

	public String getStringValue() {
		return StringValue;
	}

	public First() {
		Logger.writeMessage("In first class", Logger.DebugLevel.CONSTRUCTOR);
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
		IntValue = intValue_In;
	}

	public void setFloatValue2(float floatValue_In) {
		FloatValue = floatValue_In;
	}

	public void setShortValue2(short shortValue_In) {
		ShortValue = shortValue_In;
	}

	public void setStringValue2(String stringValue_In) {
		StringValue = stringValue_In;
	}

	public void setByteValue2(byte byteValue_In) {
		ByteValue = byteValue_In;
	}

	public void setLongValue2(long longValue_In) {
		LongValue = longValue_In;
	}

	public void setDoubleValue2(double doubleValue) {
		DoubleValue = doubleValue;
	}

	public void setBooleanValue2(boolean booleanValue) {
		BooleanValue = booleanValue;
	}

	public void setCharValue2(char charValue) {
		CharValue = charValue;
	}

	@Override
	public boolean equals(Object first) {
		if (first instanceof First) {
			First firstObj = (First) first;
			Boolean nullString;
			if( this.StringValue == null && firstObj.StringValue == null){
				nullString = true;
			}else{
				nullString = this.StringValue.equals(firstObj.StringValue);
			}
			return (this.ShortValue == firstObj.ShortValue
					&& nullString
					&& this.LongValue == firstObj.LongValue
					&& this.IntValue == firstObj.IntValue
					&& this.CharValue == firstObj.CharValue
					&& this.ByteValue == firstObj.ByteValue
					&& this.BooleanValue == firstObj.BooleanValue
					&& this.DoubleValue == firstObj.DoubleValue && this.FloatValue == firstObj.FloatValue);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int stringVal;
		if(this.StringValue == null){
			stringVal = 0;
		}else{
		stringVal = this.StringValue.hashCode();	
		}
		int result = (int) (Integer.hashCode(this.IntValue)
				+ Float.hashCode(this.FloatValue)
				+ Byte.hashCode(this.ByteValue)
				+ Character.hashCode(this.CharValue)
				+ Double.hashCode(this.DoubleValue)
				+ Long.hashCode(this.LongValue)
				+ Short.hashCode(this.ShortValue) 
				+ stringVal 
				+ Boolean.hashCode(this.BooleanValue));
		return result;
	}

}
