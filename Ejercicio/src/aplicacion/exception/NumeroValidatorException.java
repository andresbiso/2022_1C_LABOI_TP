package aplicacion.exception;

@SuppressWarnings("serial")
public class NumeroValidatorException extends ValoresValidationException {

	public NumeroValidatorException() {
		super();
	}

	public NumeroValidatorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumeroValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumeroValidatorException(String message) {
		super(message);
	}

	public NumeroValidatorException(Throwable cause) {
		super(cause);
	}

}
