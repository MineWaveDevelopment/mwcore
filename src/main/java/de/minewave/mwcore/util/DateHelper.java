package de.minewave.mwcore.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class DateHelper {

	public static String getFormattedLocalDateTime(long millis) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(getLocalDateTime(millis));
	}
	
	public static LocalDateTime getLocalDateTime(long millis) {
		return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
}
