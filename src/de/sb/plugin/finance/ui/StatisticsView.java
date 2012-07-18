package de.sb.plugin.finance.ui;

import java.awt.Frame;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.io.DatabaseOperations;
import de.sb.plugin.finance.ui.views.statistics.AreaChart;
import de.sb.plugin.finance.ui.views.statistics.IncomeOutcomeBarChart;
import de.sb.plugin.finance.ui.views.statistics.TimeSeriesChartOld;
import de.sb.plugin.finance.util.R;

public class StatisticsView extends ViewPart {
	private final Map<Integer, BigDecimal> varIncome = new HashMap<Integer, BigDecimal>(); // Month 0 based, Amount
	private final Map<Integer, BigDecimal> varOutcome = new HashMap<Integer, BigDecimal>();
	private final Map<Integer, BigDecimal> fixIncome = new HashMap<Integer, BigDecimal>();
	private final Map<Integer, BigDecimal> fixOutcome = new HashMap<Integer, BigDecimal>();

	public StatisticsView() {

	}

	private void addAmountToMap(final Map<Integer, BigDecimal> map, final Transaction t) {
		int key = t.getDate().get(Calendar.MONTH);
		BigDecimal amount = map.get(key);
		amount = amount.add(t.getAmount());
		map.put(key, amount);
	}

	private void calcValues() {
		DatabaseOperations ops = DatabaseOperations.getInstance();
		List<Transaction> allTransactions = ops.getAllTransactions();

		fillStandardValuesIntoMap(fixIncome);
		fillStandardValuesIntoMap(fixOutcome);
		fillStandardValuesIntoMap(varIncome);
		fillStandardValuesIntoMap(varOutcome);

		for (Transaction t : allTransactions) {
			if (t.getType().equals(TransactionType.FIX_INCOME.getName())) {
				addAmountToMap(fixIncome, t);
			} else if (t.getType().equals(TransactionType.FIX_OUTCOME.getName())) {
				addAmountToMap(fixOutcome, t);
			} else if (t.getType().equals(TransactionType.INCOME.getName())) {
				addAmountToMap(varIncome, t);
			} else if (t.getType().equals(TransactionType.OUTCOME.getName())) {
				addAmountToMap(varOutcome, t);
			}
		}
	}

	private void createAreaChart(final Composite parent) {
		calcValues();

		double[][] data = new double[2][12];
		for (int index = 0; index < R.MONTH_NAMES_SHORT.length; index++) {
			double var = varIncome.get(index).doubleValue();
			double com = var + fixIncome.get(index).doubleValue();

			data[0][index] = var;
			data[1][index] = com;
		}

		Frame frame = SWT_AWT.new_Frame(parent);
		final CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Series ", "Type ", data);

		final ChartPanel chartPanel = new ChartPanel(new AreaChart(dataset).getChart());
		frame.add(chartPanel);
	}

	private void createBarGraph(final Composite composite) {
		calcValues();

		Frame frame = SWT_AWT.new_Frame(composite);

		DefaultCategoryDataset bars = new DefaultCategoryDataset();
		DefaultCategoryDataset lines = new DefaultCategoryDataset();
		for (int index = 0; index < R.MONTH_NAMES_SHORT.length; index++) {
			String monthName = R.MONTH_NAMES_SHORT[index];
			double income = fixIncome.get(index).add(varIncome.get(index)).doubleValue();
			double outcome = fixOutcome.get(index).add(varOutcome.get(index)).doubleValue();

			bars.addValue(income, "Einnahme", monthName);
			bars.addValue(outcome, "Ausgabe", monthName);

			lines.addValue(fixIncome.get(index).doubleValue(), "VarIncome", monthName);
			lines.addValue(fixOutcome.get(index).doubleValue(), "VarOutcome", monthName);
		}

		final ChartPanel chartPanel = new ChartPanel(new IncomeOutcomeBarChart(bars, lines, "Monat", "€").getChart());
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		frame.add(chartPanel);
	}

	@Override
	public void createPartControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		// createAreaChart(composite);
		// createBarGraph(composite);
		// createTimeSeriesChart(composite);

		// int result = new NewTimeSeriesChartDialog(new Shell()).open();
	}

	private void createTimeSeriesChart(final Composite parent) {
		Frame frame = SWT_AWT.new_Frame(parent);

		final ChartPanel chartPanel = new ChartPanel(new TimeSeriesChartOld(DatabaseOperations.getInstance().getAllTransactionsByYear(2012), new BigDecimal("0.00")).getChart());
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		frame.add(chartPanel);
	}

	private void fillStandardValuesIntoMap(final Map<Integer, BigDecimal> map) {
		for (int i = 0; i < R.MONTH_NAMES_SHORT.length; i++) {
			map.put(i, new BigDecimal("0.00"));
		}
	}

	@Override
	public void setFocus() {

	}
}
