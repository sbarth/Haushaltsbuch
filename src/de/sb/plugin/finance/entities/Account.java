package de.sb.plugin.finance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.swt.graphics.Image;

@Entity
@SequenceGenerator(name = "account_sequence", sequenceName = "account_id_seq")
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = 2353704457224727666L;

	private long id;
	private Image logo;
	private BigDecimal startAmount;
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
	public long getId() {
		return id;
	}

	public Image getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getStartAmount() {
		return startAmount;
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

	public void setId(final long id) {
		this.id = id;
	}

	public void setLogo(final Image logo) {
		this.logo = logo;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setStartAmount(final BigDecimal startAmount) {
		this.startAmount = startAmount;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account: ID=" + getId() + ", Name=" + getName();
	}
}
