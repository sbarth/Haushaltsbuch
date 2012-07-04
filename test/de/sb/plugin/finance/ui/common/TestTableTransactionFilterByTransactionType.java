package de.sb.plugin.finance.ui.common;

import junit.framework.Assert;

import org.junit.Test;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.ui.common.TableTransactionFilter;
import de.sb.plugin.finance.util.R;

public class TestTableTransactionFilterByTransactionType {
	@Test
	public void filterByTransactionTypeAll() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_ALL);
		Transaction t = new Transaction();
		t.setType(TransactionType.INCOME.getName());

		Assert.assertTrue(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeFixIncome() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_INCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.FIX_INCOME.getName());

		Assert.assertTrue(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeFixIncomeFalse() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_INCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.FIX_OUTCOME.getName());

		Assert.assertFalse(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeFixOutcome() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_OUTCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.FIX_OUTCOME.getName());

		Assert.assertTrue(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeFixOutcomeFalse() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_OUTCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.FIX_INCOME.getName());

		Assert.assertFalse(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeIncome() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_INCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.INCOME.getName());

		Assert.assertTrue(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeIncomeFalse() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_INCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.OUTCOME.getName());

		Assert.assertFalse(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeOutcome() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_OUTCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.OUTCOME.getName());

		Assert.assertTrue(filter.matches(t));
	}

	@Test
	public void filterByTransactionTypeOutcomeFalse() {
		TableTransactionFilter filter = new TableTransactionFilter();
		filter.setFilterByTransactionType(R.COMBO_TRANSACTION_TYPE_OUTCOME);
		Transaction t = new Transaction();
		t.setType(TransactionType.INCOME.getName());

		Assert.assertFalse(filter.matches(t));
	}
}
