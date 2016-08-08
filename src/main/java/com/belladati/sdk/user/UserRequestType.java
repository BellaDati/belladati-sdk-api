package com.belladati.sdk.user;

/**
 * Specifies the type of the user request.
 * 
 * @author Lubomir Elko
 */
public enum UserRequestType {

	/** Login. */
	LOGIN,

	/** Unattended login. */
	LOGIN_UNATTENDED,

	/** Set of new password. */
	PASSWORD_SET,

	/** Reset of current password. */
	PASSWORD_RESET,

	/** Unlock account. */
	UNLOCK_ACCOUNT;

}
