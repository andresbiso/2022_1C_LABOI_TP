package aplicacion.exception;

public class TextoVacioException extends TextoValidatorException {

	public TextoVacioException() {
		super();
	}

	public TextoVacioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TextoVacioException(String message, Throwable cause) {
		super(message, cause);
	}

	public TextoVacioException(String message) {
		super(message);
	}

	public TextoVacioException(Throwable cause) {
		super(cause);
	}

}
