package com.belladati.sdk;

import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belladati.sdk.dashboard.Dashboard;
import com.belladati.sdk.dashboard.DashboardInfo;
import com.belladati.sdk.dataset.AttributeValue;
import com.belladati.sdk.dataset.DataSet;
import com.belladati.sdk.dataset.DataSetInfo;
import com.belladati.sdk.dataset.data.DataRow;
import com.belladati.sdk.dataset.data.DataTable;
import com.belladati.sdk.dataset.source.DataSource;
import com.belladati.sdk.dataset.source.DataSourceImport;
import com.belladati.sdk.dataset.source.DataSourcePendingImport;
import com.belladati.sdk.domain.Domain;
import com.belladati.sdk.domain.DomainCreateBuilder;
import com.belladati.sdk.domain.DomainEditBuilder;
import com.belladati.sdk.domain.DomainInfo;
import com.belladati.sdk.exception.dataset.data.UnknownServerColumnException;
import com.belladati.sdk.exception.server.InvalidStreamException;
import com.belladati.sdk.exception.server.NotFoundException;
import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.form.Form;
import com.belladati.sdk.form.FormDataPostBuilder;
import com.belladati.sdk.report.Comment;
import com.belladati.sdk.report.Report;
import com.belladati.sdk.report.ReportInfo;
import com.belladati.sdk.user.User;
import com.belladati.sdk.user.UserCreateBuilder;
import com.belladati.sdk.user.UserEditBuilder;
import com.belladati.sdk.user.UserGroup;
import com.belladati.sdk.user.UserGroupCreateBuilder;
import com.belladati.sdk.user.UserRequestType;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.MultipartPiece;
import com.belladati.sdk.util.PostBuilder;
import com.belladati.sdk.util.PaginatedIdList;
import com.belladati.sdk.util.PaginatedList;
import com.belladati.sdk.view.ImageView;
import com.belladati.sdk.view.ViewLoader;
import com.belladati.sdk.view.ViewType;
import com.belladati.sdk.view.export.ViewExporter;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A service accessing one specific BellaDati server as an authenticated user.
 * <p>
 * This instance manages the user's access token obtained during authentication.
 * It uses the {@link BellaDatiConnection} used during authentication to access
 * the server.
 * <p>
 * Service instances may be serialized, saved, and restored for later use. This
 * will instantiate a new HTTP client, but reuse the existing authentication
 * credentials. Use this to save a user's session when closing your application
 * so they don't have to log in again on their next use.
 * 
 * @author Chris Hennigfeld
 */
public interface BellaDatiService extends Serializable {

	/**
	 * Returns a list of domains visible to the current user. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @return a list of all domains visible to the current user
	 * @see <a href="http://support.belladati.com/techdoc/GET+Domains">GET Domains</a>
	 */
	CachedList<DomainInfo> getDomainInfo();

	/**
	 * Loads the domain with the specified ID.
	 * <p>
	 * 
	 * @param id ID of the domain to load
	 * @return the domain with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Domain+Detail">GET Domain Detail</a>
	 */
	Domain loadDomain(String id) throws NotFoundException;

	/**
	 * Returns a list of users for the given domain and user group. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param domainId ID of the domain
	 * @param userGroupId optional ID of the user group
	 * @return a list of all users for the given domain and user group
	 * @see <a href="http://support.belladati.com/techdoc/GET+Users">GET Users</a>
	 */
	CachedList<User> getDomainUsers(String domainId, String userGroupId);

	/**
	 * Returns a list of user groups for the given domain. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param domainId ID of the domain
	 * @return a list of all user groups for the given domain
	 * @see <a href="http://support.belladati.com/techdoc/GET+User+Groups">GET User Groups</a>
	 */
	CachedList<UserGroup> getDomainUserGroups(String domainId);

	/**
	 * Sets up a {@link DomainCreateBuilder} instance used to create a new domain.
	 * Call {@link PostBuilder#post()} to submit request to the server.
	 * 
	 * @return the builder
	 * @see <a href="http://support.belladati.com/techdoc/POST+Create+Domain">POST Create Domain</a>
	 */
	DomainCreateBuilder setupDomainCreateBuilder();

	/**
	 * Sets up a {@link DomainEditBuilder} instance used to edit an existing domain.
	 * Call {@link PostBuilder#post()} to submit request to the server.
	 * 
	 * @param domainId the target domain ID
	 * @return the builder
	 * @see <a href="http://support.belladati.com/techdoc/POST+Edit+Domain">POST Edit Domain</a>
	 */
	DomainEditBuilder setupDomainEditBuilder(String domainId);

