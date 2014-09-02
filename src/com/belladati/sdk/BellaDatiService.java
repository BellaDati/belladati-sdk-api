package com.belladati.sdk;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.belladati.sdk.dashboard.Dashboard;
import com.belladati.sdk.dashboard.DashboardInfo;
import com.belladati.sdk.dataset.AttributeValue;
import com.belladati.sdk.dataset.DataSet;
import com.belladati.sdk.dataset.DataSetInfo;
import com.belladati.sdk.dataset.data.DataTable;
import com.belladati.sdk.dataset.source.DataSource;
import com.belladati.sdk.dataset.source.DataSourceImport;
import com.belladati.sdk.dataset.source.DataSourcePendingImport;
import com.belladati.sdk.exception.dataset.data.UnknownServerColumnException;
import com.belladati.sdk.exception.server.NotFoundException;
import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.report.Comment;
import com.belladati.sdk.report.Report;
import com.belladati.sdk.report.ReportInfo;
import com.belladati.sdk.user.User;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.PaginatedIdList;
import com.belladati.sdk.util.PaginatedList;
import com.belladati.sdk.view.ViewLoader;
import com.belladati.sdk.view.ViewType;

/**
 * A service accessing one specific BellaDati server as an authenticated user.
 * <p />
 * This instance manages the user's access token obtained during authentication.
 * It uses the {@link BellaDatiConnection} used during authentication to access
 * the server.
 * <p />
 * Service instances may be serialized, saved, and restored for later use. This
 * will instantiate a new HTTP client, but reuse the existing authentication
 * credentials. Use this to save a user's session when closing your application
 * so they don't have to log in again on their next use.
 * 
 * @author Chris Hennigfeld
 */
public interface BellaDatiService extends Serializable {

	/**
	 * Returns a paginated list to access dashboards visible to the current
	 * user.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data.
	 * 
	 * @return a paginated list to access dashboards visible to the current user
	 */
	PaginatedIdList<DashboardInfo> getDashboardInfo();

	/**
	 * Loads the dashboard with the specified ID.
	 * <p />
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the dashboard to load
	 * @return the dashboard with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	Dashboard loadDashboard(String id) throws NotFoundException;

	/**
	 * Loads a thumbnail image for the dashboard with the given ID. The Java
	 * type of the image being returned depends on the implementation.
	 * 
	 * @param id ID of the dashboard whose thumbnail to load
	 * @return a thumbnail image for the dashboard with the given ID
	 * @throws IOException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 */
	Object loadDashboardThumbnail(String id) throws IOException, NotFoundException;

	/**
	 * Returns a paginated list to access reports visible to the current user.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data.
	 * 
	 * @return a paginated list to access reports visible to the current user
	 */
	PaginatedIdList<ReportInfo> getReportInfo();

	/**
	 * Loads the report with the specified ID.
	 * <p />
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the report to load
	 * @return the report with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	Report loadReport(String id) throws NotFoundException;

	/**
	 * Loads a thumbnail image for the report with the given ID. The Java type
	 * of the image being returned depends on the implementation.
	 * 
	 * @param id ID of the report whose thumbnail to load
	 * @return a thumbnail image for the report with the given ID
	 * @throws IOException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 */
	Object loadReportThumbnail(String id) throws IOException, NotFoundException;

	/**
	 * Returns a paginated list to access the comments of the report with the
	 * given ID.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data. Subsequent calls to this method for the same report
	 * always return the same list.
	 * 
	 * @param reportId ID of the report whose comments to access
	 * @return a paginated list to access the comments of the report with the
	 *         given ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	PaginatedList<Comment> getReportComments(String reportId) throws NotFoundException;

	/**
	 * Posts a comment to the report with the given ID.
	 * 
	 * @param reportId ID of the report to which to post a comment
	 * @param text text of the comment to post
	 * @throws NotFoundException if the ID wasn't found
	 */
	void postComment(String reportId, String text) throws NotFoundException;

