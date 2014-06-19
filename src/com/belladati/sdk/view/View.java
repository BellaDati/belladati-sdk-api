package com.belladati.sdk.view;

import java.util.Collection;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.intervals.DateUnit;
import com.belladati.sdk.intervals.Interval;
import com.belladati.sdk.intervals.TimeUnit;
import com.belladati.sdk.util.Localizable;
import com.belladati.sdk.util.Resource;

/**
 * A BellaDati view in a report or dashboard. Call
 * {@link #loadContent(Filter...)} to get the view's displayable content.
 * 
 * @author Chris Hennigfeld
 */
public interface View extends Resource, Localizable {

	/**
	 * Returns the type of this view.
	 * 
	 * @return the type of this view
	 */
	ViewType getType();

	/**
	 * Loads the content of this view. The type of this view determines what
	 * content to expect.
	 * 
	 * @param filters optional filters to use when loading the view
	 * @return the content of this view
	 */
	Object loadContent(Filter<?>... filters);

	/**
	 * Loads the content of this view. The type of this view determines what
	 * content to expect.
	 * 
	 * @param filters filters to use when loading the view
	 * @return the content of this view
	 */
	Object loadContent(Collection<Filter<?>> filters);

	/**
	 * Returns <tt>true</tt> if this view supports setting date intervals.
	 * 
	 * @return <tt>true</tt> if this view supports setting date intervals
	 */
	boolean isDateIntervalSupported();

	/**
	 * Returns <tt>true</tt> if this view supports setting time intervals.
	 * 
	 * @return <tt>true</tt> if this view supports setting time intervals
	 */
	boolean isTimeIntervalSupported();

	/**
	 * Returns <tt>true</tt> if this view has a date interval defined in its
	 * report.
	 * 
	 * @return <tt>true</tt> if this view has a date interval defined in its
	 *         report
	 */
	boolean hasPredefinedDateInterval();

	/**
	 * Returns <tt>true</tt> if this view has a time interval defined in its
	 * report.
	 * 
	 * @return <tt>true</tt> if this view has a time interval defined in its
	 *         report
	 */
	boolean hasPredefinedTimeInterval();

	/**
	 * Returns the view's date interval defined in its report, or <tt>null</tt>
	 * if there is no predefined interval.
	 * 
	 * @return the view's date interval defined in its report
	 */
	Interval<DateUnit> getPredefinedDateInterval();

	/**
	 * Returns the view's time interval defined in its report, or <tt>null</tt>
	 * if there is no predefined interval.
	 * 
	 * @return the view's time interval defined in its report
	 */
	Interval<TimeUnit> getPredefinedTimeInterval();

	/**
	 * Creates a loader instance used to load this view with parameters.
	 * 
	 * @return a loader instance used to load this view with parameters
	 */
	ViewLoader createLoader();
}
