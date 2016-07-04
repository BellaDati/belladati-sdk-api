package com.belladati.sdk.view;

/**
 * Indicates the type of a view. Provides information of what content to expect
 * from the view.
 * 
 * @author Chris Hennigfeld
 */
public enum ViewType {
	/** A chart view. The view is an instance of {@link JsonView}. */
	CHART("chart"),
	/** A KPI view. The view is an instance of {@link JsonView}. */
	KPI("kpi"),
	/** A table view. The view is an instance of {@link TableView}. */
	TABLE("table/bounds"),
	/** A text view. The view is an instance of {@link JsonView}. */
	TEXT("text"),
	/** A map view. The view is an instance of {@link JsonView}. */
	MAP("map"),
	/** An image view. The view is an instance of {@link JsonView}. */
	IMAGE("image");

	private final String uri;

	private ViewType(String uri) {
		this.uri = uri;
	}

	/**
	 * Returns the URI to load this view's data, relative to the API URL. This
	 * method is used by the SDK internally.
	 * 
	 * @return the URI to load this view's data, relative to the API URL
	 */
	public String getUri() {
		return uri;
	}
}
