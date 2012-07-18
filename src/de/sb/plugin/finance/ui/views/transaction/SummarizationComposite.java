package de.sb.plugin.finance.ui.views.transaction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.SwtWidgetFactory;
import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.util.R;

public class SummarizationComposite implements PropertyChangeListener {
	private final Composite content;
	private final GridData gdAlignRight;
	private final TableTransactionFilter filter;
	private BigDecimal bdFixIncome = new BigDecimal("0.00");
	private BigDecimal bdFixOutcome = new BigDecimal("0.00");
	private BigDecimal bdIncome = new BigDecimal("0.00");
	private BigDecimal bdOutcome = new BigDecimal("0.00");
	private Label lblCompleteIncome;
	private Label lblComplete;
	private Label lblCompleteOutcome;
	private Label lblFixIncome;
	private Label lblFixComplete;
	private Label lblFixOutcome;
	private Label lblVarIncome;
	private Label lblVarComplete;
	private Label lblVarOutcome;

	public SummarizationComposite(final Composite parent, final TableTransactionFilter filter) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(3, true));
		content.setLayoutData(LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL));

		this.filter = filter;
		filter.addPropertyChangeListener(this);

		gdAlignRight = LayoutFactory.createGridData(true, false, SWT.FILL, GridData.GRAB_VERTICAL);

		addCompositeCurrentMonth();
		addCompositeAccountSummarization();

		setAmounts();
	}

	private void addCompositeAccountSummarization() {
		Composite cmpCurrentSum = new Composite(content, SWT.BORDER);

		GridData gd = LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL, 1);
		cmpCurrentSum.setLayoutData(gd);
	}

	private void addCompositeCurrentMonth() {
		Composite cmpCurrentMonth = new Composite(content, SWT.BORDER);
		cmpCurrentMonth.setLayout(new GridLayout(5, true));

		cmpCurrentMonth.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL, 2));

		SwtWidgetFactory.createLabel(cmpCurrentMonth, R.LABEL_SUMMARIZATION_COMPOSITE_COM, LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL, 5));

		createLineIncome(cmpCurrentMonth);
		createLineOutcome(cmpCurrentMonth);
		createLineSeparator(cmpCurrentMonth);
		createLineDifference(cmpCurrentMonth);
	}

	private void createLineDifference(final Composite parent) {
		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE, null);

		lblFixComplete = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblFixComplete.setFont(R.FONT_ARIAL_9_BOLD);
		lblFixComplete.setForeground(R.COLOR_FONT_SUMMARIZATION_DIFF);

		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE, null);

		lblVarComplete = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblVarComplete.setFont(R.FONT_ARIAL_9_BOLD);
		lblVarComplete.setForeground(R.COLOR_FONT_SUMMARIZATION_DIFF);

		lblComplete = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblComplete.setFont(R.FONT_ARIAL_9_BOLD);
		lblComplete.setForeground(R.COLOR_FONT_SUMMARIZATION_DIFF);
	}

	private void createLineIncome(final Composite parent) {
		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_FIXINCOME, null);

		lblFixIncome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblFixIncome.setFont(R.FONT_ARIAL_9_BOLD);
		lblFixIncome.setForeground(R.COLOR_FONT_SUMMARIZATION_INCOME);

		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_VARINCOME, null);

		lblVarIncome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblVarIncome.setFont(R.FONT_ARIAL_9_BOLD);
		lblVarIncome.setForeground(R.COLOR_FONT_SUMMARIZATION_INCOME);

		lblCompleteIncome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblCompleteIncome.setFont(R.FONT_ARIAL_9_BOLD);
		lblCompleteIncome.setForeground(R.COLOR_FONT_SUMMARIZATION_INCOME);
	}

	private void createLineOutcome(final Composite parent) {
		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_FIXOUTCOME, null);

		lblFixOutcome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblFixOutcome.setFont(R.FONT_ARIAL_9_BOLD);
		lblFixOutcome.setForeground(R.COLOR_FONT_SUMMARIZATION_OUTCOME);

		SwtWidgetFactory.createLabel(parent, R.LABEL_SUMMARIZATION_COMPOSITE_VAROUTCOME, null);

		lblVarOutcome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblVarOutcome.setFont(R.FONT_ARIAL_9_BOLD);
		lblVarOutcome.setForeground(R.COLOR_FONT_SUMMARIZATION_OUTCOME);

		lblCompleteOutcome = SwtWidgetFactory.createLabelRight(parent, "", gdAlignRight);
		lblCompleteOutcome.setFont(R.FONT_ARIAL_9_BOLD);
		lblCompleteOutcome.setForeground(R.COLOR_FONT_SUMMARIZATION_OUTCOME);
	}

	private void createLineSeparator(final Composite parent) {
		SwtWidgetFactory.createLabel(parent, null, null);
		SwtWidgetFactory.createLabelSeparatorHorizontal(parent, gdAlignRight);
		SwtWidgetFactory.createLabel(parent, null, null);
		SwtWidgetFactory.createLabelSeparatorHorizontal(parent, gdAlignRight);
		SwtWidgetFactory.createLabelSeparatorHorizontal(parent, gdAlignRight);
	}

	private String formatBigDecimal(final BigDecimal value, final String additionalSign) {
		return value.toString() + " €     " + additionalSign;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		if (event.getPropertyName().equals("filterChanged") && event.getNewValue().toString().equals("true")) {
			setAmounts();
		}
	}

	private void setAmounts() {
		bdFixIncome = new BigDecimal("0.00");
		bdFixOutcome = new BigDecimal("0.00");
		bdIncome = new BigDecimal("0.00");
		bdOutcome = new BigDecimal("0.00");

		for (Transaction t : filter.getTransactions()) {
			if (t.getType().equals(TransactionType.FIX_INCOME.getName())) {
				bdFixIncome = bdFixIncome.add(t.getAmount());
			} else if (t.getType().equals(TransactionType.FIX_OUTCOME.getName())) {
				bdFixOutcome = bdFixOutcome.add(t.getAmount());
			} else if (t.getType().equals(TransactionType.INCOME.getName())) {
				bdIncome = bdIncome.add(t.getAmount());
			} else if (t.getType().equals(TransactionType.OUTCOME.getName())) {
				bdOutcome = bdOutcome.add(t.getAmount());
			}
		}

		lblFixIncome.setText(formatBigDecimal(bdFixIncome, "+"));
		lblFixOutcome.setText("-" + formatBigDecimal(bdFixOutcome, "+"));
		lblFixComplete.setText(formatBigDecimal(bdFixIncome.subtract(bdFixOutcome), "+"));
		lblVarIncome.setText(formatBigDecimal(bdIncome, "="));
		lblVarOutcome.setText("-" + formatBigDecimal(bdOutcome, "="));
		lblVarComplete.setText(formatBigDecimal(bdIncome.subtract(bdOutcome), "="));
		lblCompleteIncome.setText(formatBigDecimal(bdFixIncome.add(bdIncome), " "));
		lblCompleteOutcome.setText("-" + formatBigDecimal(bdFixOutcome.add(bdOutcome), " "));
		lblComplete.setText(formatBigDecimal(bdFixIncome.add(bdIncome).subtract(bdFixOutcome.add(bdOutcome)), " "));
	}
}
