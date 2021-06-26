package sk.stuba.fei.uim.upb.exceptions;

public class CipherException extends RuntimeException {

	public CipherException(String message) {
		super(message);
	}

	public CipherException(Throwable cause) {
		super(cause);
	}

	public CipherException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
