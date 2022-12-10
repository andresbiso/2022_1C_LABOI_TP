package datos.exception;

@SuppressWarnings("serial")
public class TableManagerException extends DBException {

	public TableManagerException() {
		super();
	}

	public TableManagerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TableManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableManagerException(String message) {
		super(message);
	}

	public TableManagerException(Throwable cause) {
		super(cause);
	}

}
