package com.belladati.sdk.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.belladati.sdk.dataset.Attribute;
import com.belladati.sdk.dataset.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A filter that can be used when loading a view's content. Filters allow you to
 * select the records to include from your data in the same way as you can
 * filter in the BellaDati web frontend.
 * <p>
 * This class cannot be instantiated directly; use
 * {@link FilterOperation#createFilter(Attribute)} on one of the static
 * operations in {@link FilterOperation} instead.
 * 
 * @author Chris Hennigfeld
 */
public abstract class Filter<F extends Filter<?>> {

	/** the filter's operation */
	private final FilterOperation<F> operation;
	/** the attribute the filter is applied to */
	private final Attribute attribute;

	private Filter(FilterOperation<F> operation, Attribute attribute) {
		this.operation = operation;
		this.attribute = attribute;
	}

	/**
	 * Returns the operation used by this filter.
	 * 
	 * @return the operation used by this filter
	 */
	public FilterOperation<F> getOperation() {
		return operation;
	}

	/**
	 * Returns the attribute used by this filter.
	 * 
	 * @return the attribute used by this filter
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * Returns a JSON representation of this filter to send to the server. Used
	 * by the SDK internally.
	 * 
	 * @return a JSON representation of this filter
	 */
	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ObjectNode attributeNode = mapper.createObjectNode();

		node.put(attribute.getCode(), attributeNode);
		attributeNode.put("op", operation.getOp());

		return node;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Filter) {
			return toJson().equals(((Filter<?>) obj).toJson());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return toJson().hashCode();
	}

	/**
	 * A filter that contains only an attribute and an operation, but no values.
	 * An example is to filter for an attribute to be <tt>null</tt>.
	 * 
	 * @author Chris Hennigfeld
	 */
	public static class NoValueFilter extends Filter<NoValueFilter> {

		NoValueFilter(FilterOperation<NoValueFilter> operation, Attribute attribute) {
			super(operation, attribute);
		}
	}

	/**
	 * A filter that contains an attribute and an operation, and a set of
	 * values. An example is to filter for an attribute containing one of a set
	 * of values.
	 * 
	 * @author Chris Hennigfeld
	 */
	public static class MultiValueFilter extends Filter<MultiValueFilter> {
		private final Set<AttributeValue> values = new HashSet<AttributeValue>();

		MultiValueFilter(FilterOperation<MultiValueFilter> operation, Attribute attribute) {
			super(operation, attribute);
		}

		/**
		 * Returns the set of attribute values used in this filter. This set is
		 * mutable, any changes made to the set will directly affect the filter.
		 * 
		 * @return the set of attribute values used in this filter
		 */
		public Set<AttributeValue> getValues() {
			return values;
		}

		/**
		 * Adds a value to this filter's value set.
		 * 
		 * @param value the value to add
		 * @return this filter, to allow chaining
		 */
		public MultiValueFilter addValue(AttributeValue value) {
			values.add(value);
			return this;
		}

		/**
		 * Adds multiple values to this filter's value set.
		 * 
		 * @param values the values to add
		 * @return this filter, to allow chaining
		 */
		public MultiValueFilter addAll(AttributeValue... values) {
			return addAll(Arrays.asList(values));
		}

		/**
		 * Adds multiple values to this filter's value set.
		 * 
		 * @param values the values to add
		 * @return this filter, to allow chaining
		 */
		public MultiValueFilter addAll(Collection<AttributeValue> values) {
			this.values.addAll(values);
			return this;
		}

		@Override
		public ObjectNode toJson() {
			ObjectNode node = super.toJson();
			ArrayNode filterValues = new ObjectMapper().createArrayNode();
			for (AttributeValue value : values) {
				filterValues.add(value.getValue());
			}
			((ObjectNode) node.get(getAttribute().getCode())).put("values", filterValues);
			return node;
		}
	}
}
