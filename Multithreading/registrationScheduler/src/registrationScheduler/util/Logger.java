package registrationScheduler.util;

public class Logger {

	public static enum DebugLevel {
		CONSTRUCTOR,
		RUN_CALL,
		NO_OUTPUT,
		RESULT_DATA,
		STORE_DATA
	};

	private static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 0:
			debugLevel = DebugLevel.NO_OUTPUT;
			break;
		case 1:
			debugLevel = DebugLevel.STORE_DATA;
			break;
		case 2:
			debugLevel = DebugLevel.RESULT_DATA;
			break;
		case 3:
			debugLevel = DebugLevel.RUN_CALL;
			break;
		case 4:
			debugLevel = DebugLevel.CONSTRUCTOR;
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
