package de.sb.plugin.finance.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Category;

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
	public List<Account> getAllAccounts() {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery("findAllAccounts");

		List<Account> accounts = query.getResultList();
		handler.closeEntityManager();

		return accounts;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery("findAllCategories");

		List<Category> categories = query.getResultList();
		handler.closeEntityManager();

		return categories;
	}

	public void insert(final Object o) {
		EntityManager manager = handler.openEntityTransaction();
		manager.merge(o);
		handler.closeEntityTransaction();
	}
}
