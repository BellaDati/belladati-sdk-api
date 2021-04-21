package com.belladati.sdk.dataset.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.belladati.sdk.exception.dataset.data.NoColumnsException;
import com.belladati.sdk.exception.interval.InvalidAbsoluteIntervalException;
import com.belladati.sdk.intervals.DateUnit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Indicates what to do with existing data during the import. Options include
 * doing nothing, deleting or overwriting selected records, and clearing all
 * data.
 * 
 * @author Chris Hennigfeld
 */
public abstract class OverwritePolicy {

	private static final String DELETE_ALL = "DELETE_ALL";
	private static final String DELETE_BY_MEMBERS = "DELETE_BY_MEMBERS";
	private static final String UPDATE_BY_VALUES = "UPDATE_BY_VALUES";

	private OverwritePolicy() {
		// hide default constructor to avoid outside subclassing
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes all data in the data set
	 * before importing. Only the newly imported records will be present in the
	 * data set.
	 * 
	 * @return a policy deleting all data
	 */
	public static OverwritePolicy deleteAll() {
		return AllOverwritePolicy.INSTANCE;
	}

	/**
	 * Returns an {@link OverwritePolicy} that makes no changes to the existing
	 * data. All previous records are kept and newly imported records appended
	 * to the data set.
	 * 
	 * @return a policy keeping all data
	 */
	public static OverwritePolicy deleteNone() {
		return NoOverwritePolicy.INSTANCE;
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records that are
	 * identical in all attributes with a record being imported. As a result, no
	 * two records can exist in the data set that have the entirely same
	 * attribute values.
	 * <p>
	 * Empty attribute values in existing data are treated as matching anything.
	 * Existing records that match an imported record on all non-empty
	 * attributes are deleted.
	 * 
	 * @return a policy deleting non-unique data by all attributes
	 */
	public static OverwritePolicy byAllAttributes() {
		return AllAttributeOverwritePolicy.INSTANCE;
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records that are
	 * identical in the specified attributes with a record being imported. As a
	 * result, no two records can exist in the data set that have the same
	 * values for all of those attributes at once. <br>
	 * At least one attribute needs to be specified.
	 * <p>
	 * Empty attribute values in existing data are treated as matching anything.
	 * Existing records that match an imported record on all non-empty
	 * attributes are deleted.
	 * 
	 * @param firstAttribute first attribute
	 * @param otherAttributes additional, optional attributes
	 * @return a policy deleting non-unique data by selected attributes
	 */
	public static OverwritePolicy byAttributes(String firstAttribute, String... otherAttributes) {
		List<String> attributes = new ArrayList<String>();
		attributes.add(firstAttribute);
		attributes.addAll(Arrays.asList(otherAttributes));
		return new AttributeOverwritePolicy(attributes);
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records that are
	 * identical in the specified attributes with a record being imported. As a
	 * result, no two records can exist in the data set that have the same
	 * values for all of those attributes at once. <br>
	 * At least one attribute needs to be specified.
	 * <p>
	 * Empty attribute values in existing data are treated as matching anything.
	 * Existing records that match an imported record on all non-empty
	 * attributes are deleted.
	 * 
	 * @param attributes attributes to match
	 * @return a policy deleting non-unique data by selected attributes
	 * @throws NoColumnsException if the list is empty
	 */
	public static OverwritePolicy byAttributes(List<String> attributes) throws NoColumnsException {
		return new AttributeOverwritePolicy(attributes);
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records with a date on or
	 * after the given date in the specified column.
	 * <p>
	 * Records with an empty date value in existing data are also deleted.
	 * 
	 * @param attribute date attribute to compare
	 * @param start first day (inclusive) from which to delete data
	 * @return {@link OverwritePolicy} object
	 */
	public static OverwritePolicy byDateFrom(String attribute, Calendar start) {
		return new DateRangeOverwritePolicy(attribute, start, null);
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records with a date
	 * before or on the given date in the specified column.
	 * <p>
	 * Records with an empty date value in existing data are also deleted.
	 * 
	 * @param attribute date attribute to compare
	 * @param end last day (inclusive) up to which to delete data
	 * @return {@link OverwritePolicy} object
	 */
	public static OverwritePolicy byDateTo(String attribute, Calendar end) {
		return new DateRangeOverwritePolicy(attribute, null, end);
	}

	/**
	 * Returns an {@link OverwritePolicy} that deletes records with a date
	 * between or on the given dates in the specified column.
	 * <p>
	 * Records with an empty date value in existing data are also deleted.
	 * 
	 * @param attribute date attribute to compare
	 * @param start first day (inclusive) from which to delete data
	 * @param end last day (inclusive) up to which to delete data
	 * @return {@link OverwritePolicy} object
	 */
	public static OverwritePolicy byDateFromTo(String attribute, Calendar start, Calendar end) {
		return new DateRangeOverwritePolicy(attribute, start, end);
	}

	/**
	 * Builds this policy's JSON representation.
	 * 
	 * @return this policy's JSON representation
	 */
	public abstract JsonNode toJson();

	/**
	 * Returns all attribute codes used in this policy. Can be used to check if
	 * a policy fits to a given data set.
	 * 
	 * @return all attributes used in this policy
	 */
	public List<String> getAttributeCodes() {
		return Collections.emptyList();
	}

	private static class NoOverwritePolicy extends OverwritePolicy {
		private static final NoOverwritePolicy INSTANCE = new NoOverwritePolicy();

		@Override
		public JsonNode toJson() {
			return new ObjectMapper().createObjectNode();
		}
	}

	private static class AllOverwritePolicy extends OverwritePolicy {
		private static final AllOverwritePolicy INSTANCE = new AllOverwritePolicy();

		@Override
		public JsonNode toJson() {
			return new ObjectMapper().createObjectNode().put("policy", DELETE_ALL);
		}
	}

	private static class DateRangeOverwritePolicy extends OverwritePolicy {

		private final String attribute;
		private final Long start;
		private final Long end;

		private DateRangeOverwritePolicy(String attribute, Calendar start, Calendar end) {
			if (start != null && end != null && start.after(end)) {
				throw new InvalidAbsoluteIntervalException(DateUnit.DAY, start, end);
			}
			this.attribute = attribute;
			this.start = start == null ? null : start.getTimeInMillis();
			this.end = end == null ? null : end.getTimeInMillis();
		}

		@Override
		public List<String> getAttributeCodes() {
			return Collections.singletonList(attribute);
		}

		@Override
		public JsonNode toJson() {
			ObjectNode json = new ObjectMapper().createObjectNode();
			json.put("policy", DELETE_ALL);
			json.put("dateAttribute", attribute);

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (start != null) {
				json.put("dateFrom", format.format(new Date(start)));
			}
			if (end != null) {
				json.put("dateTo", format.format(new Date(end)));
			}

			return json;
		}
	}

	private static class AttributeOverwritePolicy extends OverwritePolicy {

		private final List<String> attributes;

		private AttributeOverwritePolicy(List<String> attributes) {
			if (attributes.isEmpty()) {
				throw new NoColumnsException();
			}
			List<String> temp = new ArrayList<String>(attributes);
			this.attributes = Collections.unmodifiableList(temp);
		}

		@Override
		public List<String> getAttributeCodes() {
			return attributes;
		}

		@Override
		public JsonNode toJson() {
			ObjectNode json = new ObjectMapper().createObjectNode();
			json.put("policy", DELETE_BY_MEMBERS);
			ArrayNode attributes = new ObjectMapper().createArrayNode();
			for (String attribute : this.attributes) {
				attributes.add(attribute);
			}
			json.put("attributes", attributes);
			return json;
		}
	}

	private static class UpdateValuesPolicy extends OverwritePolicy {

		private final List<String> attributes;

		private UpdateValuesPolicy(List<String> attributes) {
			if (attributes.isEmpty()) {
				throw new NoColumnsException();
			}
			List<String> temp = new ArrayList<>(attributes);
			this.attributes = Collections.unmodifiableList(temp);
		}

		@Override
		public List<String> getAttributeCodes() {
			return attributes;
		}

		@Override
		public JsonNode toJson() {
			ObjectNode json = new ObjectMapper().createObjectNode();
			json.put("policy", UPDATE_BY_VALUES);
			ArrayNode attributes = new ObjectMapper().createArrayNode();
			for (String attribute : this.attributes) {
				attributes.add(attribute);
			}
			json.put("attributes", attributes);
			return json;
		}
	}

	private static class AllAttributeOverwritePolicy extends OverwritePolicy {
		private static final AllAttributeOverwritePolicy INSTANCE = new AllAttributeOverwritePolicy();

		@Override
		public JsonNode toJson() {
			return new ObjectMapper().createObjectNode().put("policy", DELETE_BY_MEMBERS).put("attributesAll", true);
		}
	}
}
