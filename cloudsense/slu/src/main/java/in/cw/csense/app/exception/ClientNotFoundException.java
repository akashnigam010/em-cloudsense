package in.cw.csense.app.exception;

public class ClientNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(final String message) {
		super(message);
	}
}
