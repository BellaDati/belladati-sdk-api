package com.belladati.sdk.user;

import java.util.Date;

import com.belladati.sdk.util.Resource;

/**
 * A BellaDati user.
 * 
 * @author Chris Hennigfeld
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
	 * Returns the date and time of the user's first login, or <tt>null</tt> if
	 * the user has never logged in.
	 * 
	 * @return the date and time of the user's first login
	 */
	Date getFirstLogin();

	/**
	 * Returns the date and time of the user's most recent login, or
	 * <tt>null</tt> if the user has never logged in.
	 * 
	 * @return the date and time of the user's most recent login
	 */
	Date getLastLogin();

	/**
	 * Returns the locale string set for the user.
	 * 
	 * @return the locale string set for the user
	 */
	String getLocale();
}