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

	private final List<DataColumn> columns;
	private final Map<DataColumn, String> content = new HashMap<DataColumn, String>();

	DataRow(List<DataColumn> columns) {
		this.columns = columns;
	}

	/**
	 * Returns this row's content for the given column.
	 * 
	 * @param columnCode the column to read
	 * @return this row's content for the given column
	 * @throws UnknownColumnException if the column doesn't exist
	 */
	public String get(String columnCode) throws UnknownColumnException {
		for (DataColumn column : columns) {
			if (columnCode.equals(column.getCode())) {
				return content.get(column);
			}
		}
		throw new UnknownColumnException(columnCode);
	}

	/**
	 * Returns this row's entire content. Columns that are not set will be
	 * returned as <tt>null</tt>.
	 * 
	 * @return this row's entire content
	 */
	public List<String> getAll() {
		List<String> values = new ArrayList<String>();
		for (DataColumn column : columns) {
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
		for (DataColumn column : columns) {
			if (columnCode.equals(column.getCode())) {
				content.put(column, value);
				return this;
			}
		}
		throw new UnknownColumnException(columnCode);
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
		if (offset + values.length > columns.size()) {
			throw new TooManyColumnsException(columns.size(), offset + values.length);
		}
		for (int i = 0; i + offset < columns.size() && i < values.length; i++) {
			content.put(columns.get(i), values[i - offset]);
		}
		return this;
	}

	/**
	 * Returns all columns in this table.
	 * 
	 * @return all columns in this table
	 */
	public List<DataColumn> getColumns() {
		return columns;
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
