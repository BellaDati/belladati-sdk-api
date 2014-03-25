package com.belladati.sdk.intervals;

import com.fasterxml.jackson.databind.JsonNode;

public interface IntervalUnit {
	String name();

	JsonNode buildAbsoluteNode(long timestamp);
}
