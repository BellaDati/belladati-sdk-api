package com.belladati.sdk.exception;

/**
 * No SDK implementation was found or the SDK implementation doesn't match the
 * SDK API declaration. If you see this exception, check that you have the
 * implementation JAR on your classpath and it matches your SDK API version.
 * 
 * @author Chris Hennigfeld
 */
public class InvalidImplementationException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -1212994455970447440L;

	public InvalidImplementationException(String message, Throwable cause) {
		super(message, cause);
	}
}
