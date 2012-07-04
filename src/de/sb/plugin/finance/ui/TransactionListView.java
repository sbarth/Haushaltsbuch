package de.sb.plugin.finance.ui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.ui.transaction.FilterComposite;
import de.sb.plugin.finance.ui.transaction.SummarizationComposite;
import de.sb.plugin.finance.ui.transaction.TransactionTableComposite;

public class TransactionListView extends ViewPart {
	public TransactionListView() {

	}

	@Override
	public void createPartControl(final Composite parent) {
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);

		TableTransactionFilter filter = new TableTransactionFilter();

		FilterComposite filterComp = new FilterComposite(parent, getSite(), filter);
		TransactionTableComposite tableComp = new TransactionTableComposite(parent, getSite(), filter);
		SummarizationComposite summarizationComp = new SummarizationComposite(parent);
	}

	@Override
	public void setFocus() {

	}
}
