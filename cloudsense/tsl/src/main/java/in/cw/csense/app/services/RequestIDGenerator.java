package in.cw.csense.app.services;

public class RequestIDGenerator {

	private static Integer numericSuffix = 1000;
	
	public static String getRequestID(final String ownerName) {
		return ownerName + (numericSuffix++);
	}
}
