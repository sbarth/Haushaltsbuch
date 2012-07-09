package de.sb.plugin.finance.listener;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.ui.TransactionListView;
import de.sb.plugin.finance.ui.dialogs.NewTransactionDialog;

public class NewTransactionDialogHandler extends AbstractHandler {
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		NewTransactionDialog dialog = new NewTransactionDialog(new Shell());

		if (dialog.open() == Window.OK) {
			DatabaseOperations ops = DatabaseOperations.getInstance();
			Transaction t = dialog.getTransaction();
			ops.insert(t);

			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			TransactionListView view = (TransactionListView) page.getActivePart();
			view.refreshView();
		}

		return null;
	}
}
