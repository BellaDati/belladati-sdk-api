package com.belladati.sdk.dataset.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.belladati.sdk.exception.dataset.data.NoColumnsException;
import com.belladati.sdk.exception.dataset.data.TooManyColumnsException;
import com.belladati.sdk.exception.dataset.data.UnknownColumnException;
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

	private final List<DataColumn> columns;
	private final List<DataRow> rows = new ArrayList<DataRow>();
	private OverwritePolicy overwritePolicy = OverwritePolicy.deleteNone();

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p>
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param firstColumn first column
	 * @param otherColumns additional, optional columns
	 * @return basic instance
	 */
	public static DataTable createBasicInstance(String firstColumn, String... otherColumns) {
		List<String> list = new ArrayList<String>();
		list.add(firstColumn);
		list.addAll(Arrays.asList(otherColumns));
		return createBasicInstance(list);
	}

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p>
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param columns columns for the table
	 * @return new DataTable
	 * @throws NoColumnsException if the list is empty
	 */
	public static DataTable createBasicInstance(List<String> columns) throws NoColumnsException {
		List<DataColumn> list = new ArrayList<DataColumn>();
		for (String column : columns) {
			list.add(new DataColumn(column));
		}
		return new DataTable(list);
	}

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p>
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param firstColumn first column
	 * @param otherColumns additional, optional columns
	 * @return new detailed instance
	 */
	public static DataTable createDetailedInstance(DataColumn firstColumn, DataColumn... otherColumns) {
		List<DataColumn> list = new ArrayList<DataColumn>();
		list.add(firstColumn);
		for (DataColumn column : otherColumns) {
			list.add(column);
		}
		return createDetailedInstance(list);
	}

	/**
	 * Creates a new instance with the given column setup. At least one column
	 * must be specified in the table. Rows are allowed to be empty.
	 * <p>
	 * Columns should be unique, but the table doesn't enforce this.
	 * 
	 * @param columns columns for the table
	 * @return mew data table
	 * @throws NoColumnsException if the list is empty
	 */
	public static DataTable createDetailedInstance(List<DataColumn> columns) throws NoColumnsException {
		return new DataTable(columns);
	}

	private DataTable(List<DataColumn> columns) throws NoColumnsException {
		if (columns.isEmpty()) {
			throw new NoColumnsException();
		}
		this.columns = Collections.unmodifiableList(new ArrayList<DataColumn>(columns));
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
		rows.add(new DataRow(columns).setAll(values));
		return this;
	}

	/**
	 * Creates a new, empty row in the table.
	 * 
	 * @return the newly created row
	 */
	public DataRow createRow() {
		DataRow row = new DataRow(columns);
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
	public List<DataColumn> getColumns() {
		return columns;
	}

	/**
	 * Sets the overwrite policy to use with this table. If none is set,
	 * {@link OverwritePolicy#deleteNone()} is used.
	 * 
	 * @param overwritePolicy the overwrite policy to use
	 * @return this table, to allow chaining
	 * @throws UnknownColumnException if any of the columns used in the policy
	 *             doesn't exist in the table
	 */
	public DataTable setOverwritePolicy(OverwritePolicy overwritePolicy) throws UnknownColumnException {
		for (String attribute : overwritePolicy.getAttributeCodes()) {
			assertAttributeExists(attribute);
		}
		this.overwritePolicy = overwritePolicy;
		return this;
	}

	/**
	 * Asserts that the given attribute exists as a column in this table.
	 * 
	 * @param attribute the attribute to find
	 * @throws UnknownColumnException if the attribute doesn't exist
	 */
	private void assertAttributeExists(String attribute) throws UnknownColumnException {
		for (DataColumn column : columns) {
			if (column.getCode().equals(attribute)) {
				return;
			}
		}
		throw new UnknownColumnException(attribute);
	}

	/**
	 * Returns the current overwrite policy of this table.
	 * 
	 * @return the current overwrite policy of this table
	 */
	public OverwritePolicy getOverwritePolicy() {
		return overwritePolicy;
	}

	/**
	 * Returns this table in JSON representation.
	 * 
	 * @return this table in JSON representation
	 */
	public JsonNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		ArrayNode columnsNode = mapper.createArrayNode();
		for (DataColumn column : columns) {
			columnsNode.add(column.toJson());
		}
		ArrayNode dataNode = mapper.createArrayNode();
		for (DataRow row : rows) {
			dataNode.add(row.toJson());
		}

		node.put("columns", columnsNode);
		node.put("data", dataNode);
		node.put("overwrite", overwritePolicy.toJson());
		return node;
	}
}
