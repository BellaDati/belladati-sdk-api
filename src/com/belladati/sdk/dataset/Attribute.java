package com.belladati.sdk.dataset;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.filter.FilterOperation;
import com.belladati.sdk.util.Resource;
import com.belladati.sdk.view.View;

/**
 * An attribute from a BellaDati data set. Attributes can contain data in
 * various formats.
 * <p />
 * Attributes can be used to filter the data used in reports by certain
 * attribute values. Refer to the {@link Filter} and {@link FilterOperation}
 * classes to create filters, and to {@link View#loadContent(Filter...)} to use
 * them loading report views.
 * 
 * @author Chris Hennigfeld
 */
public interface Attribute extends com.belladati.sdk.report.Attribute, Resource {

	/**
	 * Returns the type of this attribute.
	 * 
	 * @return the type of this attribute
	 */
	AttributeType getType();
}
