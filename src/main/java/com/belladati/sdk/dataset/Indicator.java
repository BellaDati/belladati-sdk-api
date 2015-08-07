package com.belladati.sdk.dataset;

import com.belladati.sdk.util.Resource;

/**
 * An indicator from a BellaDati data set. Indicators can contain raw values,
 * formulas, or other indicators as a group.
 * 
 * @author Chris Hennigfeld
 */
public interface Indicator extends Resource {

	/**
	 * Returns the type of this indicator.
	 * 
	 * @return the type of this indicator
	 */
	IndicatorType getType();

	/**
	 * Returns the internal code of this data indicator, or <tt>null</tt> if
	 * this indicator is not of type {@link IndicatorType#DATA}.
	 * 
	 * @return the internal code of this data indicator
	 */
	String getCode();

	/**
	 * Returns the formula used in this formula indicator, or <tt>null</tt> if
	 * this indicator is not of type {@link IndicatorType#FORMULA}.
	 * 
	 * @return the formula used in this formula indicator
	 */
	String getFormula();
}
