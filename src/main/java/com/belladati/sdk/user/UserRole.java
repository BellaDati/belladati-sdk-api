package com.belladati.sdk.user;

/**
 * Indicates the role of an user.
 * 
 * @author Lubomir Elko
 */
public enum UserRole {

	/** Global/System administrator. */
	ADMIN("ADMIN"),

	/** Domain administrator. */
	DOMAIN_ADMIN("WORKSPACE_ADMIN"),

	/** Data Manager. */
	DATA_MANAGER("DATASET_ADMIN"),

	/** Report Editor. */
	REPORT_EDITOR("REPORT_ADMIN");

	private final String jsonRole;

	private UserRole(String jsonRole) {
		this.jsonRole = jsonRole;
	}

	public String getJsonRole() {
		return jsonRole;
	}

	/**
	 * Returns the attribute type matching the given string type from JSON. This
	 * method is used by the SDK internally.
	 * 
	 * @param jsonRole string type from JSON
	 * @return the attribute type matching the given string type from JSON
	 */
	public static UserRole valueOfJson(String jsonRole) {
		for (UserRole role : values()) {
			if (role.jsonRole.equalsIgnoreCase(jsonRole)) {
				return role;
			}
		}
		return null;
	}

}
