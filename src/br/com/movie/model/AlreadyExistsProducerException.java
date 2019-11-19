package br.com.movie.model;

public class AlreadyExistsProducerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsProducerException() {

	}

	public AlreadyExistsProducerException(String message) {
		super(message);

	}

	public AlreadyExistsProducerException(Throwable cause) {
		super(cause);

	}

	public AlreadyExistsProducerException(String message, Throwable cause) {
		super(message, cause);

	}

	//Constructor JDK 1.7
	public AlreadyExistsProducerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
