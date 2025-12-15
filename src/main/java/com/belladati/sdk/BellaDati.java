package com.belladati.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.belladati.sdk.exception.InvalidImplementationException;

/**
 * Serves as the entry point to the BellaDati SDK. Use either of the connect
 * methods to connect to a server, then authenticate to access a user's data.
 * <p>
 * It is recommended to use only one {@link BellaDatiConnection} per server to
 * allow reuse of connection resources. Most client applications will connect
 * only to a single BellaDati server - BellaDati cloud or an on-premise
 * installation - meaning only one connection should be used.
 * <p>
 * The SDK uses default timeouts of 10 seconds which should work fine for most
 * servers and internet connections. For environments that require different
 * settings, timeouts and connection management can be configured using system
 * properties:
 * <ul>
 * <li><strong>bdTimeout</strong>: Sets all timeouts without an individual
 * setting to the given value.</li>
 * <li><strong>bdConnectionRequestTimeout</strong>: Timeout for getting a
 * connection from the local connection manager.</li>
 * <li><strong>bdConnectTimeout</strong>: Timeout for establishing a connection
 * to the server.</li>
 * <li><strong>bdSocketTimeout</strong>: Timeout while waiting for data from the
 * server.</li>
 * <li><strong>bdMaxConnections</strong>: Maximum number of simultaneous API
 * connections. Defaults to 40, which should be plenty for most applications. If
 * your application sends large amounts of concurrent requests, local caching
 * may result in better performance than increasing this setting.</li>
 * </ul>
 * All timeouts are set in milliseconds. These properties only affect new
 * connections being created and don't change existing connections. If needed,
 * set the timeouts before calling any of the {@link #connect()} methods.
 * 
 * 
 * 
 */
public class BellaDati {

	/**
	 * Connects to the BellaDati cloud service.
	 * 
	 * @return a connection to the BellaDati cloud service
	 */
	public static BellaDatiConnection connect() {
		return connect("https://service.belladati.com/");
	}

	/**
	 * Connects to a BellaDati server hosted at the specified URL.
	 * 
	 * @param baseUrl URL of the BellaDati server
	 * @return a connection to the BellaDati server hosted at the specified URL
	 */
	public static BellaDatiConnection connect(String baseUrl) {
		return connect(baseUrl, false);
	}

	/**
	 * Connects to a BellaDati server hosted at the specified URL. This
	 * connection accepts servers using self-signed SSL certificates.
	 * <p>
	 * <b>Warning:</b> Avoid using this type of connection whenever possible.
	 * When using a server without a certificate signed by a known certificate
	 * authority, an attacker could impersonate your server and intercept
	 * passwords or sensitive data sent by the SDK.
	 * 
	 * @param baseUrl URL of the BellaDati server
	 * @return a connection to the BellaDati server hosted at the specified URL
	 */
	public static BellaDatiConnection connectInsecure(String baseUrl) {
		return connect(baseUrl, true);
	}

	private static BellaDatiConnection connect(String baseUrl, boolean trustSelfSigned) {
		try {
			return (BellaDatiConnection) getConnectionConstructor().newInstance(baseUrl, trustSelfSigned);
		} catch (ClassNotFoundException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (NoSuchMethodException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (InvocationTargetException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (IllegalAccessException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (InstantiationException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (IllegalArgumentException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (SecurityException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		} catch (ClassCastException e) {
			throw new InvalidImplementationException("Failed to instantiate connection", e);
		}
	}

	/**
	 * Reflectively loads the implementation's constructor to open a
	 * {@link BellaDatiConnection}.
	 * 
	 * @return the constructor of a {@link BellaDatiConnection} implementation
	 * @throws ClassNotFoundException if the implementing class isn't found
	 * @throws NoSuchMethodException if no constructor exists for the expected
	 *             arguments
	 * @throws SecurityException if access is denied
	 */
	private static Constructor<?> getConnectionConstructor() throws ClassNotFoundException, NoSuchMethodException,
		SecurityException {
		Class<?> clazz = Class.forName("com.belladati.sdk.impl.BellaDatiConnectionImpl");
		Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, Boolean.TYPE);
		constructor.setAccessible(true);
		return constructor;
	}
}
