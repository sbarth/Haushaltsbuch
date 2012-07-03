package de.sb.plugin.finance.views;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.sb.plugin.finance.filters.TableTransactionFilter;
import de.sb.plugin.finance.views.parts.transactions.filter.FilterComposite;
import de.sb.plugin.finance.views.parts.transactions.sum.SummarizationComposite;
import de.sb.plugin.finance.views.parts.transactions.table.TransactionTableComposite;

public class TransactionList extends ViewPart {
	public TransactionList() {

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
