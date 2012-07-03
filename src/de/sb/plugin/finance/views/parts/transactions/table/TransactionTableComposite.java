package de.sb.plugin.finance.views.parts.transactions.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.filters.TableTransactionFilter;
import de.sb.plugin.finance.util.ElementToNodeParser;
import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;

public class TransactionTableComposite implements PropertyChangeListener {
	private final Composite content;
	private final ElementToNodeParser parser;
	private final int[] tableColumnWidths;
	private final IWorkbenchPartSite site;
	private final List<Transaction> transactions;
	private final String[] tableColumnNames;
	private final TableTransactionFilter filter;
	private TreeViewer treeViewer;

	public TransactionTableComposite(final Composite parent, final IWorkbenchPartSite workbenchSite, final TableTransactionFilter filter) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(1, false));
		this.filter = filter;
		site = workbenchSite;

		filter.addPropertyChangeListener(this);

		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		GridData gd = LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL);
		content.setLayoutData(gd);

		tableColumnNames = R.TABLE_TRANSACTION_COLUMNS;
		tableColumnWidths = R.TABLE_TRANSACTION_COLUMN_WIDTHS;

		if (tableColumnNames.length != tableColumnWidths.length) {
			throw new RuntimeException("Anzahl der Spaltennamen stimmt nicht mit Anzahl der Spaltenbreiten überein!");
		}

		transactions = DatabaseOperations.getInstance().getAllTransactions();

		parser = new ElementToNodeParser(transactions);

		addSelectionListener();
		addTableTreeViewer();
	}

	private void addSelectionListener() {
		ISelectionService selectionService = site.getWorkbenchWindow().getSelectionService();

		selectionService.addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}

				Iterator<?> iterator = ((IStructuredSelection) selection).iterator();

				while (iterator.hasNext()) {
					Object o = iterator.next();

					if (o instanceof String) {
						String groupBy = (String) o;

						switch (groupBy) {
							case R.COMBO_TRANSACTION_GROUPBY_ACCOUNT:
								// TODO GrouBy implementieren
								break;
							case R.COMBO_TRANSACTION_GROUPBY_BRANCH:
								// TODO GrouBy implementieren
								break;
							case R.COMBO_TRANSACTION_GROUPBY_CATEGORY:
								// TODO GrouBy implementieren
								break;
							case R.COMBO_TRANSACTION_GROUPBY_DATE:
								treeViewer.setInput(parser.parseByDay());
								break;
							case R.COMBO_TRANSACTION_GROUPBY_MONTH:
								// TODO GrouBy implementieren
								break;
							case R.COMBO_TRANSACTION_GROUPBY_NOTHING:
								treeViewer.setInput(parser.parseByNothing());
								break;
							case R.COMBO_TRANSACTION_GROUPBY_TRANSACTION_TYPE:
								// TODO GrouBy implementieren
								break;
							case R.COMBO_TRANSACTION_GROUPBY_WEEK:
								treeViewer.setInput(parser.parseByWeek());
								break;
							default:
								break;
						}
					}
				}
			}
		});
	}

	private void addTableTreeViewer() {
		GridData gd = LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL);

		treeViewer = new TreeViewer(content, SWT.BORDER | SWT.NO_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		treeViewer.setContentProvider(new TreeContentProvider());

		for (int index = 0; index < tableColumnNames.length; index++) {
			int columnWidth = tableColumnWidths[index];
			String columnName = tableColumnNames[index];

			createTreeViewerColumn(columnName, columnWidth);
		}

		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLayoutData(gd);

		treeViewer.addFilter(filter);
		treeViewer.setInput(parser.parseByDay());
	}

	private void createTreeViewerColumn(final String columnName, final int columnWidth) {
		TreeViewerColumn tvCol = new TreeViewerColumn(treeViewer, SWT.NONE);

		tvCol.setLabelProvider(new TreeColumnLabelProvider(columnName));

		TreeColumn col = tvCol.getColumn();
		col.setText(columnName);
		col.setWidth(columnWidth);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("filterChanged") && event.getNewValue().toString().equals("true")) {
			filter.setFilterChanged(false);
			treeViewer.refresh();
		}
	}
}
