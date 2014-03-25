package com.belladati.sdk.auth;

import java.io.Serializable;
import java.net.URL;

import com.belladati.sdk.BellaDatiService;
import com.belladati.sdk.exception.auth.AuthorizationException;

/**
 * An ongoing OAuth authentication. To continue authentication, open the URL
 * obtained from {@link #getAuthorizationUrl()} in a browser and enter username
 * and password. After successful authentication, call {@link #requestAccess()}
 * to gain access to the server via SDK.
 * 
 * @author Chris Hennigfeld
 */
public interface OAuthRequest extends Serializable {

	/**
	 * Returns the URL to authorize this request. Open this URL in a web browser
	 * to allow the user to log in to BellaDati and grant access to your client
	 * application.
	 * 
	 * @return the URL to authorize this request
	 */
	URL getAuthorizationUrl();

	/**
	 * Returns the URL to authorize this request. Open this URL in a web browser
	 * to allow the user to log in to BellaDati and grant access to your client
	 * application.
	 * <p>
	 * After authorization, BellaDati redirects the user to the given URL. This
	 * needs to be a valid URL.
	 * 
	 * @param redirectUrl BellaDati will redirect the user to this URL after
	 *            successful authorization
	 * @return the URL to authorize this request
	 * @throws IllegalArgumentException if the redirect URL is not a valid URL
	 */
	URL getAuthorizationUrl(String redirectUrl) throws IllegalArgumentException;

	/**
	 * Requests an access token using this OAuth request. Call this method only
	 * after the user has authorized the request by logging in at the
	 * authorization URL.
	 * 
	 * @return a service instance accessing BellaDati as this request's user
	 * @throws AuthorizationException if something went wrong during
	 *             authentication; check
	 *             {@link AuthorizationException#getReason()} for details
	 */
	BellaDatiService requestAccess() throws AuthorizationException;
}
