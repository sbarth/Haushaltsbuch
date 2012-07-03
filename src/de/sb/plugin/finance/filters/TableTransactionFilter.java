package de.sb.plugin.finance.filters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.util.Compare;
import de.sb.plugin.finance.util.R;

public class TableTransactionFilter extends ViewerFilter {
	private Calendar calFrom;
	private Calendar calTo;
	private Account filterByAccount;
	private String filterByDate;
	private String filterBySearch;
	private String filterByTransactionType;

	public TableTransactionFilter() {
		calFrom = new GregorianCalendar();
		calTo = new GregorianCalendar();
	}

	public Calendar getCalFrom() {
		return calFrom;
	}

	public Calendar getCalTo() {
		return calTo;
	}

	public boolean matches(final Transaction transaction) {
		boolean matches = true;

		if (filterByAccount != null && filterByAccount.getId() != 0) {
			matches = matches && (transaction.getAccount().getId() == filterByAccount.getId());
		}
		if (filterByDate != null && !filterByDate.equals("")) {
			if (calFrom != null && calTo != null) {
				boolean from = Compare.areOnSameDay(calFrom, transaction.getDate());
				boolean to = Compare.areOnSameDay(calTo, transaction.getDate());

				matches = matches && (calFrom.before(transaction.getDate()) && calTo.after(transaction.getDate()) || from || to);
			}
		}
		if (filterByTransactionType != null && !filterByTransactionType.equals("")) {
			if (filterByTransactionType.equals(R.COMBO_TRANSACTION_TYPE_INCOME)) {
				matches = matches && (TransactionType.FIX_INCOME.getName().equals(transaction.getType()) || //
						TransactionType.INCOME.getName().equals(transaction.getType()));
			} else if (filterByTransactionType.equals(R.COMBO_TRANSACTION_TYPE_OUTCOME)) {
				matches = matches && (TransactionType.FIX_OUTCOME.getName().equals(transaction.getType()) //
						|| TransactionType.OUTCOME.getName().equals(transaction.getType()));
			}
		}

		return matches;
	}

	@Override
	public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
		TreeNode node = (TreeNode) element;
		if (node.getValue().getClass() == Transaction.class) {
			return matches((Transaction) node.getValue());
		} else if (node.getValue().getClass() == String.class) {
			for (TreeNode child : node.getChildren()) {
				if (child.getValue().getClass() == Transaction.class) {
					boolean match = matches((Transaction) child.getValue());
					if (match) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void setDateFromTo(final Calendar calFrom, final Calendar calTo) {
		this.calFrom = calFrom;
		this.calTo = calTo;
	}

	public void setFilterByAccount(final Account o) {
		filterByAccount = o;
	}

	public void setFilterByDate(final String date) {
		switch (date) {
			case R.COMBO_TRANSACTION_TIMESPAN_CURRENTDAY:
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH:
				calFrom.set(Calendar.DAY_OF_MONTH, 1);
				calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER:
				int startMonth = (calFrom.get(Calendar.MONTH) / 3);

				calFrom.set(Calendar.MONTH, startMonth * 3);
				calFrom.set(Calendar.DAY_OF_MONTH, 1);

				calTo.set(Calendar.MONTH, startMonth * 3 + 2);
				calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR:
				calFrom.set(Calendar.DAY_OF_YEAR, 1);
				calTo.set(Calendar.DAY_OF_YEAR, calTo.getActualMaximum(Calendar.DAY_OF_YEAR));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH:
				calFrom.add(Calendar.MONTH, -1);
				calFrom.set(Calendar.DAY_OF_MONTH, 1);

				calTo.add(Calendar.MONTH, -1);
				calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER:
				startMonth = (calFrom.get(Calendar.MONTH) / 3);

				calFrom.set(Calendar.MONTH, startMonth * 3);
				calFrom.add(Calendar.MONTH, -3);
				calFrom.set(Calendar.DAY_OF_MONTH, 1);

				calTo.set(Calendar.MONTH, startMonth * 3 + 2);
				calTo.add(Calendar.MONTH, -3);
				calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR:
				calFrom.add(Calendar.YEAR, -1);
				calFrom.set(Calendar.DAY_OF_YEAR, 1);
				calTo.add(Calendar.YEAR, -1);
				calTo.set(Calendar.DAY_OF_YEAR, calTo.getActualMaximum(Calendar.DAY_OF_YEAR));
				break;
			case R.COMBO_TRANSACTION_TIMESPAN_SELECT:
				break;
			default:
				break;
		}

		filterByDate = date;
	}

	public void setFilterBySearch(final String filterBySearch) {
		this.filterBySearch = filterBySearch;
	}

	public void setFilterByTransactionType(final String transactionType) {
		filterByTransactionType = transactionType;
	}
}