	/**
	 * Sets up a {@link UserGroupCreateBuilder} instance used to create a new user group.
	 * Call {@link PostBuilder#post()} to submit request to the server.
	 * 
	 * @param domainId the target domain ID
	 * @return the builder
	 * @see <a href="http://support.belladati.com/techdoc/POST+Create+User+Group">POST Create User Group</a>
	 */
	UserGroupCreateBuilder setupUserGroupCreateBuilder(String domainId);

	/**
	 * Sets up a {@link UserGroupCreateBuilder} instance used to create a new user.
	 * Call {@link PostBuilder#post()} to submit request to the server.
	 * 
	 * @param domainId the target domain ID
	 * @return the builder
	 * @see <a href="http://support.belladati.com/techdoc/POST+Create+User">POST Create User</a>
	 */
	UserCreateBuilder setupUserCreateBuilder(String domainId);

	/**
	 * Sets up a {@link UserEditBuilder} instance used to edit an existing user.
	 * Call {@link PostBuilder#post()} to submit request to the server.
	 * 
	 * @param userId the target user ID
	 * @return the builder
	 * @see <a href="http://support.belladati.com/techdoc/POST+Edit+User">POST Edit User</a>
	 */
	UserEditBuilder setupUserEditBuilder(String userId);

	/**
	 * Returns a paginated list to access dashboards visible to the current
	 * user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data.
	 * 
	 * @return a paginated list to access dashboards visible to the current user
	 * @see <a href="http://support.belladati.com/techdoc/GET+Dashboards">GET Dashboards</a>
	 */
	PaginatedIdList<DashboardInfo> getDashboardInfo();

	/**
	 * Loads the dashboard with the specified ID.
	 * <p>
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the dashboard to load
	 * @return the dashboard with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Dashboard+Detail">GET Dashboard Detail</a>
	 */
	Dashboard loadDashboard(String id) throws NotFoundException;

	/**
	 * Loads a thumbnail image for the dashboard with the given ID. The Java
	 * type of the image being returned depends on the implementation.
	 * 
	 * @param id ID of the dashboard whose thumbnail to load
	 * @return a thumbnail image for the dashboard with the given ID
	 * @throws InvalidStreamException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Dashboard+Thumbnail">GET Dashboard Thumbnail</a>
	 */
	Object loadDashboardThumbnail(String id) throws InvalidStreamException, NotFoundException;

	/**
	 * Returns a paginated list to access reports visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data.
	 * 
	 * @return a paginated list to access reports visible to the current user
	 * @see <a href="http://support.belladati.com/techdoc/GET+Reports">GET Reports</a>
	 */
	PaginatedIdList<ReportInfo> getReportInfo();

	/**
	 * Loads the report with the specified ID.
	 * <p>
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the report to load
	 * @return the report with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Report+Detail">GET Report Detail</a>
	 */
	Report loadReport(String id) throws NotFoundException;

	/**
	 * Loads a thumbnail image for the report with the given ID. The Java type
	 * of the image being returned depends on the implementation.
	 * 
	 * @param id ID of the report whose thumbnail to load
	 * @return a thumbnail image for the report with the given ID
	 * @throws InvalidStreamException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Report+Thumbnail">GET Report Thumbnail</a>
	 */
	Object loadReportThumbnail(String id) throws InvalidStreamException, NotFoundException;

	/**
	 * Returns a paginated list to access the comments of the report with the
	 * given ID.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data. Subsequent calls to this method for the same report
	 * always return the same list.
	 * 
	 * @param reportId ID of the report whose comments to access
	 * @return a paginated list to access the comments of the report with the
	 *         given ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Report+Comments">GET Report Comments</a>
	 */
	PaginatedList<Comment> getReportComments(String reportId) throws NotFoundException;

	/**
	 * Posts a comment to the report with the given ID.
	 * 
	 * @param reportId ID of the report to which to post a comment
	 * @param text text of the comment to post
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/POST+Comments">POST Comments</a>
	 */
	void postComment(String reportId, String text) throws NotFoundException;

	/**
	 * Deletes a comment from the report with the given ID.
	 * 
	 * @param commentId ID of the comment to delete
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/DELETE+Comments">DELETE Comments</a>
	 */
	void deleteComment(String commentId) throws NotFoundException;

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
	 * Sets up a {@link ViewLoader} instance used to load a view.
	 * 
	 * @param viewId ID of the view to load
	 * @param viewType type of the view to load
	 * @return a loader to load the JSON representation of the view
	 */
	ViewLoader setupViewLoader(String viewId, ViewType viewType);

