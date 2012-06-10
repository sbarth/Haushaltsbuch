package de.sb.plugin.finance.entities;

public class Category {
	private Category parent;
	private String name;

	public Category(final Category parent, final String name) {
		this.parent = parent;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Category getParent() {
		return parent;
	}

	@Override
	public String toString() {
		if (parent != null) {
			return parent + " => " + name;
		}

		return name;
	}
}
