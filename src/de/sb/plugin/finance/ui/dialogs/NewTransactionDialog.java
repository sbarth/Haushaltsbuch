package de.sb.plugin.finance.ui.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.util.LayoutFactory;
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

		Label lblAccount = new Label(comp, SWT.NONE);
		lblAccount.setLayoutData(gdLabels);
		lblAccount.setText("Konto: ");

		ComboViewer cvAccount = new ComboViewer(comp, SWT.READ_ONLY);
		cvAccount.getCombo().setLayoutData(gdRightSide);
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
		cvAccount.getCombo().select(0);

		return parent;
	}
}
