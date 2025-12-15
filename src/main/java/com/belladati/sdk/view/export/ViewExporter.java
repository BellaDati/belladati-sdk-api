package com.belladati.sdk.view.export;

import com.belladati.sdk.util.IdElement;
import com.belladati.sdk.util.PageOrientation;
import com.belladati.sdk.util.PageSize;

/**
 * ViewExporter is an auxiliary class for view exporting. It has just single responsibility.
 * 
 * 
 */
public interface ViewExporter extends IdElement {

	/**
	 * Returns the ID of the view that will be exported.
	 * 
	 * @return the ID of the view that will be exported
	 */
	String getId();

	/**
	 * Exports view as PDF.
	 * 
	 * @param pageSize optional size of an exported page
	 * @param pageOrientation optional orientation of an exported page
	 * @return ViewExport which desired export.
	 */
	ViewExport exportPdf(PageSize pageSize, PageOrientation pageOrientation);

	/**
	 * Exports view as PNG image.
	 * 
	 * @param width optional width of an exported image
	 * @param height optional height of an exported image
	 * @return ViewExport which desired export.
	 */
	ViewExport exportPng(Integer width, Integer height);

}