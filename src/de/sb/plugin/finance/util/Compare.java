package de.sb.plugin.finance.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Compare {
	public static boolean areInSameWeek(final Calendar cal1, final Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			return false;
		}

		if (cal1.get(Calendar.WEEK_OF_YEAR) != cal2.get(Calendar.WEEK_OF_YEAR)) {
			return false;
		}

		return true;
	}

	public static boolean areOnSameDay(final Calendar cal1, final Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			return false;
		}

		if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
			return false;
		}
		if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
			return false;
		}

		return true;
	}

	public static boolean areOnSameDay(final Date d1, final Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}

		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();

		c1.setTime(d1);
		c2.setTime(d2);

		// if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH)) {
		// return false;
		// }
		// if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) {
		// return false;
		// }
		// if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
		// return false;
		// }

		return areOnSameDay(c1, c2);
	}
}
