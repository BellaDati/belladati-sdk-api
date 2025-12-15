package com.belladati.sdk.exception.dataset.data;

/**
 * Thrown when attempting to insert more data than columns are available.
 * 
 * 
 */
public class TooManyColumnsException extends DataStructureException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -5930082188683421673L;

	public TooManyColumnsException(int actualColumns, int maxColumns) {
		super("Too many column values: found " + actualColumns + " content values for " + maxColumns + "columns.");
	}
}
