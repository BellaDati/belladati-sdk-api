package com.belladati.sdk.user;

import com.belladati.sdk.util.PostBuilder;

/**
 * Builder used to initiate user object that should be created.
 * 
 * 
 */
public interface UserCreateBuilder extends PostBuilder {

	/**
	 * Sets the user's username.
	 * 
	 * @param username the user's username
	 */
	void setUsername(String username);

	/**
	 * Sets the user's email.
	 * 
	 * @param email the user's email
	 */
	void setEmail(String email);

	/**
	 * Sets the user's time first name.
	 * 
	 * @param firstName the user's first name
	 */
	void setFirstName(String firstName);

	/**
	 * Sets the user's time last name.
	 * 
	 * @param lastName the user's last name
	 */
	void setLastName(String lastName);

	/**
	 * Sets the user's time zone.
	 * 
	 * @param timeZone the user's time zone
	 */
	void setTimeZone(String timeZone);

	/**
	 * Sets the user's locale.
	 * 
	 * @param locale the user's locale
	 */
	void setLocale(String locale);

	/**
	 * Sets the user's roles.
	 * 
	 * @param roles the user's roles
	 */
	void setRoles(UserRole... roles);

	/**
	 * Sets the user's user group associations.
	 * 
	 * @param userGroupIds the array of user group IDs
	 */
	void setUserGroups(String... userGroupIds);

}
