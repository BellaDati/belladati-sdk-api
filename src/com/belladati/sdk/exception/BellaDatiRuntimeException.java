package com.belladati.sdk.exception;

/**
 * Common superclass for all unchecked BellaDati exceptions. Catch this if you
 * want to ensure you're dealing with any SDK exceptions while still allowing
 * other RuntimeExceptions to go through.
 * 
 * @author Chris Hennigfeld
 */
public abstract class BellaDatiRuntimeException extends RuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -1248473202460066206L;

	protected BellaDatiRuntimeException(String message) {
		super(message);
	}

	protected BellaDatiRuntimeException(Throwable cause) {
		super(cause);
	}

	protected BellaDatiRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
