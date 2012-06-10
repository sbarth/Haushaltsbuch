package de.sb.plugin.finance.filters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import de.sb.plugin.finance.util.Compare;
import de.sb.plugin.finance.util.R;

public class TestTableTransactionFilterByDate {
	@Test
	public void filterByCurrentDay() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTDAY);
		Calendar today = new GregorianCalendar();
		Assert.assertEquals(filter.getCalFrom(), today);
		Assert.assertEquals(filter.getCalTo(), today);
	}

	@Test
	public void filterByCurrentMonthDecember2010() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2010, 11, 31);
		Calendar today2 = getDate(2010, 11, 31);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2010, 11, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2010, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentMonthFebruary2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 1, 1);
		Calendar today2 = getDate(2011, 1, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 1, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 1, 28);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentMonthFebruary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 1, 27);
		Calendar today2 = getDate(2012, 1, 27);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 1, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 1, 29);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentMonthJanuary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 0, 15);
		Calendar today2 = getDate(2012, 0, 15);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 0, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterApril2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 3, 27);
		Calendar today2 = getDate(2011, 3, 27);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 3, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 5, 30);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterDecember2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 11, 31);
		Calendar today2 = getDate(2012, 11, 31);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterFebruary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 1, 1);
		Calendar today2 = getDate(2012, 1, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 2, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterJanuary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 0, 15);
		Calendar today2 = getDate(2012, 0, 15);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 2, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterMarch2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 2, 1);
		Calendar today2 = getDate(2011, 2, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 2, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterMay2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 4, 1);
		Calendar today2 = getDate(2011, 4, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 3, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 5, 30);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentQuarterOctober2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentYear2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 5, 24);
		Calendar today2 = getDate(2011, 5, 24);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByCurrentYear2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastMonthOfJanuary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 0, 15);
		Calendar today2 = getDate(2012, 0, 15);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 11, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastMonthOfMarch2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 2, 1);
		Calendar today2 = getDate(2011, 2, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 1, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 1, 28);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastMonthOfMarch2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 2, 1);
		Calendar today2 = getDate(2012, 2, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 1, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 1, 29);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastMonthOfOctober2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 8, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 8, 30);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterApril2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 3, 27);
		Calendar today2 = getDate(2011, 3, 27);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 2, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterDecember2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 11, 31);
		Calendar today2 = getDate(2012, 11, 31);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 6, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 8, 30);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterFebruary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 1, 1);
		Calendar today2 = getDate(2012, 1, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterJanuary2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 0, 15);
		Calendar today2 = getDate(2012, 0, 15);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterMarch2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 2, 1);
		Calendar today2 = getDate(2011, 2, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2010, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2010, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterMay2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 4, 1);
		Calendar today2 = getDate(2011, 4, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 2, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastQuarterOctober2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 6, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 8, 30);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastYear2011() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2011, 5, 13);
		Calendar today2 = getDate(2011, 5, 13);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2010, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2010, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterByLastYear2012() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2011, 0, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2011, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterBySelected1() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 9, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_SELECT);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 9, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 9, 1);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterBySelected2() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 5, 12);
		Calendar today2 = getDate(2012, 10, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_SELECT);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 5, 12);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 10, 1);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterBySelected3() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 10, 1);
		Calendar today2 = getDate(2012, 9, 1);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_SELECT);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 10, 1);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 9, 1);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	@Test
	public void filterBySelected4() {
		TableTransactionFilter filter = new TableTransactionFilter();

		Calendar today1 = getDate(2012, 2, 12);
		Calendar today2 = getDate(2012, 11, 31);

		filter.setDateFromTo(today1, today2);
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_SELECT);

		Calendar expectFrom = new GregorianCalendar();
		expectFrom.set(2012, 2, 12);

		Calendar expectTo = new GregorianCalendar();
		expectTo.set(2012, 11, 31);

		Assert.assertTrue(Compare.areOnSameDay(filter.getCalFrom(), expectFrom));
		Assert.assertTrue(Compare.areOnSameDay(filter.getCalTo(), expectTo));
	}

	private Calendar getDate(final int year, final int month, final int day) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);

		return cal;
	}
}
