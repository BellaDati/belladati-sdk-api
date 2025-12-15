package com.belladati.sdk.view;

import java.util.Collection;
import java.util.Locale;

import com.belladati.sdk.filter.Filter;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A view displaying a table. BellaDati tables consist of a left-sided header, a
 * top header, and a data content:
 * <table summary="header position description">
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
 * 
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
	 * <p>
	 * If the table is large, containing hundreds or thousands of rows or
	 * columns, it is recommended not to load the entire table at once. You can
	 * use the row and column parameters in the load methods to only load the
	 * part of the table currently accessed by the user, then dynamically load
	 * more content later on as needed.
	 * 
	 * 
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
		 * Returns true if the left header has at least one column.
		 * 
		 * @return true if the left header has at least one column
		 */
		boolean hasLeftHeader();

		/**
		 * Returns true if the top header has at least one row.
		 * 
		 * @return true if the top header has at least one row
		 */
		boolean hasTopHeader();

		/**
		 * Loads the left header between the given row indexes.
		 * 
		 * @param startRow the first left header row to load, starting at 0
		 * @param endRow the row until which to load, exclusive
		 * @return the left header between the given row indexes
		 * @throws IllegalArgumentException if the first or last row are out of
		 *             bounds, or firstRow is greater than lastRow
		 */
		JsonNode loadLeftHeader(int startRow, int endRow) throws IllegalArgumentException;

		/**
		 * Loads the top header between the given column indexes.
		 * 
		 * @param startColumn the first top header column to load, starting at 0
		 * @param endColumn the column until which to load, exclusive
		 * @return the top header between the given column indexes
		 * @throws IllegalArgumentException if the first or last column are out
		 *             of bounds, or firstColumn is greater than lastColumn
		 */
		JsonNode loadTopHeader(int startColumn, int endColumn) throws IllegalArgumentException;

		/**
		 * Loads the table's data between the given row and column indexes.
		 * 
		 * @param startRow the first data row to load, starting at 0
		 * @param endRow the row until which to load, exclusive
		 * @param startColumn the first data column to load, starting at 0
		 * @param endColumn the column until which to load, exclusive
		 * @return the table's data between the given row and column indexes
		 * @throws IllegalArgumentException if any of the indexes is out of
		 *             bounds, or a first index is greater than the
		 *             corresponding last index
		 */
		JsonNode loadData(int startRow, int endRow, int startColumn, int endColumn) throws IllegalArgumentException;

		/**
		 * Returns the locale in which to load table contents. Use
		 * {@link ViewLoader#setLocale(Locale)} before calling
		 * {@link ViewLoader#loadContent()} or {@link #setLocale(Locale)} on the
		 * table to set it.
		 * 
		 * @return the locale in which to load table contents, or null
		 *         if no specific locale was set
		 */
		Locale getLocale();

		/**
		 * Sets the locale in which to load table contents. Elements that don't
		 * have a translation in this language are sent in their default
		 * language. Set to null to return all elements in default
		 * language.
		 * 
		 * @param locale the locale in which to load table contents
		 * @return this table
		 */
		Table setLocale(Locale locale);
	}
}
