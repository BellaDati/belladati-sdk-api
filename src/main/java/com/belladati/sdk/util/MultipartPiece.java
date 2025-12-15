package com.belladati.sdk.util;

/**
 * Form part, it is one piece of request that could be sent as Multipart Form-Data (MIME type
 * {@code multipart/form-data}). This interface is used to unify heterogeneous values submitted
 * through a multipart form.
 * @see <a href="https://tools.ietf.org/html/rfc7578">RFC 7578</a>
 * 
 * 
 */
public interface MultipartPiece<T> {

	/**
	 * Returns the name of this form part.
	 * 
	 * @return the name of this form part
	 */
	String getName();

	/**
	 * Returns the value of this form part.
	 * 
	 * @return the value of this form part
	 */
	T getValue();

	/**
	 * Returns the content type of this form part.
	 * 
	 * @return the content type of this form part
	 */
	String getContentType();

}
