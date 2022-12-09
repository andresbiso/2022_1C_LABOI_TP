package aplicacion.exception;

public class TextoValidatorException extends ValoresValidationException {

	public TextoValidatorException() {
		super();
	}

	public TextoValidatorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TextoValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public TextoValidatorException(String message) {
		super(message);
	}

	public TextoValidatorException(Throwable cause) {
		super(cause);
	}
   
}
