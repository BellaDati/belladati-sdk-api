package com.belladati.sdk.user;

import java.util.Date;
import java.util.Set;

import com.belladati.sdk.util.Resource;

/**
 * A BellaDati user.
 * 
 * 
 */
public interface User extends Resource {

	/**
	 * Returns the user's full display name, i.e. {@link #getGivenName()} +
	 * {@link #getFamilyName()}.
	 * 
	 * @return the user's full display name
	 */
	String getName();

	/**
	 * Returns the user's username, i.e. the name entered when logging in.
	 * 
	 * @return the user's username
	 */
	String getUsername();

	/**
	 * Returns the user's given name.
	 * 
	 * @return the user's given name
	 */
	String getGivenName();

	/**
	 * Returns the user's family name.
	 * 
	 * @return the user's family name
	 */
	String getFamilyName();

	/**
	 * Returns the user's email address.
	 * 
	 * @return the user's email address
	 */
	String getEmail();

	/**
	 * Returns the date and time of the user's first login, or null if
	 * the user has never logged in.
	 * 
	 * @return the date and time of the user's first login
	 */
	Date getFirstLogin();

	/**
	 * Returns the date and time of the user's most recent login, or
	 * null if the user has never logged in.
	 * 
	 * @return the date and time of the user's most recent login
	 */
	Date getLastLogin();

	/**
	 * Returns the user's time zone.
	 * 
	 * @return the user's time zone
	 */
	String getTimeZone();

	/**
	 * Returns the locale string set for the user.
	 * 
	 * @return the locale string set for the user
	 */
	String getLocale();

	/**
	 * Returns the active state of the user.
	 * 
	 * @return the active state of the user
	 * @see #loadStatus()
	 */
	boolean getActive();

	/**
	 * Loads the current active status of the user from BellaDati.
	 * 
	 * @return the current active status of the user
	 */
	String loadStatus();

	/**
	 * Posts the new active status of the user to BellaDati.
	 * 
	 * @param status the new active status of the user
	 */
	void postStatus(String status);

	/**
	 * Returns the ID of the domain where the user belongs.
	 * 
	 * @return the ID of the domain where the user belongs
	 */
	String getDomainId();

	/**
	 * Returns the user roles for the user.
	 * 
	 * @return the user roles for the user
	 */
	Set<UserRole> getUserRoles();

	/**
	 * Returns the user groups for the user.
	 * 
	 * @return the user groups for the user
	 */
	Set<UserGroup> getUserGroups();

	/**
	 * Creates an user request of desired type for this user.
	 * 
	 * @param requestType specifies the type of the request
	 * @return Request ID and request code of the created request separated by ";".
	 */
	String createUserRequest(UserRequestType requestType);

	/**
	 * Creates an access token for this user.
	 * 
	 * @param validity if set, specifies the validity (in seconds) of issued token
	 * @param domainId specifies the domain (in multi-domain deployments only) for which the access should be granted
	 * @return OAuth token and OAuth token secret separated by ";".
	 */
	String createAccessToken(Integer validity, String domainId);

}
