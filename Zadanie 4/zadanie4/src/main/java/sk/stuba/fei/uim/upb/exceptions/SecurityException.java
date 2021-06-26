package sk.stuba.fei.uim.upb.exceptions;

public class SecurityException extends RuntimeException {

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
