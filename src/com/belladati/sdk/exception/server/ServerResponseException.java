package com.belladati.sdk.exception.server;

import com.belladati.sdk.exception.BellaDatiRuntimeException;

/**
 * Indicates an unexpected response from the server, typically a HTTP 4xx or 5xx
 * error.
 * <p />
 * In most cases, a subclass of this exception is thrown depending on the type
 * of error that occurred. This exception itself is only used when an error
 * occurs that matches none of the subclasses.
 * 
 * @author Chris Hennigfeld
 */
public class ServerResponseException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -6829113009769255929L;

	public ServerResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerResponseException(String message) {
		super(message);
	}

	public ServerResponseException(Throwable cause) {
		super(cause);
	}
}
