package com.belladati.sdk.view.export;

import java.io.InputStream;

/**
 * This class represents export of the view. There are several possibilities as
 * view can be exported as PDF, XLS, ... depending on the view type.
 * 
 * @author pavol.kovac
 */
public class ViewExport {

	private String viewId;
	private InputStream inputStream;
	private ViewExportType exportType;

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream export) {
		this.inputStream = export;
	}

	public ViewExportType getExportType() {
		return exportType;
	}

	public void setExportType(ViewExportType exportType) {
		this.exportType = exportType;
	}

	@Override
	public String toString() {
		return "ViewExport [viewId=" + viewId + ", inputStream=" + inputStream + ", exportType=" + exportType + "]";
	}

}
