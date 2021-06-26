package sk.stuba.fei.uim.upb.exceptions;

public class DictionaryException extends RuntimeException {

	public DictionaryException(String message) {
		super(message);
	}

	public DictionaryException(Throwable cause) {
		super(cause);
	}

	public DictionaryException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
