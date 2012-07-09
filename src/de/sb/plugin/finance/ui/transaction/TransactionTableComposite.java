package de.sb.plugin.finance.ui.transaction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
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
import de.sb.plugin.finance.dummy.DummyData;
import de.sb.plugin.finance.ui.common.ElementToNodeParser;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.util.R;

public class TransactionTableComposite implements PropertyChangeListener {
	private final Composite content;
	private final int[] tableColumnWidths = R.TABLE_TRANSACTION_COLUMN_WIDTHS;
	private final IWorkbenchPartSite site;
	private final String[] tableColumnNames = R.TABLE_TRANSACTION_COLUMNS;
	private final TableTransactionFilter filter;
	private ElementToNodeParser parser;
	private String groupBy = R.COMBO_TRANSACTION_GROUPBY_DATE;
	private TreeViewer treeViewer;

	public TransactionTableComposite(final Composite parent, final IWorkbenchPartSite workbenchSite, final TableTransactionFilter filter) {
		content = new Composite(parent, SWT.BORDER);
		content.setLayout(new GridLayout(1, false));
		content.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		this.filter = filter;
		this.site = workbenchSite;

		filter.addPropertyChangeListener(this);
		filter.setDateFromTo(new GregorianCalendar(), new GregorianCalendar());
		filter.setFilterByDate(R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH);

		if (tableColumnNames.length != tableColumnWidths.length) {
			throw new RuntimeException("Anzahl der Spaltennamen stimmt nicht mit Anzahl der Spaltenbreiten überein!");
		}

		// TODO Am Ende löschen
		if (DatabaseOperations.getInstance().getAllTransactions().size() == 0) {
			DummyData.insertTransactions(1000);
		}

		parser = new ElementToNodeParser(DatabaseOperations.getInstance().getAllTransactions());

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
						groupBy = (String) o;
						setInput(parser.getTreeNode(groupBy));
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
		setInput(parser.getTreeNode(groupBy));
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
			treeViewer.refresh();

			if (filter.getFilterBySearch() != null && !filter.getFilterBySearch().isEmpty()) {
				treeViewer.expandAll();
			} else {
				treeViewer.collapseAll();
			}

			filter.setFilterChanged(false);
		}
	}

	public void refreshView() {
		parser = new ElementToNodeParser(DatabaseOperations.getInstance().getAllTransactions());
		setInput(parser.getTreeNode(groupBy));
	}

	private void setInput(TreeNode treeNode) {
		if (treeNode.getChildren() != null) {
			treeViewer.setInput(treeNode);
		}
	}
}