	/**
	 * Creates a new {@link ImageView} in report.
	 * 
	 * @param reportId ID of the target report
	 * @param viewName name of the new image view
	 * @param image image file
	 * @param width width (percentage value) of the view
	 * @param height height (absolute value in pixels) of the view
	 * @return ID of the newly created view
	 * @see <a href="http://support.belladati.com/techdoc/POST+Images">POST Images</a>
	 */
	String createImageView(String reportId, String viewName, File image, Integer width, Integer height);

	/**
	 * Updates image in an existing {@link ImageView}.
	 * 
	 * @param viewId ID of the view to edit
	 * @param image image file
	 * @see <a href="http://support.belladati.com/techdoc/POST+Edit+Image+View">POST Edit Image View</a>
	 */
	void editImageView(String viewId, File image);

	/**
	 * Returns the possible values for the attribute with the given code in the
	 * data set with the specified ID. This is a cached list, call
	 * {@link CachedCollection#loadFirstTime()} to populate it initially.
	 * 
	 * @param dataSetId ID of the data set in which the attribute is defined
	 * @param attributeCode code of the attribute whose values to load
	 * @return all possible values for the attribute
	 * @throws NotFoundException if the ID or code wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Attribute+Values">GET Attribute Values</a>
	 */
	CachedList<AttributeValue> getAttributeValues(String dataSetId, String attributeCode) throws NotFoundException;

	/**
	 * Edits image in appearance settings for for the attribute with the given code and value in the
	 * data set with the specified ID.
	 * 
	 * @param dataSetId ID of the data set in which the attribute is defined
	 * @param attributeCode code of the attribute
	 * @param attributeValue value of the attribute
	 * @param image image to post
	 * @throws URISyntaxException if the URI cannot be created from given parameters
	 * @see <a href="http://support.belladati.com/techdoc/POST+Attribute+Value+Image">POST Attribute Value Image</a>
	 */
	void postAttributeValueImage(String dataSetId, String attributeCode, String attributeValue, File image)
		throws URISyntaxException;

	/**
	 * Loads the user with the given ID.
	 * 
	 * @param userId ID of the user to load
	 * @return the user with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+User+Detail">GET User Detail</a>
	 */
	User loadUser(String userId) throws NotFoundException;

	/**
	 * Loads the user with the given username (login).
	 * 
	 * @param username login of the user to load
	 * @return the user with the specified username
	 * @throws NotFoundException if the username wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+User+Detail+by+Username">GET User Detail by Username</a>
	 */
	User loadUserByUsername(String username) throws NotFoundException;

	/**
	 * Loads the profile image of the user with the given ID. The Java type of
	 * the image being returned depends on the implementation.
	 * 
	 * @param userId ID of the user whose image to load
	 * @return the profile image of the user with the given ID
	 * @throws InvalidStreamException if no image exists or it cannot be loaded
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+User+Photo">GET User Photo</a>
	 */
	Object loadUserImage(String userId) throws InvalidStreamException, NotFoundException;

	/**
	 * Loads the active status of the user with the given ID.
	 * 
	 * @param userId ID of the user whose status to load
	 * @return the active status of the user with the given ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+User+Status">GET User Status</a>
	 */
	String loadUserStatus(String userId) throws NotFoundException;	

	/**
	 * Posts the active status to the user with the given ID.
	 * 
	 * @param userId ID of the user to update
	 * @param status new user status to post
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/POST+Set+User+Status">POST Set User Status</a>
	 */
	void postUserStatus(String userId, String status) throws NotFoundException;

	/**
	 * Returns a paginated list to access data sets visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 * 
	 * @return a paginated list to access data sets visible to the current user
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Sets">GET Data Sets</a>
	 */
	PaginatedIdList<DataSetInfo> getDataSetInfo();

	/**
	 * Loads the data set with the specified ID.
	 * <p>
	 * This method makes an API call to BellaDati and may take some time to
	 * complete.
	 * 
	 * @param id ID of the data set to load
	 * @return the data set with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Set+Detail">GET Data Set Detail</a>
	 */
	DataSet loadDataSet(String id) throws NotFoundException;

	/**
	 * Returns a paginated list to access data set data visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 * 
	 * @return a paginated list to access data set data visible to the current user
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Set+Data">GET Data Set Data</a>
	 */
	PaginatedIdList<DataRow> getDataSetData(String dataSetId) throws NotFoundException;

