package de.sb.plugin.finance.views.dialogs;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.listener.CurrencyVerifyListener;
import de.sb.plugin.finance.listener.ValueMatchCurrencyStrategy;
import de.sb.plugin.finance.listener.ValueNotEmptyStrategy;
import de.sb.plugin.finance.util.LayoutFactory;
import de.sb.plugin.finance.util.R;

@SuppressWarnings("restriction")
public class NewAccountDialog extends TitleAreaDialog {
	private final Account account;
	private ComboViewer cvLogo;
	private Text txtDescription;
	private Text txtName;
	private Text txtStartAmount;
	private Binding bindName;
	private Binding bindStartAmount;

	public NewAccountDialog(final Shell parentShell) {
		super(parentShell);

		this.account = new Account();
	}

	private boolean checkSeverity(List<BindingStatus> statusList) {
		for (BindingStatus status : statusList) {
			if (status.getSeverity() != ValidationStatus.ok().getSeverity()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void create() {
		super.create();

		setBinding();
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

		// TODO Logo einbauen
		// Label lblLogo = new Label(comp, SWT.NONE);
		// lblLogo.setLayoutData(gdLabels);
		// lblLogo.setText(R.LABEL_DIALOG_NEW_ACCOUNT_LOGO);
		//
		// cvLogo = new ComboViewer(comp, SWT.READ_ONLY);
		// cvLogo.getCombo().setLayoutData(gdRightSide);
		// cvLogo.setContentProvider(ArrayContentProvider.getInstance());

		Label lblName = new Label(comp, SWT.NONE);
		lblName.setLayoutData(gdLabels);
		lblName.setText(R.LABEL_DIALOG_NEW_ACCOUNT_NAME);

		txtName = new Text(comp, SWT.BORDER);
		txtName.setLayoutData(gdRightSide);

		Label lblDescription = new Label(comp, SWT.NONE);
		lblDescription.setLayoutData(gdLabels);
		lblDescription.setText(R.LABEL_DIALOG_NEW_ACCOUNT_DESCRIPTION);

		txtDescription = new Text(comp, SWT.BORDER);
		txtDescription.setLayoutData(gdRightSide);

		Label lblStartAmount = new Label(comp, SWT.NONE);
		lblStartAmount.setLayoutData(gdLabels);
		lblStartAmount.setText(R.LABEL_DIALOG_NEW_ACCOUNT_STARTAMOUNT);

		txtStartAmount = new Text(comp, SWT.BORDER);
		txtStartAmount.setLayoutData(gdRightSide);
		txtStartAmount.addVerifyListener(new CurrencyVerifyListener());

		return parent;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 300);
	}

	@Override
	protected void okPressed() {
		BindingStatus statusName = (BindingStatus) bindName.getValidationStatus().getValue();
		BindingStatus statusStartAmount = (BindingStatus) bindStartAmount.getValidationStatus().getValue();

		if (checkSeverity(Arrays.asList(statusName, statusStartAmount))) {
			super.okPressed();
		}
	}

	private void setBinding() {
		DataBindingContext ctx = new DataBindingContext();

		// Binding mit Validierung
		bindName = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtName), BeanProperties.value("name").observe(account), new ValueNotEmptyStrategy(), null);
		ControlDecorationSupport.create(bindName, SWT.TOP | SWT.RIGHT);

		bindStartAmount = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtStartAmount), BeanProperties.value("startAmount").observe(account), new ValueMatchCurrencyStrategy(), null);
		ControlDecorationSupport.create(bindStartAmount, SWT.TOP | SWT.RIGHT);

		// Binding ohne Validierung
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtDescription), BeanProperties.value("description").observe(account));

		// ctx.bindValue(WidgetProperties.text(SWT.Selection).observe(cvLogo),
		// BeanProperties.value("logo").observe(account)); //TODO Logo einbauen
	}
}
