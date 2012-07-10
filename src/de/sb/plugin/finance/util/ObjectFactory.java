package de.sb.plugin.finance.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ObjectFactory {
	public static Calendar getCurrentDay(final int hourOfDay, final int min, final int sec, final int ms) {
		Calendar cal = new GregorianCalendar();

		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, ms);

		return cal;
	}
}
