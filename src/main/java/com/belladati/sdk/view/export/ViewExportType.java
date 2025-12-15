package com.belladati.sdk.view.export;

/**
 * This enumeration determines view export types. Not every view supports all types of
 * exports.
 * 
 * 
 */
public enum ViewExportType {

	PDF("application/pdf"),

	PNG("image/png");

	private String mediaType;

	private ViewExportType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaType() {
		return mediaType;
	}
}
