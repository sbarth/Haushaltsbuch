package de.sb.plugin.finance.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
	private Account account;
	private BigDecimal amount;
	private Calendar date;
	private Category category;
	private String description;
	private TransactionType type;

	public Account getAccount() {
		return account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Category getCategory() {
		return category;
	}

	public Calendar getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public TransactionType getType() {
		return type;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public void setDate(final Calendar date) {
		this.date = date;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setType(final TransactionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

		return format.format(date.getTime()) + ": " + category + " = " + amount;
	}
}
