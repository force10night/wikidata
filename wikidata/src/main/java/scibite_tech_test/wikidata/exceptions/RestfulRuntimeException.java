package scibite_tech_test.wikidata.exceptions;

public class RestfulRuntimeException extends Exception {
	private static final long serialVersionUID = 6425542920595941730L;
	
	public RestfulRuntimeException() {
		super();
	}
	public RestfulRuntimeException(String message) {
		super(message);
	}
	public RestfulRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestfulRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	public RestfulRuntimeException(Throwable cause) {
		super(cause);
	}
}
