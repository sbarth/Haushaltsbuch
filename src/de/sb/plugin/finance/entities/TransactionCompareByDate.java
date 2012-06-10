package de.sb.plugin.finance.entities;

import java.util.Comparator;


public class TransactionCompareByDate implements Comparator<Transaction> {
	@Override
	public int compare(final Transaction t1, final Transaction t2) {
		return t1.getDate().compareTo(t2.getDate());
	}
}
