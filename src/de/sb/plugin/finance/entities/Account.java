package de.sb.plugin.finance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.sb.plugin.finance.util.Queries;

@Entity
@Table(name = "account")
@NamedQuery(name = Queries.FIND_ALL_ACCOUNTS, query = Queries.FIND_ALL_ACCOUNTS_QUERY)
public class Account implements Serializable {
	private static final long serialVersionUID = 2353704457224727666L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "start_amount")
	private BigDecimal startAmount;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<Transaction> transactions;

	@Column(name = "description")
	private String description;

	@Column(name = "logo")
	private String logo;

	@Column(name = "name")
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

	public long getId() {
		return id;
	}

	public String getLogo() {
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

	public void setLogo(final String logo) {
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
