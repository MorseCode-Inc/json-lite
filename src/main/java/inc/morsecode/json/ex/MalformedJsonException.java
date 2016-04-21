package inc.morsecode.json.ex;

public class MalformedJsonException extends Exception {

	public MalformedJsonException(String message) {
		super(message);
	}

	public MalformedJsonException(Throwable cause) {
		super(cause);
	}

	public MalformedJsonException(String message, Throwable cause) {
		super(message, cause);
	}

}
