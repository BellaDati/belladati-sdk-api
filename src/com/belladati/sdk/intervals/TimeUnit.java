package com.belladati.sdk.intervals;

import java.util.Calendar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Units that can be used in BellaDati time intervals.
 * 
 * @author Chris Hennigfeld
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
	};

	@Override
	public String getUnitNodeName() {
		return "timeInterval";
	}
}
