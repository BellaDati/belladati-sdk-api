package com.belladati.sdk.exception.dataset.data;

/**
 * Thrown when attempting write a column on the server that doesn't exist in the
 * data set.
 * 
 * 
 */
public class UnknownServerColumnException extends DataStructureException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 4871545977096378230L;

	private final String id;
	private final String column;

	public UnknownServerColumnException(String id, String column) {
		super("Column '" + column + "' doesn't exist in the data set " + id + ".");
		this.id = id;
		this.column = column;
	}

	/**
	 * Returns the ID of the data set.
	 * 
	 * @return the ID of the data set
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the column code that wasn't found.
	 * 
	 * @return the column code that wasn't found
	 */
	public String getColumn() {
		return column;
	}
}
