package com.belladati.sdk.exception.server;

/**
 * Indicates a HTTP 404 error from the server. Most likely this is caused by
 * requesting an item ID that doesn't exist.
 * 
 * @author Chris Hennigfeld
 */
public class NotFoundException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -8834757786053458403L;

	private final String uri;

	/**
	 * Creates a new instance for the given URI.
	 * 
	 * @param uri the URI that couldn't be found
	 */
	public NotFoundException(String uri) {
		super("Server could not find resource: " + uri);
		this.uri = uri;
	}

	/**
	 * Returns the URI that couldn't be found.
	 * 
	 * @return the URI that couldn't be found
	 */
	public String getUri() {
		return uri;
	}
}
