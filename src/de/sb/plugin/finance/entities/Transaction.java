package de.sb.plugin.finance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.sb.plugin.finance.util.Queries;

@Entity
@Table(name = "transaction")
@NamedQuery(name = Queries.FIND_ALL_TRANSACTIONS, query = Queries.FIND_ALL_TRANSACTIONS_QUERY)
public class Transaction extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -5996366282282751725L;

	@Column(name = "account_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private Account account;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "date")
	private Calendar date;

	@Column(name = "category_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private String type;

	@Column(name = "transfer_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Transaction transfer;

	public Transaction() {
		date = new GregorianCalendar();
	}

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

	public long getId() {
		return id;
	}

	public Transaction getTransfer() {
		return transfer;
	}

	public String getType() {
		return type;
	}

	public void setAccount(final Account account) {
		Account old = this.account;
		this.account = account;
		changes.firePropertyChange("account", old, account);
	}

	public void setAmount(final BigDecimal amount) {
		BigDecimal old = this.amount;
		this.amount = amount;
		changes.firePropertyChange("amount", old, amount);
	}

	public void setCategory(final Category category) {
		Category old = this.category;
		this.category = category;
		changes.firePropertyChange("category", old, category);
	}

	public void setDate(final Calendar date) {
		Calendar old = this.date;
		this.date = date;
		changes.firePropertyChange("date", old, date);
	}

	public void setDescription(final String description) {
		String old = this.description;
		this.description = description;
		changes.firePropertyChange("description", old, description);
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTransfer(Transaction transfer) {
		Transaction old = this.transfer;
		this.transfer = transfer;
		changes.firePropertyChange("transfer", old, transfer);
	}

	public void setType(final String type) {
		String old = this.type;
		this.type = type;
		changes.firePropertyChange("type", old, type);
	}

	@Override
	public String toString() {
		return "Transaction: ID=" + id + ", " + category.getName() + ", " + amount;
	}
}
