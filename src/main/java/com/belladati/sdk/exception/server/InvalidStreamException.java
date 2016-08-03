package com.belladati.sdk.exception.server;

/**
 * A response from the server couldn't be parsed as stream even though the server
 * responded with HTTP 200. This most likely indicates a server-side error
 * creating the response content.
 * 
 * @author Lubomir Elko
 */
public class InvalidStreamException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -4888707398097436788L;

	public InvalidStreamException(String message) {
		super(message);
	}

	public InvalidStreamException(Throwable cause) {
		super(cause);
	}

	public InvalidStreamException(String message, Throwable cause) {
		super(message, cause);
	}

}
