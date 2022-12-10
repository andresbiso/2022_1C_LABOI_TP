package aplicacion.exception;

public class FechaValidatorException extends ValoresValidationException {

	public FechaValidatorException() {
		super();
	}

	public FechaValidatorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FechaValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public FechaValidatorException(String message) {
		super(message);
	}

	public FechaValidatorException(Throwable cause) {
		super(cause);
	}

}
