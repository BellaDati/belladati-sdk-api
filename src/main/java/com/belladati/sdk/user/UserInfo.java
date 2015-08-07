package com.belladati.sdk.user;

import java.io.IOException;

import com.belladati.sdk.util.ResourceInfo;

/**
 * Information object about a BellaDati user. To retrieve user details, call
 * {@link #loadDetails()}.
 * 
 * @author Chris Hennigfeld
 */
public interface UserInfo extends ResourceInfo<User> {

	/**
	 * Returns the user's full display name.
	 * 
	 * @return the user's full display name
	 */
	String getName();

	/**
	 * Loads the associated user's profile image. The Java type of the image
	 * being returned depends on the implementation.
	 * 
	 * @return the associated user's profile image
	 * @throws IOException if no image exists or it cannot be loaded
	 */
	Object loadImage() throws IOException;
}
