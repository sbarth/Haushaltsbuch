package de.sb.plugin.finance.entities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class Account {
	private Image logo;
	private List<Transaction> transactions;
	private String description;
	private String name;

	public Account() {
		transactions = new ArrayList<Transaction>();
	}

	public void addTransaction(final Transaction transaction) {
		transactions.add(transaction);
	}

	public String getDescription() {
		return description;
	}

	public Image getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void removeTransaction(final Transaction transaction) {
		transactions.remove(transaction);
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setLogo(final Image logo) {
		this.logo = logo;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
