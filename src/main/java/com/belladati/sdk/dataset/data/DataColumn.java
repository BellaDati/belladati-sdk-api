package com.belladati.sdk.dataset.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A single row in a data table.
 * 
 * 
 */
public final class DataColumn {
	private final String code;
	private String format;

	public DataColumn(String code) {
		this(code, null);
	}

	public DataColumn(String code, String format) {
		this.code = code;
		this.format = format;
	}

	/**
	 * Returns this column's code.
	 * 
	 * @return this column's code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns this column's date or time format, if any.
	 * 
	 * @return this column's date or time format, or null
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets this column's date or time format.
	 * 
	 * @param format the date or time format for this column, or null
	 *            to not use any
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Returns this column in JSON representation.
	 * 
	 * @return this column in JSON representation
	 */
	public JsonNode toJson() {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("code", code);
		if (format != null) {
			node.put("format", format);
		}
		return node;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DataColumn) {
			return code.equals(((DataColumn) obj).code);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}
}
