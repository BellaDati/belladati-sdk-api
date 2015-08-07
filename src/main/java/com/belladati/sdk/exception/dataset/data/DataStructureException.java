package com.belladati.sdk.exception.dataset.data;

import com.belladati.sdk.exception.BellaDatiRuntimeException;

/**
 * Indicates a problem with a data table, e.g. a mismatch between values and
 * columns.
 * 
 * @author Chris Hennigfeld
 */
abstract class DataStructureException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -9094186762531314044L;

	protected DataStructureException(String message) {
		super(message);
	}

}