	/**
	 * Returns a filtered paginated list to access data set data visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 *
	 * @return a paginated list to access data set data visible to the current user
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Set+Data">GET Data Set Data</a>
	 */
	PaginatedIdList<DataRow> getDataSetDataFiltered(String dataSetId, Filter<?>... filters) throws NotFoundException;

	/**
	 * Posts a row to the data set with the given ID. If the row has "id" then existing row with this
	 * identification will be updated. Otherwise, new row will be added.
	 * 
	 * @param dataSetId ID of the data set to which to post a row
	 * @param row data row to post
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/POST+Data+Set+Data">POST Data+Set+Data</a>
	 */
	void postDataSetData(String dataSetId, DataRow row) throws NotFoundException;

	/**
	 * Posts rows to the data set with the given ID. If a row has "id" then existing row with this
	 * identification will be updated. Otherwise, new row will be added.
	 *
	 * @param dataSetId ID of the data set to which to post a row
	 * @param rows data row to post
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/POST+Data+Set+Data">POST Data+Set+Data</a>
	 */
	void postDataSetData(String dataSetId, Collection<DataRow> rows) throws NotFoundException;

	/**
	 * Updates {@code rows} in the data set {@code dataSetId} that match on attributes given in {@code matchAttributes}.
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/PATCH+Data+Set+Data">PATCH Data Set Data</a>
	 */
	void patchDataSetData(String dataSetId, Collection<DataRow> rows, Collection<String> matchAttributes) throws NotFoundException;

	/**
	 * Deletes data from a data set based on given filter and then inserts new rows.
	 * @param dataSetId
	 * @param rows
	 * @param filters
	 * @throws NotFoundException
	 */
	void replaceDataSetData(String dataSetId, Collection<DataRow> rows, Filter<?>... filters) throws NotFoundException;

	/**
	 * Deletes data from a data set based on given filter.
	 * @param dataSetId
	 * @param filters
	 * @throws NotFoundException
	 */
	void deleteDataSetData(String dataSetId, Filter<?>... filters) throws NotFoundException;

	/**
	 * Uploads the given data into this data set. This method doesn't perform
	 * any validation of the data; the caller should ensure it matches the data
	 * set's structure.
	 * 
	 * @param id ID of the data set to upload to
	 * @param data the data to upload
	 * @throws UnknownServerColumnException if the data table contains a column
	 *             that doesn't exist in the data set
	 * @see <a href="http://support.belladati.com/techdoc/POST+JSON+data">POST JSON data</a>
	 */
	void uploadData(String id, DataTable data) throws UnknownServerColumnException;

	/**
	 * Uploads the given image into BellaDati Media Gallery.
	 * 
	 * @param image image to upload to
	 * @param name optional name visible in the Media Gallery
	 * @see <a href="http://support.belladati.com/techdoc/POST+Image+to+Media+Gallery">POST Image to Media Gallery</a>
	 */
	void uploadImage(File image, String name);

	/**
	 * Returns the list of data sources for the data set with the given ID. This
	 * is a cached list, call {@link CachedCollection#loadFirstTime()} to
	 * populate it initially.
	 * 
	 * @param id ID of the data set whose sources to load
	 * @return the list of data sources for the data set with the given ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Sources">GET Data Sources</a>
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
	 * @see <a href="http://support.belladati.com/techdoc/GET+Data+Source+Executions">GET Data Source Executions</a>
	 */
	CachedList<DataSourceImport> getDataSourceImports(String id) throws NotFoundException;

