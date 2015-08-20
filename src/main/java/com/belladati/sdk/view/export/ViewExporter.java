package com.belladati.sdk.view.export;

/**
 * ViewExporter is an auxiliary class for view exporting. It has just single
 * responsibility
 * 
 * @author pavol.kovac
 */
public interface ViewExporter {

	/**
	 * Based on the viewId it exports to PDF
	 * 
	 * @see ViewExport
	 * @param viewId
	 * @return ViewExport which desired export.
	 */
	ViewExport exportToPdf(String viewId);

}