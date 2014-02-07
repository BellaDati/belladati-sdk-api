package com.belladati.sdk.report;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.filter.FilterOperation;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.view.View;

/**
 * An attribute from a BellaDati data set. Attributes can be used to filter the
 * data used in reports by certain attribute values.
 * <p />
 * Refer to the {@link Filter} and {@link FilterOperation} classes to create
 * filters, and to {@link View#loadContent(Filter...)} to use them loading
 * report views.
 * 
 * @author Chris Hennigfeld
 */
public interface Attribute {

	/**
	 * Returns the human-readable name of this attribute.
	 * 
	 * @return the human-readable name of this attribute
	 */
	String getName();

	/**
	 * Returns the internal code of this attribute.
	 * 
	 * @return the internal code of this attribute
	 */
	String getCode();

	/**
	 * Returns the possible values for this attribute. This is a cached list,
	 * call {@link CachedCollection#load()} to populate it initially.
	 * 
	 * @return all possible values for this attribute
	 */
	CachedList<AttributeValue> getValues();
}
