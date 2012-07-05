package de.sb.plugin.finance.listener;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.ui.dialogs.NewTransactionDialog;

public class NewTransactionDialogHandler extends AbstractHandler {
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		NewTransactionDialog dialog = new NewTransactionDialog(new Shell());

		if (dialog.open() == Window.OK) {
			Transaction t = dialog.getTransaction();

			System.err.println("account: " + t.getAccount());
			System.err.println("category: " + t.getCategory());
			System.err.println("transfer: " + t.getTransfer());
			System.err.println("type: " + t.getType());
		}

		return null;
	}
}
