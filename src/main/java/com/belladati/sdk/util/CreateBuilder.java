package com.belladati.sdk.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Builder used to initiate object that should be created.
 * 
 * @author Lubomir Elko
 */
public interface CreateBuilder {

	/**
	 * Returns a JSON representation of this object to send to the server. Used by the SDK internally.
	 * 
	 * @return a JSON representation of this object
	 */
	JsonNode toJson();

	/**
	 * Submits configured JSON representation to the server as a create operation.
	 * 
	 * @return ID of the newly created object
	 */
	String post();

}
