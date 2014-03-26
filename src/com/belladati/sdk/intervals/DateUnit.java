package com.belladati.sdk.intervals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Units that can be used in BellaDati date intervals.
 * 
 * @author Chris Hennigfeld
 */
public enum DateUnit implements IntervalUnit {
	DAY {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("year", start.get(Calendar.YEAR));
			node.put("month", start.get(Calendar.MONTH) + 1);
			node.put("day", start.get(Calendar.DAY_OF_MONTH));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(Interval.readInt(node, "year"), Interval.readInt(node, "month") - 1, Interval.readInt(
				node, "day"));
		}
	},
	WEEK {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("year", start.get(Calendar.YEAR));
			node.put("week", start.get(Calendar.WEEK_OF_YEAR));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			Calendar cal = new GregorianCalendar(Interval.readInt(node, "year"), 0, 0);
			cal.set(Calendar.WEEK_OF_YEAR, Interval.readInt(node, "week"));
			return cal;
		}
	},
	MONTH {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("year", start.get(Calendar.YEAR));
			node.put("month", start.get(Calendar.MONTH) + 1);
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(Interval.readInt(node, "year"), Interval.readInt(node, "month") - 1, 0);
		}
	},
	QUARTER {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("year", start.get(Calendar.YEAR));
			node.put("quarter", start.get(Calendar.MONTH) / 3);
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(Interval.readInt(node, "year"), Interval.readInt(node, "quarter") * 3, 0);
		}
	},
	YEAR {
		@Override
		public JsonNode buildAbsoluteNode(long timestamp) {
			ObjectNode node = new ObjectMapper().createObjectNode();

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timestamp);
			node.put("year", start.get(Calendar.YEAR));
			return node;
		}

		@Override
		public Calendar parseAbsolute(JsonNode node) {
			return new GregorianCalendar(Interval.readInt(node, "year"), 0, 0);
		}
	};

	@Override
	public String getUnitNodeName() {
		return "dateInterval";
	}
}
