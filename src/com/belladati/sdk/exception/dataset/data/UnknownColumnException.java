package com.belladati.sdk.exception.dataset.data;

/**
 * Thrown when attempting to access a column that doesn't exist.
 * 
 * @author Chris Hennigfeld
 */
public class UnknownColumnException extends DataStructureException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 4871545977096378230L;

	public UnknownColumnException(String column) {
		super("Column '" + column + "' doesn't exist in this data row.");
	}

}
