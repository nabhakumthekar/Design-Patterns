package spreadsheetUpdates.util;

public class Logger {

	public static enum DebugLevel {
		CONSTRUCTOR,
		FILE_EMPTY,
		NO_OUTPUT,
		PRINT_SUM,
		PRINT_CYCLES
	};

	private static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 0:
			debugLevel = DebugLevel.NO_OUTPUT;
			break;
		case 1:
			debugLevel = DebugLevel.PRINT_SUM;
			break;
		case 2:
			debugLevel = DebugLevel.PRINT_CYCLES;
			break;
		case 3:
			debugLevel = DebugLevel.FILE_EMPTY;
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
