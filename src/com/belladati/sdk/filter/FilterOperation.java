package com.belladati.sdk.filter;

import com.belladati.sdk.BellaDatiService;
import com.belladati.sdk.dataset.Attribute;
import com.belladati.sdk.dataset.AttributeType;
import com.belladati.sdk.dataset.AttributeValue;
import com.belladati.sdk.filter.Filter.MultiValueFilter;
import com.belladati.sdk.filter.Filter.NoValueFilter;
import com.belladati.sdk.util.CachedList;

/**
 * Provides access to all available filter operations. Filter operations can be
 * used together with an attribute to create filters.
 * 
 * @author Chris Hennigfeld
 */
// this would be an enum, but we need different types so the compiler can
// distinguish between no-value and multi-value operations
public abstract class FilterOperation<F extends Filter<?>> {

	/** Operation that requires an attribute to be <tt>null</tt>. */
	public static final NoValueOperation NULL = new NoValueOperation("NULL");

	/** Operation that requires an attribute <b>not</b> to be <tt>null</tt>. */
	public static final NoValueOperation NOT_NULL = new NoValueOperation("NOT_NULL");

	/** Operation that requires an attribute's value to be in a list of values. */
	public static final MultiValueOperation IN = new MultiValueOperation("IN");

	/**
	 * Operation that requires an attribute's value <b>not</b> to be in a list
	 * of values.
	 */
	public static final MultiValueOperation NOT_IN = new MultiValueOperation("NOT_IN");

	/**
	 * Returns all available filter operations. This is similar to the
	 * <tt>values()</tt> method in an enum.
	 * 
	 * @return all available filter operations
	 */
	public static final FilterOperation<?>[] values() {
		return new FilterOperation<?>[] { NULL, NOT_NULL, IN, NOT_IN };
	}

	private final String op;

	private FilterOperation(String op) {
		this.op = op;
	}

	/**
	 * Creates a filter for the given attribute with this operation.
	 * 
	 * @param attribute the attribute to filter by
	 * @return a filter for the given attribute with this operation
	 */
	public abstract F createFilter(Attribute attribute);

	/**
	 * Creates a filter using just an attribute code. Can be used if the code is
	 * already known to avoid having to make an extra API call loading report
	 * attributes.
	 * 
	 * @param service service instance to connect to the server (to allow
	 *            retrieving the attribute's values)
	 * @param dataSetId ID of the data set the attribute is defined in
	 * @param attributeCode code of the attribute
	 * @return a filter for the given attribute with this operation
	 */
	public F createFilter(BellaDatiService service, String dataSetId, String attributeCode) {
		return createFilter(new FilterAttribute(service, dataSetId, attributeCode));
	}

	String getOp() {
		return op;
	}

	@Override
	public String toString() {
		return op;
	}

	/**
	 * Returns <tt>true</tt> if this filter operation uses attribute values.
	 * 
	 * @return <tt>true</tt> if this filter operation uses attribute values
	 */
	public boolean supportsValues() {
		return this instanceof MultiValueOperation;
	}

	/**
	 * Represents a filter operation that doesn't contain any attribute values.
	 * 
	 * @author Chris Hennigfeld
	 */
	public static class NoValueOperation extends FilterOperation<NoValueFilter> {
		private NoValueOperation(String op) {
			super(op);
		}

		@Override
		public NoValueFilter createFilter(Attribute attribute) {
			return new NoValueFilter(this, attribute);
		}
	}

	/**
	 * Represents a filter operation that may contain attribute values.
	 * 
	 * @author Chris Hennigfeld
	 */
	public static class MultiValueOperation extends FilterOperation<MultiValueFilter> {
		private MultiValueOperation(String op) {
			super(op);
		}

		@Override
		public MultiValueFilter createFilter(Attribute attribute) {
			return new MultiValueFilter(this, attribute);
		}
	}

	/**
	 * Internal Attribute implementation to dynamically create filter attributes
	 * that haven't been loaded through a report.
	 * 
	 * @author Chris Hennigfeld
	 */
	private class FilterAttribute implements Attribute {
		private final BellaDatiService service;

		private final String dataSetId;
		private final String code;

		private FilterAttribute(BellaDatiService service, String dataSetId, String code) {
			this.service = service;
			this.dataSetId = dataSetId;
			this.code = code;
		}

		@Override
		public String getId() {
			return null;
		}

		@Override
		public String getName() {
			return code;
		}

		@Override
		public String getCode() {
			return code;
		}

		@Override
		public AttributeType getType() {
			return AttributeType.TEXT;
		}

		@Override
		public CachedList<AttributeValue> getValues() {
			return service.getAttributeValues(dataSetId, code);
		}
	}
}