package com.belladati.sdk.view;

import java.util.Collection;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.intervals.DateUnit;
import com.belladati.sdk.intervals.Interval;
import com.belladati.sdk.intervals.TimeUnit;

public interface ViewLoader {

	Object loadContent();

	ViewLoader setDateInterval(Interval<DateUnit> interval);

	ViewLoader setTimeInterval(Interval<TimeUnit> interval);

	ViewLoader addFilter(Filter<?> filter);

	ViewLoader addFilters(Filter<?>... filters);

	ViewLoader addFilters(Collection<Filter<?>> filters);
}
