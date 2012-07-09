package de.sb.plugin.finance.ui.strategy;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;

public class DateToCalendarStrategy extends UpdateValueStrategy {

	public DateToCalendarStrategy() {
		setConverter(new Converter(Date.class, Calendar.class) {
			@Override
			public Object convert(Object fromObject) {
				Date date = (Date) fromObject;
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(date);
				return cal;
			}
		});

	}
}
