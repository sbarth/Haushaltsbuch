package de.sb.plugin.finance.views.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;

public class NewAccountDialog extends TitleAreaDialog {
	public NewAccountDialog(final Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(final Composite parent) {
		Control contents = super.createContents(parent);

		setTitle(R.TITLE_DIALOG_NEW_ACCOUNT);

		return contents;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		GridData gdLabels = LayoutFactory.createGridData(false, false, GridData.GRAB_HORIZONTAL, GridData.GRAB_VERTICAL);
		GridData gdRightSide = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL);

		Label lblLogo = new Label(comp, SWT.NONE);
		lblLogo.setLayoutData(gdLabels);
		lblLogo.setText(R.LABEL_DIALOG_NEW_ACCOUNT_LOGO);

		ComboViewer cvLogo = new ComboViewer(comp, SWT.READ_ONLY);
		cvLogo.getCombo().setLayoutData(gdRightSide);
		cvLogo.setContentProvider(ArrayContentProvider.getInstance());

		Label lblName = new Label(comp, SWT.NONE);
		lblName.setLayoutData(gdLabels);
		lblName.setText(R.LABEL_DIALOG_NEW_ACCOUNT_NAME);

		Text txtName = new Text(comp, SWT.BORDER);
		txtName.setLayoutData(gdRightSide);

		Label lblDescription = new Label(comp, SWT.NONE);
		lblDescription.setLayoutData(gdLabels);
		lblDescription.setText(R.LABEL_DIALOG_NEW_ACCOUNT_DESCRIPTION);

		Text txtDescription = new Text(comp, SWT.BORDER);
		txtDescription.setLayoutData(gdRightSide);

		return parent;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 300);
	}
}
