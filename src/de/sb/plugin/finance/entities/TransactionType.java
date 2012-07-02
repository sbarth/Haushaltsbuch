package de.sb.plugin.finance.entities;

public enum TransactionType {
	FIX_INCOME("Einnahme (Fix)"),
	FIX_OUTCOME("Ausgabe (Fix)"),
	INCOME("Einnahme (Variabel)"),
	OUTCOME("Ausgabe (Variabel)"), ;

	private String name;

	TransactionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
