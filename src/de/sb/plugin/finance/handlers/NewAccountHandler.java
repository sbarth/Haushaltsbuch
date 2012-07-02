package de.sb.plugin.finance.handlers;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Category;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;
import de.sb.plugin.finance.views.dialogs.NewAccountDialog;

public class NewAccountHandler extends AbstractHandler {
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		NewAccountDialog dialog = new NewAccountDialog(new Shell());
		int result = dialog.open();

		if (result == Window.OK) {
			DatabaseOperations ops = DatabaseOperations.getInstance();

			List<Account> allAccounts = ops.getAllAccounts();
			List<Category> allCategories = ops.getAllCategories();

			Transaction t = new Transaction();
			t.setAccount(allAccounts.get(0));
			t.setAmount(new BigDecimal("500.00"));
			t.setCategory(allCategories.get(0));
			t.setDate(new GregorianCalendar());
			t.setDescription("Taschengeld");
			t.setType(TransactionType.INCOME.getName());

			ops.insert(t);
		}

		return null;
	}
}
