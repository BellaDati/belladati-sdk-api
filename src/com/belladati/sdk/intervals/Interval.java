package com.belladati.sdk.intervals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Interval<U extends IntervalUnit> {

	protected final U intervalUnit;

	Interval(U intervalUnit) {
		this.intervalUnit = intervalUnit;
	}

	public U getIntervalUnit() {
		return intervalUnit;
	}

	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ObjectNode unitIntervalNode = mapper.createObjectNode();

		node.put("timeInterval", unitIntervalNode);
		unitIntervalNode.put("interval", buildIntervalNode());
		unitIntervalNode.put("aggregationType", intervalUnit.name());

		return node;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	abstract ObjectNode buildIntervalNode();
}