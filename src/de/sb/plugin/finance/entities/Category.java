package de.sb.plugin.finance.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@NamedQuery(name = "findAllCategories", query = "select cat from Category cat")
public class Category implements Serializable {
	private static final long serialVersionUID = 1421857784835172168L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "parent_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Category parent;

	@Column(name = "name")
	private String name;

	public Category() {}

	public Category(final Category parent, final String name) {
		this.parent = parent;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getParent() {
		return parent;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		if (parent != null) {
			return parent + " => " + name;
		}

		return name;
	}
}
