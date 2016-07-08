package com.belladati.sdk.form;

import java.util.List;

import com.belladati.sdk.util.Resource;

/**
 * Element of import form. It represents one form field that can be filled in.
 * 
 * @author Lubomir Elko
 */
public interface FormElement extends Resource {

	/**
	 * Returns the type of an import form element.
	 * 
	 * @return the type of an import form element
	 */
	FormElementType getType();

	/**
	 * Returns the flag indicating if value should be mapped to date column.
	 * It is available only if this element has type {@link FormElementType#DATEFIELD DATEFIELD}.
	 * 
	 * @return the flag indicating if value should be mapped to date column
	 */
	Boolean getMapToDateColumn();

	/**
	 * Returns the list of options in select box field.
	 * It is available only if this element has type {@link FormElementType#SELECT SELECT}.
	 * 
	 * @return the list of options in select box field
	 */
	List<String> getItems();

}
