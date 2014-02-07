package com.belladati.sdk.view;

import java.util.Collection;

import com.belladati.sdk.filter.Filter;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A view displaying a table. BellaDati tables consist of a left-sided header, a
 * top header, and a data content:
 * <table>
 * <tr>
 * <td style="text-align:center; padding: 0.5em;"></td>
 * <td style="text-align:center; padding: 0.5em;">Top Header</td>
 * </tr>
 * <tr>
 * <td style="text-align:center; padding: 0.5em;">Left Header</td>
 * <td style="text-align:center; padding: 0.5em;">Data</td>
 * </tr>
 * </table>
 * The first step to loading a table is calling {@link #loadContent(Filter...)},
 * resulting in a {@link Table} object. This object provides information about
 * the number of available rows and columns and provides methods to load the
 * actual table contents.
 * 
 * @author Chris Hennigfeld.
 */
public interface TableView extends View {

	/**
	 * Loads basic table information for this view. Use the resulting object to
	 * load table contents.
	 * 
	 * @param filters optional filters to use when loading the table
	 * @return basic table information for this view
	 */
	Table loadContent(Filter<?>... filters);

	/**
	 * Loads basic table information for this view. Use the resulting object to
	 * load table contents.
	 * 
	 * @param filters optional filters to use when loading the table
	 * @return basic table information for this view
	 */
	Table loadContent(Collection<Filter<?>> filters);

	/**
	 * Holds basic information about a table view. Use this object to load table
	 * headers and contents for display.
	 * <p />
	 * If the table is large, containing hundreds or thousands of rows or
	 * columns, it is recommended not to load the entire table at once. You can
	 * use the row and column parameters in the load methods to only load the
	 * part of the table currently accessed by the user, then dynamically load
	 * more content later on as needed.
	 * 
	 * @author Chris Hennigfeld
	 */
	interface Table {

		/**
		 * Returns the total number of rows in this table.
		 * 
		 * @return the total number of rows in this table
		 */
		int getRowCount();

		/**
		 * Returns the total number of columns in this table.
		 * 
		 * @return the total number of columns in this table
		 */
		int getColumnCount();

		/**
		 * Returns <tt>true</tt> if the left header has at least one column.
		 * 
		 * @return <tt>true</tt> if the left header has at least one column
		 */
		boolean hasLeftHeader();

		/**
		 * Returns <tt>true</tt> if the top header has at least one row.
		 * 
		 * @return <tt>true</tt> if the top header has at least one row
		 */
		boolean hasTopHeader();

		/**
		 * Loads the left header between the given row indexes, inclusively.
		 * 
		 * @param firstRow the first left header row to load
		 * @param lastRow the last left header row to load
		 * @return the left header between the given row indexes
		 * @throws IllegalArgumentException if the first or last row are out of
		 *             bounds, or firstRow is greater than lastRow
		 */
		JsonNode loadLeftHeader(int firstRow, int lastRow) throws IllegalArgumentException;

		/**
		 * Loads the top header between the given column indexes, inclusively.
		 * 
		 * @param firstColumn the first top header column to load
		 * @param lastColumn the last top header column to load
		 * @return the top header between the given column indexes
		 * @throws IllegalArgumentException if the first or last column are out
		 *             of bounds, or firstColumn is greater than lastColumn
		 */
		JsonNode loadTopHeader(int firstColumn, int lastColumn) throws IllegalArgumentException;

		/**
		 * Loads the table's data between the given row and column indexes,
		 * inclusively.
		 * 
		 * @param firstRow the first data row to load
		 * @param lastRow the last data row to load
		 * @param firstColumn the first data column to load
		 * @param lastColumn the last data column to load
		 * @return the table's data between the given row and column indexes
		 * @throws IllegalArgumentException if any of the indexes is out of
		 *             bounds, or a first index is greater than the
		 *             corresponding last index
		 */
		JsonNode loadData(int firstRow, int lastRow, int firstColumn, int lastColumn) throws IllegalArgumentException;
	}
}
