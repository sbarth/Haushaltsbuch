package de.sb.plugin.finance.views.parts.transactions.filter;

import java.util.Iterator;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPartSite;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.filters.TableTransactionFilter;
import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;
import de.sb.plugin.finance.util.SelectionProviderIntermediate;

//TODO 2 Buttons zum Auf und Zuklappen der Nodes
public class FilterComposite {
	private final Composite content;
	private final IWorkbenchPartSite site;
	private final TableTransactionFilter filter;
	private ComboViewer cvAccount;
	private ComboViewer cvTransactionType;
	private ComboViewer cvTimespan;

	public FilterComposite(final Composite parent, final IWorkbenchPartSite site, TableTransactionFilter filter) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(5, false));
		this.filter = filter;
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

		cvAccount = new ComboViewer(content, SWT.READ_ONLY);
		cvAccount.getCombo().setLayoutData(gd);
		cvAccount.setContentProvider(ArrayContentProvider.getInstance());
		cvAccount.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				String text = "";
				if (element instanceof Account) {
					Account acc = (Account) element;
					text = acc.getName();

					if (acc.getDescription() != null && !acc.getDescription().isEmpty()) {
						text += " - " + acc.getDescription();
					}
				}

				return text;
			}
		});
		cvAccount.setInput(DatabaseOperations.getInstance().getAllAccounts(true));
		cvAccount.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				FilterComposite.this.selectionChanged(cvAccount, event.getSelection());
			}
		});
		cvAccount.getCombo().select(0);

		cvTransactionType = new ComboViewer(content, SWT.READ_ONLY);
		cvTransactionType.getCombo().setLayoutData(gd);
		cvTransactionType.setContentProvider(ArrayContentProvider.getInstance());
		cvTransactionType.setInput(R.COMBO_TRANSACTION_TYPE);
		cvTransactionType.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				FilterComposite.this.selectionChanged(cvTransactionType, event.getSelection());
			}
		});
		cvTransactionType.getCombo().select(0);

		cvTimespan = new ComboViewer(content, SWT.READ_ONLY);
		cvTimespan.getCombo().setLayoutData(gd);
		cvTimespan.setContentProvider(ArrayContentProvider.getInstance());
		cvTimespan.setInput(R.COMBO_TRANSACTION_TIMESPAN);
		cvTimespan.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				FilterComposite.this.selectionChanged(cvTimespan, event.getSelection());
			}
		});
		cvTimespan.getCombo().select(1);

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

		final Text search = new Text(content, SWT.BORDER);
		search.setLayoutData(gd);

		search.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(Event event) {
				filter.setFilterBySearch(search.getText());
				filter.setFilterChanged(true);
			}
		});
	}

	private void selectionChanged(ComboViewer cv, ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}
		Iterator<?> iterator = ((IStructuredSelection) selection).iterator();

		while (iterator.hasNext()) {
			Object o = iterator.next();

			if (o instanceof String) {
				String value = (String) o;

				if (cv == cvTransactionType) {
					filter.setFilterByTransactionType(value);
				} else if (cv == cvTimespan) {
					filter.setFilterByDate(value);
				}
			} else if (o instanceof Account) {
				filter.setFilterByAccount((Account) o);
			}
		}

		filter.setFilterChanged(true);
	}
}
