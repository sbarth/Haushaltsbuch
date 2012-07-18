package de.sb.plugin.finance.ui.views.statistics;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;

public class TimeSeriesChartOld {
	private final List<Transaction> transactions;
	private final BigDecimal startAmount;
	private final JFreeChart chart;

	public TimeSeriesChartOld(final List<Transaction> transactions, final BigDecimal startAmount) {
		this.startAmount = startAmount;
		this.transactions = transactions;

		final TimeSeriesCollection dataset1 = createDataset();
		chart = ChartFactory.createTimeSeriesChart("Multiple Dataset Demo 1", "Time", "Value", dataset1, true, true, false);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		// this.plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 4, 4, 4, 4));
		final ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);

		final NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
		rangeAxis2.setAutoRangeIncludesZero(false);

	}

	private double calcValue(double value, final Transaction t) {
		if (t.getType().equals(TransactionType.FIX_INCOME.getName()) || t.getType().equals(TransactionType.INCOME.getName())) {
			value += t.getAmount().doubleValue();
		} else if (t.getType().equals(TransactionType.FIX_OUTCOME.getName()) || t.getType().equals(TransactionType.OUTCOME.getName())) {
			value -= t.getAmount().doubleValue();
		}

		return value;
	}

	private TimeSeriesCollection createDataset() {
		final TimeSeries series = new TimeSeries("Verlauf");
		double value = startAmount.doubleValue();

		// transactions überprüfen das auch welche drin sind

		Calendar date = transactions.get(0).getDate();
		List<Transaction> list = new ArrayList<Transaction>();
		RegularTimePeriod rtp = new Day(new GregorianCalendar(transactions.get(0).getDate().get(Calendar.YEAR), 0, 1).getTime());
		for (int dayOfYear = 1; dayOfYear <= date.getMaximum(Calendar.DAY_OF_YEAR); dayOfYear++) {
			list.clear();

			for (int index = 0; index < transactions.size(); index++) {
				Transaction t = transactions.get(index);

				if (t.getDate().get(Calendar.DAY_OF_YEAR) == dayOfYear) {
					value = calcValue(value, t);
				}
			}

			series.add(rtp, value);
			rtp = rtp.next();
		}

		return new TimeSeriesCollection(series);
	}

	public JFreeChart getChart() {
		return chart;
	}
}
