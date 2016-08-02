package com.belladati.sdk.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;

import com.belladati.sdk.filter.Filter;

/**
 * A view displaying Image content.
 * 
 * @author Pavol Kovac
 */
public interface ImageView extends View {

	/**
	 * Loads the Image representation of this view.
	 * 
	 * @param filters optional filters to use when loading the view
	 * @return the Image representation of this view
	 */
	Image loadContent(Filter<?>... filters);

	/**
	 * Loads the Image representation of this view.
	 * 
	 * @param filters filters to use when loading the view
	 * @return the Image representation of this view
	 */
	Image loadContent(Collection<Filter<?>> filters);

	/**
	 * Updates image in this view.
	 * 
	 * @param image image file
	 */
	void updateImage(File image);

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
