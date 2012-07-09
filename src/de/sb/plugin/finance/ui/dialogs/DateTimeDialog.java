package de.sb.plugin.finance.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.ui.common.LayoutFactory;

public class DateTimeDialog extends Dialog {

	protected DateTimeDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));

		DateTime dt = new DateTime(composite, SWT.BORDER);
		dt.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		return composite;
	}
}
