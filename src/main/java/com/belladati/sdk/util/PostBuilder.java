package com.belladati.sdk.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Builder used to initiate object that should be send to BellaDati via POST request.
 * 
 * 
 */
public interface PostBuilder {

	/**
	 * Returns a JSON representation of this object to send to the server. Used by the SDK internally.
	 * Modifications done in returned JSON will be not send to the server, please use appropriate methods
	 * to set request parameters and attributes.
	 * 
	 * @return a JSON representation of this object
	 */
	JsonNode toJson();

	/**
	 * Submits configured JSON representation to the server as a POST operation.
	 * You can submit request only once.
	 * 
	 * @return String response
	 */
	String post();

}
