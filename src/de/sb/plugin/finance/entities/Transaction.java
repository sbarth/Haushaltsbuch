package de.sb.plugin.finance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.sb.plugin.finance.util.R;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
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

	public Transaction() {}

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

	public String getType() {
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

	public void setId(long id) {
		this.id = id;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return R.DATE_FORMAT.format(date.getTime()) + ": " + category + " = " + amount;
	}
}
