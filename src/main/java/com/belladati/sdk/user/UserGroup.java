package com.belladati.sdk.user;

import com.belladati.sdk.util.Resource;

/**
 * A BellaDati user group.
 * 
 * 
 */
public interface UserGroup extends Resource {

	/**
	 * Returns the user group's full display name.
	 * 
	 * @return the user group's full display name
	 */
	String getName();

	/**
	 * Returns the user group's description.
	 * 
	 * @return the user group's description
	 */
	String getDescription();

}
