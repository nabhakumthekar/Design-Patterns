package genericSerDeser.util;

public class Second {
	
	private double DoubleValue;
	private double DoubleValue2;
	private long LongValue;
	private long LongValue2;
	private short ShortValue;
	private short ShortValue2;
	private String StringValue;
	
	public  Second() {
		Logger.writeMessage("In second class", Logger.DebugLevel.CONSTRUCTOR);
	}
	public double getDoubleValue() {
		return DoubleValue;
	}

	public double getDoubleValue2() {
		return DoubleValue2;
	}

	public long getLongValue() {
		return LongValue;
	}

	public long getLongValue2() {
		return LongValue2;
	}

	public short getShortValue() {
		return ShortValue;
	}

	public short getShortValue2() {
		return ShortValue2;
	}

	public String getStringValue() {
		return StringValue;
	}

	public void setShortValue(short shortValue_In) {
		ShortValue = shortValue_In;
	}

	public void setStringValue(String stringValue_In) {
		StringValue = stringValue_In;
	}


	public void setLongValue(long longValue_In) {
		LongValue = longValue_In;
	}

	public void setDoubleValue(double doubleValue) {
		DoubleValue = doubleValue;
	}

	
	public void setShortValue2(short shortValue_In) {
		ShortValue2 = shortValue_In;
	}

	public void setLongValue2(long longValue_In) {
		LongValue2 = longValue_In;
	}

	public void setDoubleValue2(double doubleValue) {
		DoubleValue2 = doubleValue;
	}

	@Override
	public boolean equals(Object second) {
		if (second instanceof Second) {
			Second secondObj = (Second) second;
			return equalRetrunVal(secondObj);
		}
		return false;
	}
	
	public boolean equalRetrunVal(Second secondObj) {
		Boolean nullString;
		if( this.StringValue == null && secondObj.StringValue == null){
			nullString = true;
		}else{
			nullString = this.StringValue.equals(secondObj.StringValue);
		}
		Boolean retrunVal = this.ShortValue == secondObj.ShortValue
				&& nullString
				&& this.LongValue == secondObj.LongValue
				&& this.DoubleValue == secondObj.DoubleValue 
				&& this.LongValue2 == secondObj.LongValue2
				&& this.DoubleValue2 == secondObj.DoubleValue2;
		return retrunVal;
	}
	@Override
	public int hashCode() {
		int stringVal;
		if(this.StringValue == null){
			stringVal = 0;
		}else{
		stringVal = this.StringValue.hashCode();	
		}
		int result = (int) Double.hashCode(this.DoubleValue)
				+ Long.hashCode(this.LongValue)
				+ Short.hashCode(this.ShortValue) 
				+ stringVal
				+ Double.hashCode(this.DoubleValue2)
				+ Long.hashCode(this.LongValue2)
				+ Short.hashCode(this.ShortValue2);
		return result;
	}
}
