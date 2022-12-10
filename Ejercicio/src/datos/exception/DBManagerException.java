package datos.exception;

@SuppressWarnings("serial")
public class DBManagerException extends DBException {

	public DBManagerException() {
		super();
	}

	public DBManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBManagerException(String message) {
		super(message);
	}

	public DBManagerException(Throwable cause) {
		super(cause);
	}

}
