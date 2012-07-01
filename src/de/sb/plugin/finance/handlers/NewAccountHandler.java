package de.sb.plugin.finance.handlers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.views.dialogs.NewAccountDialog;

public class NewAccountHandler extends AbstractHandler {
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		NewAccountDialog dialog = new NewAccountDialog(new Shell());
		int result = dialog.open();

		if (result == Window.OK) {
			Account account = dialog.getAccount();
			// DatabaseOperations.insertAccount(account);

			EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
			EntityManager em = factory.createEntityManager();

			em.getTransaction().begin();

			em.persist(account);
			em.getTransaction().commit();

			em.close();

		}

		return null;
	}
}
