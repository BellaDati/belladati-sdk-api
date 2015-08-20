package com.belladati.sdk.view.export;

/**
 * This enum determines view export types. Not every view supports all types of
 * exports.
 * 
 * @author pavol.kovac
 */
public enum ViewExportType {
	PDF("application/pdf");

	private String mediaType;

	private ViewExportType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaType() {
		return mediaType;
	}
}
