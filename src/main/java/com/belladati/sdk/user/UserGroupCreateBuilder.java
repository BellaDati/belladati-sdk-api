package com.belladati.sdk.user;

import com.belladati.sdk.util.PostBuilder;

/**
 * Builder used to initiate user group object that should be created.
 * 
 * @author Lubomir Elko
 */
public interface UserGroupCreateBuilder extends PostBuilder {

	/**
	 * Sets the user group's display name.
	 * 
	 * @param name the user group's display name
	 */
	void setName(String name);

	/**
	 * Sets the user group's description.
	 * 
	 * @param description the user group's description
	 */
	void setDescription(String description);

}
