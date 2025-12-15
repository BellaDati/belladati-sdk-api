package com.belladati.sdk;

import java.io.Serializable;

import com.belladati.sdk.auth.OAuthRequest;
import com.belladati.sdk.exception.ConnectionException;
import com.belladati.sdk.exception.auth.AuthorizationException;

/**
 * A connection to one specific BellaDati server. Use this connection to
 * authenticate via OAuth or xAuth and obtain a {@link BellaDatiService}
 * instance.
 * <p>
 * The same connection may be used to log in as multiple users, even in
 * parallel.
 * <p>
 * Connection instances may be serialized, saved, and restored for later use
 * with the same server. This will instantiate a new HTTP client.
 * 
 * 
 */
public interface BellaDatiConnection extends Serializable {

	/**
	 * Initiates OAuth authentication by requesting a request token from the
	 * BellaDati server.
	 * 
	 * @param consumerKey the consumer key configured in your BellaDati domain
	 * @param consumerSecret the consumer secret configured in your BellaDati
	 *            domain
	 * @return the ongoing OAuth authentication
	 * @throws ConnectionException if connection to the server fails
	 * @throws AuthorizationException if something went wrong during
	 *             authentication, check
	 *             {@link AuthorizationException#getReason()} for details
	 */
	OAuthRequest oAuth(String consumerKey, String consumerSecret) throws ConnectionException, AuthorizationException;

	/**
	 * Initiates OAuth authentication by requesting a request token from the
	 * BellaDati server.
	 * <p>
	 * After authorization, BellaDati redirects the user to the given URL. This
	 * needs to be a valid URL.
	 * 
	 * @param consumerKey the consumer key configured in your BellaDati domain
	 * @param consumerSecret the consumer secret configured in your BellaDati
	 *            domain
	 * @param redirectUrl BellaDati will redirect the user to this URL after
	 *            successful authorization
	 * @return the ongoing OAuth authentication
	 * @throws IllegalArgumentException if the redirect URL is not a valid URL
	 * @throws ConnectionException if connection to the server fails
	 * @throws AuthorizationException if something went wrong during
	 *             authentication, check
	 *             {@link AuthorizationException#getReason()} for details
	 */
	OAuthRequest oAuth(String consumerKey, String consumerSecret, String redirectUrl) throws IllegalArgumentException,
		ConnectionException, AuthorizationException;

	/**
	 * Authenticates to the BellaDati server using xAuth. To use xAuth, it must
	 * be enabled in your domain.
	 * <p>
	 * <b>Warning:</b> For security reasons, we recommend using OAuth for
	 * authentication. Use xAuth only if it is not possible to use a web browser
	 * for authentication in your workflow.
	 * 
	 * @param consumerKey the consumer key configured in your BellaDati domain
	 * @param consumerSecret the consumer secret configured in your BellaDati
	 *            domain
	 * @param username username of the user logging in
	 * @param password password of the user logging in
	 * @return a service instance accessing BellaDati as the user with the given
	 *         credentials
	 * @throws ConnectionException if connection to the server fails
	 * @throws AuthorizationException if something went wrong during
	 *             authentication, check
	 *             {@link AuthorizationException#getReason()} for details
	 */
	BellaDatiService xAuth(String consumerKey, String consumerSecret, String username, String password)
		throws ConnectionException, AuthorizationException;
}
