package com.belladati.sdk.view;

import java.awt.image.BufferedImage;
import java.util.Collection;

import com.belladati.sdk.filter.Filter;

/**
 * A view displaying Image content.
 * 
 * @author Pavol Kovac
 */
public interface ImageView extends View {

	/**
	 * Loads the JSON representation of this view.
	 * 
	 * @param filters optional filters to use when loading the view
	 * @return the JSON representation of this view
	 */
	BufferedImage loadContent(Filter<?>... filters);

	/**
	 * Loads the JSON representation of this view.
	 * 
	 * @param filters filters to use when loading the view
	 * @return the JSON representation of this view
	 */
	BufferedImage loadContent(Collection<Filter<?>> filters);

	/**
	 * This class is a transport crate for image
	 * 
	 * @author pavol.kovac
	 */
	interface Image {

		/**
		 * Returns ID of the image
		 * 
		 * @return id of the image
		 */
		public String getId();

		/**
		 * Returns the image itself
		 * 
		 * @return image itself
		 */
		public BufferedImage getImage();
	}

}
