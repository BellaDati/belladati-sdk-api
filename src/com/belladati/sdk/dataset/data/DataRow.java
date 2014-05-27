package com.belladati.sdk.dataset.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belladati.sdk.exception.dataset.data.TooManyColumnsException;
import com.belladati.sdk.exception.dataset.data.UnknownColumnException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * A single row in a data table. Enforces adherence to the parent table's data
 * structure.
 * 
 * @author Chris Hennigfeld
 */
public class DataRow {

	private final List<String> columnCodes;
	private final Map<String, String> content = new HashMap<String, String>();

	DataRow(List<String> columnCodes) {
		this.columnCodes = columnCodes;
	}

	/**
	 * Returns this row's content for the given column.
	 * 
	 * @param columnCode the column to read
	 * @return this row's content for the given column
	 * @throws UnknownColumnException if the column doesn't exist
	 */
	public String get(String columnCode) throws UnknownColumnException {
		if (!columnCodes.contains(columnCode)) {
			throw new UnknownColumnException(columnCode);
		}
		return content.get(columnCode);
	}

	/**
	 * Returns this row's entire content. Columns that are not set will be
	 * returned as <tt>null</tt>.
	 * 
	 * @return this row's entire content
	 */
	public List<String> getAll() {
		List<String> values = new ArrayList<String>();
		for (String column : columnCodes) {
			values.add(content.get(column));
		}
		return values;
	}

	/**
	 * Sets this row's content for the given column.
	 * 
	 * @param columnCode the column to set
	 * @param value the value to enter
	 * @return this row, to allow chaining
	 * @throws UnknownColumnException if the column doesn't exist
	 */
	public DataRow set(String columnCode, String value) throws UnknownColumnException {
		if (!columnCodes.contains(columnCode)) {
			throw new UnknownColumnException(columnCode);
		}
		content.put(columnCode, value);
		return this;
	}

	/**
	 * Sets multiple columns of this row, starting from the first column.
	 * 
	 * @param values the column values to set
	 * @return this row, to allow chaining
	 * @throws TooManyColumnsException if more values are given than columns are
	 *             available
	 */
	public DataRow setAll(String... values) throws TooManyColumnsException {
		return setAll(0, values);
	}

	/**
	 * Sets multiple columns of this row, starting from the given offset.
	 * 
	 * @param values the column values to set
	 * @return this row, to allow chaining
	 * @throws TooManyColumnsException if more values are given than columns are
	 *             available after the offset
	 */
	public DataRow setAll(int offset, String... values) throws TooManyColumnsException {
		if (offset + values.length > columnCodes.size()) {
			throw new TooManyColumnsException(columnCodes.size(), offset + values.length);
		}
		for (int i = 0; i + offset < columnCodes.size() && i < values.length; i++) {
			content.put(columnCodes.get(i), values[i - offset]);
		}
		return this;
	}

	/**
	 * Returns all columns in this table.
	 * 
	 * @return all columns in this table
	 */
	public List<String> getColumns() {
		return columnCodes;
	}

	/**
	 * Returns this row in JSON representation.
	 * 
	 * @return this row in JSON representation
	 */
	public JsonNode toJson() {
		ArrayNode node = new ObjectMapper().createArrayNode();
		for (String value : getAll()) {
			node.add(value);
		}
		return node;
	}
}
