package de.sb.plugin.finance.ui.views.transaction;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPartSite;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.io.DatabaseOperations;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.SwtWidgetFactory;
import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.ui.provider.AccountLabelProvider;
import de.sb.plugin.finance.util.ObjectFactory;
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

	private final ISelectionChangedListener filterChangedListener = new ISelectionChangedListener() {
		@Override
		public void selectionChanged(final SelectionChangedEvent event) {
			FilterComposite.this.selectionChanged((ComboViewer) event.getSource(), event.getSelection());
		}
	};

	public FilterComposite(final Composite parent, final IWorkbenchPartSite site, final TableTransactionFilter filter) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(5, false));
		content.setLayoutData(LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL));

		this.filter = filter;
		this.site = site;

		addLabels();
		addCombos();
		addTexts();
	}

	private void addCombos() {
		GridData gd = LayoutFactory.createGridData(false, false, GridData.FILL, GridData.GRAB_VERTICAL);
		final SelectionProviderIntermediate provider = new SelectionProviderIntermediate();

		final ComboViewer groupBy = SwtWidgetFactory.createComboViewer(content, Arrays.asList(R.COMBO_TRANSACTION_GROUPBY), gd, null);
		groupBy.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				provider.setSelectionProviderDelegate(groupBy);
			}
		});
		// site.setSelectionProvider(groupBy); ausreichend
		groupBy.getCombo().select(1);

		cvAccount = SwtWidgetFactory.createComboViewer(content, DatabaseOperations.getInstance().getAllAccounts(true), gd, new AccountLabelProvider());
		cvAccount.addSelectionChangedListener(filterChangedListener);
		cvAccount.getCombo().select(0);

		cvTransactionType = SwtWidgetFactory.createComboViewer(content, Arrays.asList(R.COMBO_TRANSACTION_TYPE), gd, null);
		cvTransactionType.addSelectionChangedListener(filterChangedListener);
		cvTransactionType.getCombo().select(0);

		cvTimespan = SwtWidgetFactory.createComboViewer(content, Arrays.asList(R.COMBO_TRANSACTION_TIMESPAN), gd, null);
		cvTimespan.addSelectionChangedListener(filterChangedListener);
		cvTimespan.getCombo().select(1);

		site.setSelectionProvider(provider);
	}

	private void addLabels() {
		GridData gd = LayoutFactory.createGridData(false, false, GridData.FILL, GridData.GRAB_VERTICAL);

		SwtWidgetFactory.createLabel(content, R.LABEL_FILTER_COMPOSITE_GROUPBY, gd);
		SwtWidgetFactory.createLabel(content, R.LABEL_FILTER_COMPOSITE_ACCOUNT, gd);
		SwtWidgetFactory.createLabel(content, R.LABEL_FILTER_COMPOSITE_TRANSACTIONTYPE, gd);
		SwtWidgetFactory.createLabel(content, R.LABEL_FILTER_COMPOSITE_TIMESPAN, gd);
		SwtWidgetFactory.createLabel(content, R.LABEL_FILTER_COMPOSITE_SEARCH, gd);
	}

	private void addTexts() {
		GridData gd = LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL);

		final Text search = new Text(content, SWT.BORDER);
		search.setLayoutData(gd);

		search.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				filter.setFilterBySearch(search.getText());
				filter.setFilterChanged(true);
			}
		});
	}

	private void selectionChanged(final ComboViewer cv, final ISelection selection) {
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
					filter.setDateFromTo(ObjectFactory.getCurrentDay(0, 0, 0, 0), ObjectFactory.getCurrentDay(23, 59, 59, 999));
					filter.setFilterByDate(value);
				}
			} else if (o instanceof Account) {
				filter.setFilterByAccount((Account) o);
			}
		}

		filter.setFilterChanged(true);
	}
}
