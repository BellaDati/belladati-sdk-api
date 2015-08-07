package com.belladati.sdk.exception.server;

import com.belladati.sdk.exception.BellaDatiRuntimeException;

public abstract class ServerResponseException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -6829113009769255929L;

	protected ServerResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ServerResponseException(String message) {
		super(message);
	}

	protected ServerResponseException(Throwable cause) {
		super(cause);
	}
}
