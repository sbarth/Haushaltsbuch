package de.sb.plugin.finance.util;

public class Queries {
	/**
	 * Names
	 */
	public static final String FIND_ALL_ACCOUNTS = "findAllAccounts";
	public static final String FIND_ALL_CATEGORIES = "findAllCategories";
	public static final String FIND_ALL_TRANSACTIONS = "findAllTransactions";

	/**
	 * Queries
	 */
	public static final String FIND_ALL_ACCOUNTS_QUERY = "select acc from Account acc";
	public static final String FIND_ALL_CATEGORY_QUERY = "select cat from Category cat";
	public static final String FIND_ALL_TRANSACTIONS_QUERY = "select tx from Transaction tx";
}
