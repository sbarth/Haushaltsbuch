package de.sb.plugin.finance.ui.parts.transaction;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Color;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.util.R;

public class TreeColumnLabelProvider extends ColumnLabelProvider {
	private final String columnName;

	public TreeColumnLabelProvider(final String columnName) {
		this.columnName = columnName;
	}

	@Override
	public Color getBackground(Object element) {
		Object value = ((TreeNode) element).getValue();

		if (value instanceof Transaction) {
			String type = ((Transaction) value).getType();

			if (TransactionType.FIX_INCOME.getName().equals(type) || TransactionType.INCOME.getName().equals(type)) {
				return R.COLOR_TABLE_ITEM_INCOME;
			} else if (TransactionType.FIX_OUTCOME.getName().equals(type) || TransactionType.OUTCOME.getName().equals(type)) {
				return R.COLOR_TABLE_ITEM_OUTCOME;
			}
		}

		return super.getBackground(element);
	}

	@Override
	public String getText(final Object element) {
		String result = "";

		Object value = ((TreeNode) element).getValue();

		if (value instanceof Transaction) {
			Transaction transaction = (Transaction) value;

			if (R.COLUMN_NAME_AMOUNT.equals(columnName)) {
				result = R.CURRENCY_FORMAT.format(transaction.getAmount().doubleValue());
			}
			if (R.COLUMN_NAME_CATEGORY.equals(columnName)) {
				result = transaction.getCategory().toString();
			}
			if (R.COLUMN_NAME_DATE.equals(columnName)) {
				result = R.DATE_FORMAT.format(transaction.getDate().getTime());
			}
			if (R.COLUMN_NAME_DESCRIPTION.equals(columnName)) {
				result = transaction.getDescription();
			}
			if (R.COLUMN_NAME_TRANSACTIONTYPE.equals(columnName)) {
				result = transaction.getType();
			}
		} else if (value instanceof String) {
			if (R.COLUMN_NAME_DATE.equals(columnName)) {
				result = (String) value;
			}
		}

		return result;
	}
}
