package com.belladati.sdk.dataset.source;

import java.util.Date;

import com.belladati.sdk.dataset.DataSet;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.Resource;

/**
 * A data source used by a {@link DataSet}.
 * 
 * @author Chris Hennigfeld
 */
public interface DataSource extends Resource {

	/**
	 * Returns the textual type of the data source. This is the name of the Java
	 * class used on the server side and is generally human-readable.
	 * <p>
	 * Two data sources using the same data source connector generally have the
	 * same type.
	 * 
	 * @return the textual type of the data source
	 */
	String getType();

	/**
	 * Returns the list of imports for this data source. This is a cached list,
	 * call {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @return the list of imports for this data source
	 */
	CachedList<DataSourceImport> getImports();

	/**
	 * Sets up a {@link DataSourcePendingImport} instance for this data source.
	 * Call {@link DataSourcePendingImport#post()} to submit it to the server.
	 * 
	 * @param date the date/time on which the import takes place
	 * @return the import object for further configuration and submission
	 */
	DataSourcePendingImport setupImport(Date date);
}
