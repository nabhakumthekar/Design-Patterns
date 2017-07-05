package genericSerDeser.util;

public class Logger {

	public static enum DebugLevel {
		CONSTRUCTOR,
		INSTANCES,
		NO_OUTPUT,
		DESEROBJECT,
		FILE
	};

	private static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 0:
			debugLevel = DebugLevel.NO_OUTPUT;
			break;
		case 1:
			debugLevel = DebugLevel.INSTANCES;
			break;
		case 2:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 3:
			debugLevel = DebugLevel.DESEROBJECT;
			break;
		case 4:
			debugLevel = DebugLevel.FILE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}
