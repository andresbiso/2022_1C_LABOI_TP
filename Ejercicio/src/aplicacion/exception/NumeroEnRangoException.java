package aplicacion.exception;

@SuppressWarnings("serial")
public class NumeroEnRangoException extends NumeroValidatorException {

	public NumeroEnRangoException() {
		super();
	}

	public NumeroEnRangoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumeroEnRangoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumeroEnRangoException(String message) {
		super(message);
	}

	public NumeroEnRangoException(Throwable cause) {
		super(cause);
	}

}
