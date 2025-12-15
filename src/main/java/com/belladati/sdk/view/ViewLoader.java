package com.belladati.sdk.view;

import java.util.Collection;
import java.util.Locale;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.intervals.DateUnit;
import com.belladati.sdk.intervals.Interval;
import com.belladati.sdk.intervals.TimeUnit;
import com.belladati.sdk.util.IdElement;

/**
 * A loader object that can hold various parameters to load a view. Use a loader
 * to set multiple parameters while loading views, such as date or time
 * intervals or filters.
 * 
 * 
 */
public interface ViewLoader extends IdElement {

	/**
	 * Returns the ID of the view that will be loaded.
	 * 
	 * @return the ID of the view that will be loaded
	 */
	String getId();

	/**
	 * Returns the type of the view that will be loaded.
	 * 
	 * @return the type of the view that will be loaded
	 */
	ViewType getType();

	/**
	 * Loads the view's content using the loader's current settings. The type of
	 * this view determines what content to expect.
	 * 
	 * @return the view's content
	 */
	Object loadContent();

	/**
	 * Sets a date interval to use with this loader. Replaces any date intervals
	 * previously set on this loader.
	 * 
	 * @param dateInterval the interval to set
	 * @return this loader
	 */
	ViewLoader setDateInterval(Interval<DateUnit> dateInterval);

	/**
	 * Sets a time interval to use with this loader. Replaces any time intervals
	 * previously set on this loader.
	 * 
	 * @param timeInterval the interval to set
	 * @return this loader
	 */
	ViewLoader setTimeInterval(Interval<TimeUnit> timeInterval);

	/**
	 * Adds filters to this loader. The filters are added to any other filters
	 * already defined on this loader.
	 * 
	 * @param filters the filters to add
	 * @return this loader
	 */
	ViewLoader addFilters(Filter<?>... filters);

	/**
	 * Adds filters to this loader. The filters are added to any other filters
	 * already defined on this loader.
	 * 
	 * @param filters the filters to add
	 * @return this loader
	 */
	ViewLoader addFilters(Collection<Filter<?>> filters);

	/**
	 * Sets the language in which to load the view. Elements that don't have a
	 * translation in this language are sent in their default language.
	 * 
	 * @param locale locale for which to return the view
	 * @return this loader
	 */
	ViewLoader setLocale(Locale locale);
}
