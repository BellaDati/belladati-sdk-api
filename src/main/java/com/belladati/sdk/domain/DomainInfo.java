package com.belladati.sdk.domain;

import com.belladati.sdk.user.User;
import com.belladati.sdk.user.UserGroup;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.ResourceInfo;

/**
 * Information object about a BellaDati domain. Use this to get meta-information
 * about the domain. To retrieve the domain users call {@link #loadUsers(String)} and
 * the domain user groups call {@link #loadUserGroups()}.
 * 
 * @author Lubomir Elko
 */
public interface DomainInfo extends ResourceInfo<Domain> {

	/**
	 * Returns the associated domain's description.
	 * 
	 * @return the associated domain's description, or an empty string if no
	 *         description exists
	 */
	String getDescription();

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
