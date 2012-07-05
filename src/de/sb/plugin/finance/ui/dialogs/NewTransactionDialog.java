package de.sb.plugin.finance.ui.dialogs;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingStatus;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.SwtWidgetFactory;
import de.sb.plugin.finance.ui.provider.AccountLabelProvider;
import de.sb.plugin.finance.ui.provider.CategoryLabelProvider;
import de.sb.plugin.finance.ui.provider.TransactionTypeLabelProvider;
import de.sb.plugin.finance.ui.strategy.TransactionTypeIntegerToStringStrategy;
import de.sb.plugin.finance.util.R;

//TODO Datum DateTimeWidget einfügen
//TODO AutoComplete Klasse für TextFelder schreiben
//TODO Superklasse für Dialoge schreiben
@SuppressWarnings("restriction")
public class NewTransactionDialog extends TitleAreaDialog {
	private final Transaction transaction;
	private ComboViewer cvAccount;
	private ComboViewer cvCategory;
	private ComboViewer cvTransactionType;
	private ComboViewer cvParent;
	private Text txtDate;
	private Text txtAmount;
	private Text txtDescription;

	public NewTransactionDialog(Shell parentShell) {
		super(parentShell);

		this.transaction = new Transaction();
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
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		GridData gdLabels = LayoutFactory.createGridData(false, false, GridData.GRAB_HORIZONTAL, GridData.GRAB_VERTICAL);
		GridData gdRight = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL);

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_ACCOUNT, gdLabels);
		cvAccount = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllAccounts(false), gdRight, new AccountLabelProvider());

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_CATEGORY, gdLabels);
		cvCategory = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllCategories(), gdRight, new CategoryLabelProvider());

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_TRANSACTION_TYPE, gdLabels);
		cvTransactionType = SwtWidgetFactory.createComboViewer(comp, Arrays.asList(TransactionType.values()), gdRight, new TransactionTypeLabelProvider());

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_TO, gdLabels);
		cvParent = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllAccounts(false), gdRight, new AccountLabelProvider());

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_DATE, gdLabels);
		txtDate = SwtWidgetFactory.createText(comp, gdRight);

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_AMOUNT, gdLabels);
		txtAmount = SwtWidgetFactory.createCurrencyText(comp, gdRight);

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_DESCRIPTION, gdLabels);
		txtDescription = SwtWidgetFactory.createMultiText(comp, gdRight);

		return parent;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(300, 400);
	}

	public Transaction getTransaction() {
		return transaction;
	}

	// @Override
	// protected void okPressed() {
	// BindingStatus statusName = (BindingStatus) bindName.getValidationStatus().getValue();
	// BindingStatus statusStartAmount = (BindingStatus) bindStartAmount.getValidationStatus().getValue();

	// if (checkSeverity(Arrays.asList(statusName, statusStartAmount))) {
	// super.okPressed();
	// }
	// }

	private void setBinding() {
		DataBindingContext ctx = new DataBindingContext();

		// Binding mit Validierung
		Binding bindAccount = ctx.bindValue(ViewersObservables.observeSingleSelection(cvAccount), BeanProperties.value("account").observe(transaction));
		Binding bindAmount;
		Binding bindCategory = ctx.bindValue(ViewersObservables.observeSingleSelection(cvCategory), BeanProperties.value("category").observe(transaction));
		Binding bindDate;
		Binding bindType = ctx.bindValue(WidgetProperties.singleSelectionIndex().observe(cvTransactionType.getCombo()), BeanProperties.value("type").observe(transaction), new TransactionTypeIntegerToStringStrategy(), null);

		// Binding ohne Validierung
		Binding bindDescription;
		Binding bindParent = ctx.bindValue(ViewersObservables.observeSingleSelection(cvParent), BeanProperties.value("transfer").observe(transaction));

		// bindName = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtName),
		// BeanProperties.value("name").observe(account), new ValueNotEmptyStrategy(), null);
		// ControlDecorationSupport.create(bindName, SWT.TOP | SWT.RIGHT);
		//
		// bindStartAmount = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtStartAmount),
		// BeanProperties.value("startAmount").observe(account), new ValueMatchCurrencyStrategy(), null);
		// ControlDecorationSupport.create(bindStartAmount, SWT.TOP | SWT.RIGHT);

		// ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtDescription),
		// BeanProperties.value("description").observe(account));

		// ctx.bindValue(WidgetProperties.text(SWT.Selection).observe(cvLogo),
		// BeanProperties.value("logo").observe(account)); //TODO Logo einbauen
	}
}
