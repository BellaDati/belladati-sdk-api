package com.belladati.sdk.form;

import java.util.List;

import com.belladati.sdk.util.Resource;

/**
 * Import form used in BellaDati Data Collecting Module to import data into BellaDati.
 * @see <a href="http://support.belladati.com/doc/Data+Collection+Module">Data Collection Module</a>
 * 
 * @author Lubomir Elko
 */
public interface Form extends Resource {

	/**
	 * Returns the flag indicating if timestamp should be recorded for this form.
	 * 
	 * @return the flag indicating if timestamp should be recorded for this form
	 */
	boolean getRecordTimestamp();

	/**
	 * Returns a list of all form elements located in this form. You can use these
	 * elements to import data into BellaDati.
	 * 
	 * @return a list of all form elements located in this form
	 */
	List<FormElement> getElements();

}
