package aplicacion.exception;

public class TextoSoloLetrasException extends TextoValidatorException {

	public TextoSoloLetrasException() {
		super();
	}

	public TextoSoloLetrasException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TextoSoloLetrasException(String message, Throwable cause) {
		super(message, cause);
	}

	public TextoSoloLetrasException(String message) {
		super(message);
	}

	public TextoSoloLetrasException(Throwable cause) {
		super(cause);
	}

}
