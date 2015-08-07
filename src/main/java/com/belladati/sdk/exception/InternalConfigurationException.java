package com.belladati.sdk.exception;

/**
 * Indicates that something went wrong with the internal configuration of the
 * SDK. This exception may indicate a bug in the SDK implementation. Please
 * contact BellaDati support if the problem persists.
 * 
 * @author Chris Hennigfeld
 */
public class InternalConfigurationException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 1415756247469683333L;

	public InternalConfigurationException(String message) {
		super(message);
	}

	public InternalConfigurationException(Throwable cause) {
		super(cause);
	}

	public InternalConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
