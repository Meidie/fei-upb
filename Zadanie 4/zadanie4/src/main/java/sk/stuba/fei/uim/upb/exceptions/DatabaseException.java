package sk.stuba.fei.uim.upb.exceptions;

public class DatabaseException extends RuntimeException {

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

	public DatabaseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
