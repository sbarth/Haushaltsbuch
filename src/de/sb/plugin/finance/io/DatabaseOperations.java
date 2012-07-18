package de.sb.plugin.finance.io;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Category;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionCompareByDate;
import de.sb.plugin.finance.util.Queries;
import de.sb.plugin.finance.util.R;

public class DatabaseOperations {
	private volatile static DatabaseOperations dbOps;
	private static final Logger LOGGER = Logger.getLogger(DatabaseOperations.class);

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
	public List<Account> getAllAccounts(final boolean withAllEntry) {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_ALL_ACCOUNTS);

		List<Account> accounts = query.getResultList();
		handler.closeEntityManager();

		if (withAllEntry) {
			Account accAll = new Account();
			accAll.setName(R.COMBO_TRANSACTION_ACCOUNT_ALL);
			accounts.add(0, accAll);
		}

		return accounts;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories(final boolean withAllEntry) {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_ALL_CATEGORIES);

		List<Category> categories = query.getResultList();
		handler.closeEntityManager();

		if (withAllEntry) {
			Category catAll = new Category();
			catAll.setName(R.COMBO_TIMESERIES_CHART_CATEGORIES_ALL);
			categories.add(0, catAll);
		}

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

	@SuppressWarnings("unchecked")
	public List<Transaction> getAllTransactionsByYear(final int year) {
		EntityManager manager = handler.openEntityManager();
		Query query = manager.createNamedQuery(Queries.FIND_TRANSACTIONS_BY_YEAR);

		Calendar dateFrom = new GregorianCalendar(year - 1, 11, 31);
		Calendar dateTo = new GregorianCalendar(year + 1, 0, 1);

		query.setParameter(1, dateFrom);
		query.setParameter(2, dateTo);
		List<Transaction> transactions = query.getResultList();
		handler.closeEntityManager();

		Collections.sort(transactions, new TransactionCompareByDate());

		return transactions;
	}

	public void insert(final List<?> list) {
		EntityManager manager = handler.openEntityTransaction();

		for (Object o : list) {
			LOGGER.info("Speichere '" + o + "' in DB");
			manager.merge(o);
		}

		handler.closeEntityTransaction();
	}

	public void insert(final Object o) {
		EntityManager manager = handler.openEntityTransaction();

		LOGGER.info("Speichere '" + o + "' in DB");
		manager.merge(o);

		handler.closeEntityTransaction();
	}
}
