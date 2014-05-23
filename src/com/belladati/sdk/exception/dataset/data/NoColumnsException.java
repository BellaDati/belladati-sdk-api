package com.belladati.sdk.exception.dataset.data;

/**
 * Thrown when attempting to create a data table without any columns.
 * 
 * @author Chris Hennigfeld
 */
public class NoColumnsException extends DataStructureException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -5930082188683421673L;

	public NoColumnsException() {
		super("Data table must have at least one column.");
	}
}
