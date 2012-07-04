package de.sb.plugin.finance.ui.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.sb.plugin.finance.entities.TransactionType;

public class TransactionTypeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String text = "";
		if (element instanceof TransactionType) {
			TransactionType type = (TransactionType) element;
			text = type.getName();
		}

		return text;
	}
}
