package de.sb.plugin.finance.ui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.ui.views.transaction.FilterComposite;
import de.sb.plugin.finance.ui.views.transaction.SummarizationComposite;
import de.sb.plugin.finance.ui.views.transaction.TransactionTableComposite;

public class TransactionListView extends ViewPart {
	private FilterComposite filterComp;
	private TransactionTableComposite tableComp;
	private SummarizationComposite summarizationComp;

	public TransactionListView() {

	}

	@Override
	public void createPartControl(final Composite parent) {
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);

		TableTransactionFilter filter = new TableTransactionFilter();

		filterComp = new FilterComposite(parent, getSite(), filter);
		tableComp = new TransactionTableComposite(parent, getSite(), filter);
		summarizationComp = new SummarizationComposite(parent, filter);
	}

	public void refreshView() {
		tableComp.refreshView();
	}

	@Override
	public void setFocus() {

	}
}
