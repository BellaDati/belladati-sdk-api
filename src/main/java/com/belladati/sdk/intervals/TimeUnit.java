package com.belladati.sdk.intervals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Units that can be used in BellaDati time intervals.
 * 
 * 
 */
public enum TimeUnit implements IntervalUnit {
	SECOND {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("hour", start.get(Calendar.HOUR));
			node.put("minute", start.get(Calendar.MINUTE));
			node.put("second", start.get(Calendar.SECOND));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(0, 0, 0, Interval.readInt(node, "hour"), Interval.readInt(node, "minute"),
				Interval.readInt(node, "second"));
		}
	},
	MINUTE {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("hour", start.get(Calendar.HOUR));
			node.put("minute", start.get(Calendar.MINUTE));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(0, 0, 0, Interval.readInt(node, "hour"), Interval.readInt(node, "minute"), 0);
		}
	},
	HOUR {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("hour", start.get(Calendar.HOUR));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(0, 0, 0, Interval.readInt(node, "hour"), 0, 0);
		}
	};

	@Override
	public String getUnitNodeName() {
		return "timeInterval";
	}
}
