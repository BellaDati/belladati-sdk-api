package com.belladati.sdk.exception.server;

/**
 * A response from the server couldn't be parsed as JSON even though the server
 * responded with HTTP 200. This most likely indicates a server-side error
 * creating the response content.
 * 
 * @author Chris Hennigfeld
 */
public class InvalidJsonException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 2902541340191469699L;

	public InvalidJsonException(String message) {
		super(message);
	}

	public InvalidJsonException(Throwable cause) {
		super(cause);
	}

	public InvalidJsonException(String message, Throwable cause) {
		super(message, cause);
	}

}
