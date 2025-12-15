package com.belladati.sdk.domain;

import com.belladati.sdk.user.User;
import com.belladati.sdk.user.UserGroup;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.Resource;

/**
 * A BellaDati domain.
 * 
 * 
 */
public interface Domain extends Resource {

	/**
	 * Returns the domain's display name.
	 * 
	 * @return the domain's display name
	 */
	String getName();

	/**
	 * Returns the domain's description.
	 * 
	 * @return the domain's description
	 */
	String getDescription();

	/**
	 * Returns the domain's date format.
	 * 
	 * @return the domain's date format
	 */
	String getDateFormat();

	/**
	 * Returns the domain's time format.
	 * 
	 * @return the domain's time format
	 */
	String getTimeFormat();

	/**
	 * Returns the domain's time zone.
	 * 
	 * @return the domain's time zone
	 */
	String getTimeZone();

	/**
	 * Returns the locale string set for the domain.
	 * 
	 * @return the locale string set for the domain
	 */
	String getLocale();

	/**
	 * Returns the active state of the domain.
	 * 
	 * @return the active state of the domain
	 */
	boolean getActive();

	/**
	 * Returns a list of users for the given user group. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param userGroupId optional ID of the user group whose users to access
	 * @return a list of all users for the given user group in the domain
	 */
	CachedList<User> loadUsers(String userGroupId);

	/**
	 * Returns a list of user groups. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @return a list of all user groups in the domain
	 */
	CachedList<UserGroup> loadUserGroups();

}
