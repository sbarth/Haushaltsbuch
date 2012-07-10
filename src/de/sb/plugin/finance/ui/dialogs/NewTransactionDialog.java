package de.sb.plugin.finance.ui.dialogs;

import java.util.Arrays;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.internal.databinding.BindingStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
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
import de.sb.plugin.finance.ui.strategy.DateToCalendarStrategy;
import de.sb.plugin.finance.ui.strategy.TransactionTypeIntegerToStringStrategy;
import de.sb.plugin.finance.ui.strategy.ValueMatchCurrencyStrategy;
import de.sb.plugin.finance.ui.strategy.ValueNotEmptyStrategy;
import de.sb.plugin.finance.util.R;

//TODO AutoComplete Klasse für TextFelder schreiben
//TODO Umbuchung implementieren:
//		- Wenn Umbuchung dann gibt es keine Kategorie
//		- das 2. Accountfeld wird bei einer Umbuchung zum Pflichtfeld
@SuppressWarnings("restriction")
public class NewTransactionDialog extends AbstractDialog {
//	private final Transaction transactionTransferTo;
	private final Transaction transaction;
	private Binding bindAccount;
	private Binding bindAmount;
	private Binding bindCategory;
	private Binding bindDate;
	private Binding bindType;
	private ComboViewer cvAccount;
	private ComboViewer cvCategory;
	private ComboViewer cvTransactionType;
	// private ComboViewer cvTransfer;
	private DateTime dtDate;
	private Text txtAmount;
	private Text txtDescription;

	// private Label lblTransferTo;

	public NewTransactionDialog(Shell parentShell) {
		super(parentShell, R.TITLE_DIALOG_NEW_TRANSACTION);

		this.transaction = new Transaction();
//		this.transactionTransferTo = new Transaction();
	}

	// private void copyTransactionTransferTo() {
	// transactionTransferTo.setAmount(transaction.getAmount());
	// transactionTransferTo.setCategory(transaction.getCategory());
	// transactionTransferTo.setDate(transaction.getDate());
	// transactionTransferTo.setDescription(transaction.getDescription());
	// transactionTransferTo.setTransfer(transaction);
	// transactionTransferTo.setType(transaction.getType());
	//
	// transaction.setTransfer(transactionTransferTo);
	// }

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

		// TODO Umbuchung Label und Combo
		// lblTransferTo = SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_TO, gdLabels);
		// cvTransfer = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllAccounts(false),
		// gdRight, new AccountLabelProvider());

		SwtWidgetFactory.createLabel(comp, R.LABEL_DIALOG_NEW_TRANSACTION_DATE, gdLabels);
		dtDate = SwtWidgetFactory.createDateTime(comp, gdRight);

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

	// private boolean isTransfer() {
	// return transaction.getType() == TransactionType.TRANSFER.getName();
	// }

	@Override
	protected void okPressed() {
		BindingStatus statusAccount = (BindingStatus) bindAccount.getValidationStatus().getValue();
		BindingStatus statusAmount = (BindingStatus) bindAmount.getValidationStatus().getValue();
		BindingStatus statusCategory = (BindingStatus) bindCategory.getValidationStatus().getValue();
		BindingStatus statusDate = (BindingStatus) bindDate.getValidationStatus().getValue();
		BindingStatus statusType = (BindingStatus) bindType.getValidationStatus().getValue();

		if (checkSeverity(Arrays.asList(statusAccount, statusAmount, statusCategory, statusDate, statusType))) {
			// if (isTransfer()) {
			// copyTransactionTransferTo();
			// }

			super.okPressed();
		}
	}

	@Override
	protected void setBinding() {
		DataBindingContext ctx = new DataBindingContext();

		bindAccount = ctx.bindValue(ViewersObservables.observeSingleSelection(cvAccount), BeanProperties.value("account").observe(transaction), new ValueNotEmptyStrategy(), null);
		ControlDecorationSupport.create(bindAccount, SWT.TOP | SWT.RIGHT);

		bindAmount = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtAmount), BeanProperties.value("amount").observe(transaction), new ValueMatchCurrencyStrategy(), null);
		ControlDecorationSupport.create(bindAmount, SWT.TOP | SWT.RIGHT);

		bindCategory = ctx.bindValue(ViewersObservables.observeSingleSelection(cvCategory), BeanProperties.value("category").observe(transaction), new ValueNotEmptyStrategy(), null);
		ControlDecorationSupport.create(bindCategory, SWT.TOP | SWT.RIGHT);

		bindDate = ctx.bindValue(WidgetProperties.selection().observe(dtDate), BeanProperties.value("date").observe(transaction), new DateToCalendarStrategy(), null);
		ControlDecorationSupport.create(bindDate, SWT.TOP | SWT.RIGHT);

		bindType = ctx.bindValue(WidgetProperties.singleSelectionIndex().observe(cvTransactionType.getCombo()), BeanProperties.value("type").observe(transaction), new TransactionTypeIntegerToStringStrategy(), null);
		ControlDecorationSupport.create(bindType, SWT.TOP | SWT.RIGHT);

		// Binding ohne Validierung
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(txtDescription), BeanProperties.value("description").observe(transaction));
		// ctx.bindValue(ViewersObservables.observeSingleSelection(cvTransfer),
		// BeanProperties.value("account").observe(transactionTransferTo));
	}

	// TODO Umbuchung implementieren
	// private void setListener() {
	// cvTransfer.getCombo().setVisible(false);
	// lblTransferTo.setVisible(false);
	//
	// cvTransactionType.addSelectionChangedListener(new ISelectionChangedListener() {
	// @Override
	// public void selectionChanged(SelectionChangedEvent event) {
	// if (!(event.getSelection() instanceof IStructuredSelection)) {
	// return;
	// }
	//
	// IStructuredSelection selection = (IStructuredSelection) event.getSelection();
	// Iterator<?> iterator = selection.iterator();
	//
	// while (iterator.hasNext()) {
	// TransactionType type = (TransactionType) iterator.next();
	// if (type == TransactionType.TRANSFER) {
	// cvTransfer.getCombo().setVisible(true);
	// lblTransferTo.setVisible(true);
	// } else {
	// cvTransfer.getCombo().setVisible(false);
	// lblTransferTo.setVisible(false);
	// }
	// }
	// }
	// });
	// }
}
