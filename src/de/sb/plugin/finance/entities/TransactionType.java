package de.sb.plugin.finance.entities;

import de.sb.plugin.finance.util.R;

public enum TransactionType {
	FIX_INCOME(R.TRANSACTION_TYPE_FIX_INCOME),
	FIX_OUTCOME(R.TRANSACTION_TYPE_FIX_OUTCOME),
	INCOME(R.TRANSACTION_TYPE_INCOME),
	OUTCOME(R.TRANSACTION_TYPE_OUTCOME),
	TRANSFER(R.TRANSACTION_TYPE_TRANSFER), ;

	private String name;

	TransactionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
