package de.sb.plugin.finance.ui.dialogs;

import java.util.Arrays;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.SwtWidgetFactory;
import de.sb.plugin.finance.ui.provider.AccountLabelProvider;
import de.sb.plugin.finance.ui.provider.CategoryLabelProvider;
import de.sb.plugin.finance.ui.provider.TransactionTypeLabelProvider;
import de.sb.plugin.finance.util.R;

public class NewTransactionDialog extends TitleAreaDialog {
	public NewTransactionDialog(Shell parentShell) {
		super(parentShell);

	}

	@Override
	protected Control createContents(final Composite parent) {
		Control contents = super.createContents(parent);

		setTitle(R.TITLE_DIALOG_NEW_TRANSACTION);

		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		GridData gdLabels = LayoutFactory.createGridData(false, false, GridData.GRAB_HORIZONTAL, GridData.GRAB_VERTICAL);
		GridData gdRightSide = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL);

		SwtWidgetFactory.createLabel(comp, "Konto: ", gdLabels);
		SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllAccounts(false), gdRightSide, new AccountLabelProvider());

		SwtWidgetFactory.createLabel(comp, "Kategorie: ", gdLabels);
		SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllCategories(), gdRightSide, new CategoryLabelProvider());

		SwtWidgetFactory.createLabel(comp, "Buchungstyp: ", gdLabels);
		SwtWidgetFactory.createComboViewer(comp, Arrays.asList(TransactionType.values()), gdRightSide, new TransactionTypeLabelProvider());

		return parent;
	}
}