	/**
	 * Sets up a {@link DataSourcePendingImport} instance for the data source
	 * with the given ID. Call {@link DataSourcePendingImport#post()} to submit
	 * it to the server.
	 * 
	 * @param id is identifier of data-source
	 * @param date the date/time on which the import takes place
	 * @return the import object for further configuration and submission
	 */
	DataSourcePendingImport setupDataSourceImport(String id, Date date);

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] post(String uri) throws URISyntaxException;

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @param uriParameters parameters to append to the URI
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] post(String uri, Map<String, String> uriParameters) throws URISyntaxException;

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @param content body of the request
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] post(String uri, byte[] content) throws URISyntaxException;

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @param uriParameters parameters to append to the URI
	 * @param content body of the request
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] post(String uri, Map<String, String> uriParameters, byte[] content) throws URISyntaxException;

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @param formParameters parameters to send as a form in the request body
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] postForm(String uri, Map<String, String> formParameters) throws URISyntaxException;

	/**
	 * Makes a post request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to post to
	 * @param uriParameters parameters to append to the URI
	 * @param formParameters parameters to send as a form in the request body
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] postForm(String uri, Map<String, String> uriParameters, Map<String, String> formParameters) throws URISyntaxException;

	/**
	 * Makes a multipart/form-data post request to the given URI, relative to the server's URL.
	 * 
	 * @param relativeUri URI to post to
	 * @param multipart parts to send as a multipart/form-data in the request body
	 * @return the server's response
	 */
	byte[] postMultipart(String relativeUri, List<? extends MultipartPiece<?>> multipart);

	/**
	 * Makes a get request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to get
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] get(String uri) throws URISyntaxException;

	/**
	 * Makes a get request to the given URI, relative to the server's URL.
	 * 
	 * @param uri URI to get
	 * @param uriParameters parameters to append to the URI
	 * @return the server's response
	 * @throws URISyntaxException if the URI is malformed
	 */
	byte[] get(String uri, Map<String, String> uriParameters) throws URISyntaxException;

	/**
	 * Sets up a {@link ViewExporter} instance used to export a view.
	 * 
	 * @param viewId ID of the view to export
	 * @return {@link ViewExporter}
	 */
	ViewExporter setupViewExporter(String viewId);

	/**
	 * Returns the list of import forms. This is a cached list, call {@link CachedCollection#loadFirstTime()} to
	 * populate it initially.
	 * 
	 * @return the list of all import forms
	 * @see <a href="http://support.belladati.com/techdoc/GET+Forms">GET Forms</a>
	 */
	CachedList<Form> getImportForms();

	/**
	 * Loads the import form with the specified ID.
	 * 
	 * @param id ID of the import form to load
	 * @return the import form with the specified ID
	 * @throws NotFoundException if the ID wasn't found
	 * @see <a href="http://support.belladati.com/techdoc/GET+Form">GET Form</a>
	 */
	Form loadImportForm(String id) throws NotFoundException;

	/**
	 * Sets up a {@link FormDataPostBuilder} instance used to submit data.
	 * 
	 * @param formId ID of the import form
	 * @return {@link FormDataPostBuilder}
	 * @see <a href="http://support.belladati.com/techdoc/POST+Form+Data">POST Form Data</a>
	 */
	FormDataPostBuilder setupFormDataPostBuilder(String formId);

	/**
	 * Loads the file with the given absolute path. File is returned as a stream but the exact type
	 * of the response depends on the implementation.
	 * 
	 * @param absolutePath absolute path of the file to load
	 * @return the file as a stream
	 * @throws URISyntaxException if the URI cannot be created from given parameters
	 * @see <a href="http://support.belladati.com/techdoc/GET+File">GET File</a>
	 */
	Object loadFile(String absolutePath) throws URISyntaxException;

	/**
	 * Merges multiple PDF files (located at the given absolute paths) into one PDF file. Constructed
	 * PDF File is returned as a stream but the exact type of the response depends on the implementation.
	 * 
	 * @param paths list of absolute paths where source PDF files are located
	 * @return the PDF file as a stream
	 * @throws URISyntaxException if the URI cannot be created from given parameters
	 * @see <a href="http://support.belladati.com/techdoc/GET+Merge+PDF+files">GET Merge PDF files</a>
	 */
	Object mergePdfFiles(List<String> paths) throws URISyntaxException;

	/**
	 * Creates an user request of desired type for user specified by username.
	 * 
	 * @param username username of the user the request is created for
	 * @param requestType specifies the type of the request
	 * @return Request ID and request code of the created request separated by ";".
	 * @see <a href="http://support.belladati.com/techdoc/POST+Create+User+Request">POST Create User Request</a>
	 */
	String createUserRequest(String username, UserRequestType requestType);

	/**
	 * Creates an access token for user specified by username.
	 * 
	 * @param username username of the user the access token is created for
	 * @param validity if set, specifies the validity (in seconds) of issued token
	 * @param domainId specifies the domain (in multi-domain deployments only) for which the access should be granted
	 * @return OAuth token and OAuth token secret separated by ";".
	 * @see <a href="http://support.belladati.com/techdoc/POST+Create+Access+Token">POST Create Access Token</a>
	 */
	String createAccessToken(String username, Integer validity, String domainId);
	
	/**
	 * Generic method to load JSON type response
	 * 
	 * @param uri Endpoint URI
	 */
	JsonNode loadJson(String uri);

}
