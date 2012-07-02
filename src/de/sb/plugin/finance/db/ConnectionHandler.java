package de.sb.plugin.finance.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ConnectionHandler {
	private static final String PERSISTENCE_UNIT = "finance_pu";
	private EntityManager manager;
	private EntityManagerFactory factory;
	private EntityTransaction transaction;

	public void closeEntityManager() {
		manager.close();
		factory.close();
	}

	public void closeEntityTransaction() {
		transaction.commit();
		closeEntityManager();
	}

	public EntityManager openEntityManager() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		manager = factory.createEntityManager();
		return manager;
	}

	public EntityManager openEntityTransaction() {
		openEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();

		return manager;
	}
}