	/**
	 * Loads the content of the view with the given ID and type.
	 * 
	 * @param viewId ID of the view to load
	 * @param viewType type of the view to load
	 * @param filters optional filters to use when loading the view
	 * @return the content of the view
	 * @throws NotFoundException if the ID wasn't found
	 */
	Object loadViewContent(String viewId, ViewType viewType, Filter<?>... filters) throws NotFoundException;

	/**
	 * Loads the JSON representation of the view with the given ID and type.
	 * 
	 * @param viewId ID of the view to load
	 * @param viewType type of the view to load
	 * @param filters filters to use when loading the view
	 * @return the JSON representation of the view
	 * @throws NotFoundException if the ID wasn't found
	 */
	Object loadViewContent(String viewId, ViewType viewType, Collection<Filter<?>> filters) throws NotFoundException;

	/**
	 * Creates a loader to load the JSON representation of the view with the
	 * given ID and type.
	 * 
	 * @param viewId ID of the view to load
	 * @param viewType type of the view to load
	 * @return a loader to load the JSON representation of the view
	 */
	ViewLoader createViewLoader(String viewId, ViewType viewType);

	/**
	 * Returns the possible values for the attribute with the given code in the
	 * data set with the specified ID. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param dataSetId ID of the data set in which the attribute is defined
	 * @param attributeCode code of the attribute whose values to load
	 * @return all possible values for the attribute
	 * @throws NotFoundException if the ID or code wasn't found
	 */
	CachedList<AttributeValue> getAttributeValues(String dataSetId, String attributeCode) throws NotFoundException;

	/**
	 * Loads the user with the given ID.
	 * 
	 * @param userId ID of the user to load
	 * @return the user with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	User loadUser(String userId) throws NotFoundException;

	/**
	 * Loads the profile image of the user with the given ID. The Java type of
	 * the image being returned depends on the implementation.
	 * 
	 * @param userId ID of the user whose image to load
	 * @return the profile image of the user with the given ID
	 * @throws IOException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 */
	Object loadUserImage(String userId) throws IOException, NotFoundException;

	/**
	 * Returns a paginated list to access data sets visible to the current user.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 * 
	 * @return a paginated list to access data sets visible to the current user
	 */
	PaginatedIdList<DataSetInfo> getDataSetInfo();

	/**
	 * Loads the data set with the specified ID.
	 * <p />
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the data set to load
	 * @return the data set with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	DataSet loadDataSet(String id) throws NotFoundException;

	/**
	 * Uploads the given data into this data set. This method doesn't perform
	 * any validation of the data; the caller should ensure it matches the data
	 * set's structure.
	 * 
	 * @param id ID of the data set to upload to
	 * @param data the data to upload
	 * @throws UnknownServerColumnException if the data table contains a column
	 *             that doesn't exist in the data set
	 */
	void uploadData(String id, DataTable data) throws UnknownServerColumnException;

	/**
	 * Returns the list of data sources for the data set with the given ID. This
	 * is a cached list, call {@link CachedCollection#loadFirstTime()} to
	 * populate it initially.
	 * 
	 * @param id ID of the data set whose sources to load
	 * @return the list of data sources for the data set with the given ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	CachedList<DataSource> getDataSources(String id) throws NotFoundException;

	/**
	 * Returns the list of import configurations for the data source with the
	 * given ID. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param id ID of the data source whose import configurations to load
	 * @return the list of import configurations for the data source with the
	 *         given ID
	 * @throws NotFoundException if the ID wasn't found
	 */
	CachedList<DataSourceImport> getDataSourceImports(String id) throws NotFoundException;

	/**
	 * Sets up a {@link DataSourcePendingImport} instance for the data source
	 * with the given ID. Call {@link DataSourcePendingImport#post()} to submit
	 * it to the server.
	 * 
	 * @param date the date/time on which the import takes place
	 * @return the import object for further configuration and submission
	 */
	DataSourcePendingImport setupDataSourceImport(String id, Date date);
}
