package com.belladati.sdk.intervals;

import java.util.Calendar;

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
	};

	@Override
	public String getUnitNodeName() {
		return "dateInterval";
	}
}
