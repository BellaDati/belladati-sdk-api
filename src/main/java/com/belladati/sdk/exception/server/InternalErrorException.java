package com.belladati.sdk.exception.server;

/**
 * Indicates an internal server error (HTTP 500) while processing an SDK
 * request. This indicates a bug or configuration problem on the server side.
 * Check the server logs to find out more details.
 * 
 * @author Chris Hennigfeld
 */
public class InternalErrorException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 2652275199888435572L;

	/**
	 * Creates a new instance.
	 */
	public InternalErrorException() {
		super("Server responded with an internal error");
	}
}
