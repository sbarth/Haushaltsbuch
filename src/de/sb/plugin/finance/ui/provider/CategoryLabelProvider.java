package de.sb.plugin.finance.ui.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.sb.plugin.finance.entities.Category;

public class CategoryLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String text = "";
		if (element instanceof Category) {
			Category cat = (Category) element;
			text = cat.getName();
		}

		return text;
	}
}
