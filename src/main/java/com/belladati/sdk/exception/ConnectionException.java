package com.belladati.sdk.exception;

/**
 * Connection to the BellaDati server failed. If you see this, check the
 * following:
 * <ul>
 * <li>Your BellaDati server is running.</li>
 * <li>The BellaDati server URL is correct.</li>
 * <li>Your server's firewall allows access from the computer running the SDK.</li>
 * <li>The computer running the SDK doesn't block outgoing HTTP/HTTPS
 * connections.</li>
 * <li>If you're using a proxy, you may need to <a href=
 * "http://docs.oracle.com/javase/6/docs/technotes/guides/net/proxies.html"
 * >configure your JVM environment</a>.</li>
 * </ul>
 * 
 * 
 */
public class ConnectionException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 8778941786354544412L;

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

}
