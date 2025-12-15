package com.belladati.sdk.exception.server;

/**
 * Indicates an unexpected response from the server, typically a HTTP 4xx or 5xx
 * error. This particular exception occurs if no other exception matches the
 * error returned by the server.
 * 
 * 
 */
public class UnexpectedResponseException extends ServerResponseException {

	private final int responseCode;
	private final String responseContent;

	/** The serialVersionUID */
	private static final long serialVersionUID = -6829113009769255929L;

	public UnexpectedResponseException(int responseCode, String responseContent, Throwable cause) {
		super("Unexpected response with code " + responseCode + ": " + responseContent, cause);
		this.responseCode = responseCode;
		this.responseContent = responseContent;
	}

	public UnexpectedResponseException(int responseCode, String responseContent) {
		super("Unexpected response with code " + responseCode + ": " + responseContent);
		this.responseCode = responseCode;
		this.responseContent = responseContent;
	}

	/**
	 * Returns the response code received by the server.
	 * 
	 * @return the response code received by the server
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Returns the response content received by the server.
	 * 
	 * @return the response content received by the server
	 */
	public String getResponseContent() {
		return responseContent;
	}
}
