package com.belladati.sdk.exception.server;

/**
 * Indicates a HTTP 405 error from the server. Usually it means that given
 * operation is not supported for given inputs. E.g. you cannot export certain
 * types of views
 * 
 * 
 */
public class MethodNotAllowedException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -8834757786053458403L;

	private final String uri;

	/**
	 * Creates a new instance for the given URI.
	 * 
	 * @param uri the URI that couldn't be found
	 */
	public MethodNotAllowedException(String uri) {
		super("Method is not allowed for uri: " + uri);
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
