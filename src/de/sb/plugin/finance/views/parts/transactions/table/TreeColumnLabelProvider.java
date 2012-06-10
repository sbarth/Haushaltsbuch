package de.sb.plugin.finance.views.parts.transactions.table;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeNode;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.util.R;

public class TreeColumnLabelProvider extends ColumnLabelProvider {
	private String columnName;

	public TreeColumnLabelProvider(final String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String getText(final Object element) {
		String result = "";

		Object value = ((TreeNode) element).getValue();

		if (value.getClass() == Transaction.class) {
			Transaction transaction = (Transaction) value;

			if (R.COLUMN_NAME_AMOUNT.equals(columnName)) {
				result = R.CURRENCY_FORMAT.format(transaction.getAmount().doubleValue());
			}
			if (R.COLUMN_NAME_CATEGORY.equals(columnName)) {
				result = transaction.getCategory().toString();
			}
			if (R.COLUMN_NAME_DATE.equals(columnName)) {
				SimpleDateFormat format = new SimpleDateFormat(R.TABLE_TRANSACTION_DATE_FORMAT);
				result = format.format(transaction.getDate().getTime());
			}
			if (R.COLUMN_NAME_DESCRIPTION.equals(columnName)) {
				result = transaction.getDescription();
			}
			if (R.COLUMN_NAME_TRANSACTIONTYPE.equals(columnName)) {
				result = transaction.getType().getName();
			}
		} else if (value.getClass() == String.class) {
			if (R.COLUMN_NAME_DATE.equals(columnName)) {
				result = (String) value;
			}
		}

		return result;
	}
}
