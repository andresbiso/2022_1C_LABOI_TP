package aplicacion.exception;

@SuppressWarnings("serial")
public class DatosModeloCreatorException extends Exception {

	public DatosModeloCreatorException() {
		super();
	}

	public DatosModeloCreatorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatosModeloCreatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatosModeloCreatorException(String message) {
		super(message);
	}

	public DatosModeloCreatorException(Throwable cause) {
		super(cause);
	}

}
