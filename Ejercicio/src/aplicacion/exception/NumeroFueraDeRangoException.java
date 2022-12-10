package aplicacion.exception;

public class NumeroFueraDeRangoException extends NumeroValidatorException {

	public NumeroFueraDeRangoException() {
		super();
	}

	public NumeroFueraDeRangoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumeroFueraDeRangoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumeroFueraDeRangoException(String message) {
		super(message);
	}

	public NumeroFueraDeRangoException(Throwable cause) {
		super(cause);
	}

}
