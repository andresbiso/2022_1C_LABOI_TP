package aplicacion.exception;

public class ValoresValidationException extends Exception {

	public ValoresValidationException() {
		super();
	}

	public ValoresValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValoresValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValoresValidationException(String message) {
		super(message);
	}

	public ValoresValidationException(Throwable cause) {
		super(cause);
	}

}
