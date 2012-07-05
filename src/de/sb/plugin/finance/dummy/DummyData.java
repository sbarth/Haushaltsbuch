package de.sb.plugin.finance.dummy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.sb.plugin.finance.db.DatabaseOperations;
import de.sb.plugin.finance.entities.Account;
import de.sb.plugin.finance.entities.Category;
import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionType;

public class DummyData {
	private static List<Account> accounts;
	private static List<Category> categories;

	private static String[] description = {
			"Das ", "geht ", "niemandem ", "etwas ", "an ", "oder ", "irre ", "ich ", "mich ", "da ", "so ", "sehr ",
	};

	private static BigDecimal createAmount() {
		int prefix = Double.valueOf(Math.random() * 1000).intValue();
		int suffix = Double.valueOf(Math.random() * 100).intValue();

		return new BigDecimal(prefix + "." + suffix);
	}

	private static Calendar createDate() {
		Calendar cal = new GregorianCalendar();
		int hourOfDay = Double.valueOf(Math.random() * 24).intValue();
		int minute = Double.valueOf(Math.random() * 60).intValue();
		int second = Double.valueOf(Math.random() * 60).intValue();
		int year = 2008 + Double.valueOf(Math.random() * 5).intValue();

		cal.set(year, 0, 0, hourOfDay, minute, second);
		cal.set(Calendar.DAY_OF_YEAR, Double.valueOf((cal.getActualMaximum(Calendar.DAY_OF_YEAR)) * Math.random()).intValue() + 1);

		return cal;
	}

	private static Transaction createTransaction() {
		Transaction transaction = new Transaction();
		transaction.setAccount(getRandomAccount());
		transaction.setAmount(createAmount());
		transaction.setCategory(categories.get(Double.valueOf(Math.random() * categories.size()).intValue()));

		Calendar cal = createDate();
		transaction.setDate(cal);

		int index = Double.valueOf(Math.random() * TransactionType.values().length).intValue();

		transaction.setDescription(getRandomDescription());
		transaction.setType(TransactionType.values()[index].getName());

		return transaction;
	}

	private static Account getRandomAccount() {
		int index = Double.valueOf(Math.random() * accounts.size()).intValue();

		return accounts.get(index);
	}

	private static String getRandomDescription() {
		int length = Double.valueOf(Math.random() * description.length).intValue();
		String desc = "";

		for (int i = 0; i < length; i++) {
			int index = Double.valueOf(Math.random() * description.length).intValue();
			desc += description[index];
		}

		return desc;
	}

	public static void insertTransactions(int count) {
		DatabaseOperations ops = DatabaseOperations.getInstance();

		accounts = ops.getAllAccounts(false);
		categories = ops.getAllCategories();

		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < count; i++) {
			transactions.add(createTransaction());
		}

		ops.insert(transactions);
	}
}
