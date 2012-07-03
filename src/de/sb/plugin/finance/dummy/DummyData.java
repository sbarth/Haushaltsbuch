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
	private static List<Category> categories;

	private static String[] description = {
			"Das ", "geht ", "niemandem ", "etwas ", "an ", "oder ", "irre ", "ich ", "mich ", "da ", "so ", "sehr ",
	};

	static {
		categories = new ArrayList<Category>();
		createCategories();
	}

	public static Account createAccount(final String name, final int transactionSize) {
		Account account = new Account();
		account.setDescription("Dresden");
		account.setLogo("");
		account.setStartAmount(new BigDecimal("10000.00"));
		account.setName(name);

		for (int index = 0; index < transactionSize; index++) {
			account.addTransaction(createTransaction(account));
		}

		return account;
	}

	private static BigDecimal createAmount() {
		int prefix = Double.valueOf(Math.random() * 1000).intValue();
		int suffix = Double.valueOf(Math.random() * 100).intValue();

		return new BigDecimal(prefix + "." + suffix);
	}

	private static void createCategories() {
		DatabaseOperations ops = DatabaseOperations.getInstance();
		categories = ops.getAllCategories();

		if (categories != null && categories.size() > 0) {
			return;
		}

		Category allgemein = new Category(null, "Allgemein");
		Category auszahlung = new Category(allgemein, "Auszahlung");
		Category einzahlung = new Category(allgemein, "Einzahlung");

		Category alltag = new Category(null, "Alltag");
		Category accessoires = new Category(alltag, "Accessoires");
		Category kleidung = new Category(alltag, "Kleidung");
		Category schuhe = new Category(alltag, "Schuhe");

		Category auto = new Category(null, "Auto");
		Category benzin = new Category(auto, "Benzin");
		Category bussgeld = new Category(auto, "Buﬂgeld");
		Category steuer = new Category(auto, "Steuer");

		categories.add(allgemein);
		categories.add(auszahlung);
		categories.add(einzahlung);
		categories.add(alltag);
		categories.add(accessoires);
		categories.add(kleidung);
		categories.add(schuhe);
		categories.add(auto);
		categories.add(benzin);
		categories.add(bussgeld);
		categories.add(steuer);

		ops.insert(auszahlung);
		ops.insert(einzahlung);
		ops.insert(accessoires);
		ops.insert(kleidung);
		ops.insert(schuhe);
		ops.insert(benzin);
		ops.insert(bussgeld);
		ops.insert(steuer);

		categories = ops.getAllCategories();
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

	private static Transaction createTransaction(final Account account) {
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(createAmount());
		transaction.setCategory(categories.get(Double.valueOf(Math.random() * categories.size()).intValue()));

		Calendar cal = createDate();
		transaction.setDate(cal);

		int index = Double.valueOf(Math.random() * TransactionType.values().length).intValue();

		transaction.setDescription(getRandomDescription());
		transaction.setType(TransactionType.values()[index].getName());

		return transaction;
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
}
