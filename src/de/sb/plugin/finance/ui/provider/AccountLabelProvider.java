package de.sb.plugin.finance.ui.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.sb.plugin.finance.entities.Account;

public class AccountLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String text = "";
		if (element instanceof Account) {
			Account acc = (Account) element;
			text = acc.getName();

			if (acc.getDescription() != null && !acc.getDescription().isEmpty()) {
				text += " - " + acc.getDescription();
			}
		}

		return text;
	}
}
