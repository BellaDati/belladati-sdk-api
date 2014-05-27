package com.belladati.sdk.dataset.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.belladati.sdk.exception.dataset.data.NoColumnsException;
import com.belladati.sdk.exception.dataset.data.TooManyColumnsException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A table holding data ready for import. Ensures that each row sticks to the
 * column structure defined in the constructor.
 * 
 * @author Chris Hennigfeld
 */
public class DataTable {

	private final List<String> columnCodes;
	private final List<DataRow> rows = new ArrayList<DataRow>();

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p />
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param firstColumn first column
	 * @param otherColumns additional, optional columns
	 */
	public DataTable(String firstColumn, String... otherColumns) {
		List<String> list = new ArrayList<String>();
		list.add(firstColumn);
		list.addAll(Arrays.asList(otherColumns));
		columnCodes = Collections.unmodifiableList(list);
	}

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p />
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param columns columns for the table
	 * @throws NoColumnsException if the list is empty
	 */
	public DataTable(List<String> columns) throws NoColumnsException {
		if (columns.isEmpty()) {
			throw new NoColumnsException();
		}
		columnCodes = Collections.unmodifiableList(columns);
	}

	/**
	 * Creates a new row in the table containing the given values.
	 * 
	 * @param values the column values to set
	 * @return this table, to allow chaining
	 * @throws TooManyColumnsException if more values are given than columns are
	 *             available
	 */
	public DataTable createRow(String... values) throws TooManyColumnsException {
		rows.add(new DataRow(columnCodes).setAll(values));
		return this;
	}

	/**
	 * Creates a new, empty row in the table.
	 * 
	 * @return the newly created row
	 */
	public DataRow createRow() {
		DataRow row = new DataRow(columnCodes);
		rows.add(row);
		return row;
	}

	/**
	 * Returns all rows currently in this table.
	 * 
	 * @return all rows currently in this table
	 */
	public List<DataRow> getRows() {
		return Collections.unmodifiableList(new ArrayList<DataRow>(rows));
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
	 * Returns this table in JSON representation.
	 * 
	 * @return this table in JSON representation
	 */
	public JsonNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ArrayNode columns = mapper.createArrayNode();
		for (String columnCode : columnCodes) {
			columns.add(mapper.createObjectNode().put("code", columnCode));
		}
		ArrayNode data = mapper.createArrayNode();
		for (DataRow row : rows) {
			data.add(row.toJson());
		}

		node.put("columns", columns);
		node.put("data", data);
		return node;
	}
}
