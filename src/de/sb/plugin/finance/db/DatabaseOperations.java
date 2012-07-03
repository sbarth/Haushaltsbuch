package de.sb.plugin.finance.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Category;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.util.Queries;
import de.sb.plugin.finance.util.R;

public class DatabaseOperations {
	private volatile static DatabaseOperations dbOps;

	public static DatabaseOperations getInstance() {
		if (dbOps == null) {
			synchronized (DatabaseOperations.class) {
				if (dbOps == null) {
					dbOps = new DatabaseOperations();
				}
			}
		}

		return dbOps;
	}

	private final ConnectionHandler handler;

	private DatabaseOperations() {
		handler = new ConnectionHandler();
	}

	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts(boolean withEmptyEntry) {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_ALL_ACCOUNTS);

		List<Account> accounts = query.getResultList();
		handler.closeEntityManager();

		if (withEmptyEntry) {
			Account emptyAcc = new Account();
			emptyAcc.setName(R.COMBO_TRANSACTION_ACCOUNT_ALL);
			accounts.add(0, emptyAcc);
		}

		return accounts;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_ALL_CATEGORIES);

		List<Category> categories = query.getResultList();
		handler.closeEntityManager();

		return categories;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getAllTransactions() {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_ALL_TRANSACTIONS);

		List<Transaction> transaction = query.getResultList();
		handler.closeEntityManager();

		return transaction;
	}

	public void insert(final Object o) {
		EntityManager manager = handler.openEntityTransaction();
		manager.merge(o);
		handler.closeEntityTransaction();
	}
}
