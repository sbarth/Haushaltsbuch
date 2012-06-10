package de.sb.plugin.finance.views.parts.transactions.filter;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPartSite;

import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;
import de.sb.plugin.finance.util.SelectionProviderIntermediate;

public class FilterComposite {
	private Composite content;
	private IWorkbenchPartSite site;

	public FilterComposite(final Composite parent, final IWorkbenchPartSite site) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(5, false));
		this.site = site;

		GridData gd = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL);
		content.setLayoutData(gd);

		addLabels();
		addCombos();
		addTexts();
	}

	private void addCombos() {
		GridData gd = LayoutFactory.createGridData(false, false, GridData.FILL, GridData.GRAB_VERTICAL);
		final SelectionProviderIntermediate provider = new SelectionProviderIntermediate();

		final ComboViewer groupBy = new ComboViewer(content, SWT.READ_ONLY);
		groupBy.getCombo().setLayoutData(gd);
		groupBy.setContentProvider(ArrayContentProvider.getInstance());
		groupBy.setInput(R.COMBO_TRANSACTION_GROUPBY);
		groupBy.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				provider.setSelectionProviderDelegate(groupBy);
			}
		});
		groupBy.getCombo().select(1);

		final ComboViewer account = new ComboViewer(content, SWT.READ_ONLY);
		account.getCombo().setLayoutData(gd);
		account.setContentProvider(ArrayContentProvider.getInstance());
		account.setInput(R.COMBO_TRANSACTION_ACCOUNT);
		account.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				provider.setSelectionProviderDelegate(account);
			}
		});
		account.getCombo().setEnabled(false);

		final ComboViewer transactionType = new ComboViewer(content, SWT.READ_ONLY);
		transactionType.getCombo().setLayoutData(gd);
		transactionType.setContentProvider(ArrayContentProvider.getInstance());
		transactionType.setInput(R.COMBO_TRANSACTION_TYPE);
		transactionType.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				provider.setSelectionProviderDelegate(transactionType);
			}
		});
		transactionType.getCombo().select(0);

		final ComboViewer timespan = new ComboViewer(content, SWT.READ_ONLY);
		timespan.getCombo().setLayoutData(gd);
		timespan.setContentProvider(ArrayContentProvider.getInstance());
		timespan.setInput(R.COMBO_TRANSACTION_TIMESPAN);
		timespan.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				provider.setSelectionProviderDelegate(timespan);
			}
		});
		timespan.getCombo().select(1);

		site.setSelectionProvider(provider);
	}

	private void addLabels() {
		GridData gd = LayoutFactory.createGridData(false, false, GridData.FILL, GridData.GRAB_VERTICAL);

		Label groupBy = new Label(content, SWT.NONE);
		groupBy.setLayoutData(gd);
		groupBy.setText(R.LABEL_FILTER_COMPOSITE_GROUPBY);

		Label account = new Label(content, SWT.NONE);
		account.setLayoutData(gd);
		account.setText(R.LABEL_FILTER_COMPOSITE_ACCOUNT);

		Label transactionType = new Label(content, SWT.NONE);
		transactionType.setLayoutData(gd);
		transactionType.setText(R.LABEL_FILTER_COMPOSITE_TRANSACTIONTYPE);

		Label timespan = new Label(content, SWT.NONE);
		timespan.setLayoutData(gd);
		timespan.setText(R.LABEL_FILTER_COMPOSITE_TIMESPAN);

		Label search = new Label(content, SWT.NONE);
		search.setLayoutData(gd);
		search.setText(R.LABEL_FILTER_COMPOSITE_SEARCH);
	}

	private void addTexts() {
		GridData gd = LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL);

		Text search = new Text(content, SWT.BORDER);
		search.setLayoutData(gd);
	}
}
