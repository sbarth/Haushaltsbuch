package de.sb.plugin.finance.ui.parts.transaction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;

public class SummarizationComposite {
	private Composite content;

	public SummarizationComposite(final Composite parent) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(3, true));

		GridData gd = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL);
		content.setLayoutData(gd);

		addCompositeCurrentMonth();
		addCompositeAccountSummarization();
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

		Label headline = new Label(cmpCurrentMonth, SWT.NONE);
		headline.setLayoutData(LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL, 5));
		headline.setText(R.LABEL_SUMMARIZATION_COMPOSITE_CURRENTMONTH);

		createLineIncome(cmpCurrentMonth);
		createLineOutcome(cmpCurrentMonth);
		createLineDifference(cmpCurrentMonth);
	}

	private void createLineDifference(final Composite parent) {
		Label fixIncome = new Label(parent, SWT.NONE);
		fixIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE);

		Label fixIncomeValue = new Label(parent, SWT.NONE);
		fixIncomeValue.setText("0,00 €     +");

		Label varIncome = new Label(parent, SWT.NONE);
		varIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE);

		Label varIncomeValue = new Label(parent, SWT.NONE);
		varIncomeValue.setText("0,00 €     =");

		Label completeIncome = new Label(parent, SWT.NONE);
		completeIncome.setText("0,00 €");
	}

	private void createLineIncome(final Composite parent) {
		Label fixIncome = new Label(parent, SWT.NONE);
		fixIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_FIXINCOME);

		Label fixIncomeValue = new Label(parent, SWT.NONE);
		fixIncomeValue.setText("0,00 €     +");

		Label varIncome = new Label(parent, SWT.NONE);
		varIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_VARINCOME);

		Label varIncomeValue = new Label(parent, SWT.NONE);
		varIncomeValue.setText("0,00 €     =");

		Label completeIncome = new Label(parent, SWT.NONE);
		completeIncome.setText("0,00 €");
	}

	private void createLineOutcome(final Composite parent) {
		Label fixIncome = new Label(parent, SWT.NONE);
		fixIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_FIXOUTCOME);

		Label fixIncomeValue = new Label(parent, SWT.NONE);
		fixIncomeValue.setText("0,00 €     +");

		Label varIncome = new Label(parent, SWT.NONE);
		varIncome.setText(R.LABEL_SUMMARIZATION_COMPOSITE_VAROUTCOME);

		Label varIncomeValue = new Label(parent, SWT.NONE);
		varIncomeValue.setText("0,00 €     =");

		Label completeIncome = new Label(parent, SWT.NONE);
		completeIncome.setText("0,00 €");
	}
}
