package de.sb.plugin.finance.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class R {
	/**
	 * Integer-Arrays
	 */
	public static final int[] TABLE_TRANSACTION_COLUMN_WIDTHS = {
			150, 150, 150, 100, 350
	};

	/**
	 * Strings
	 */
	public static final String COLUMN_NAME_AMOUNT = "Betrag";
	public static final String COLUMN_NAME_CATEGORY = "Kategorie";
	public static final String COLUMN_NAME_DATE = "Datum";
	public static final String COLUMN_NAME_DESCRIPTION = "Beschreibung";
	public static final String COLUMN_NAME_TRANSACTIONTYPE = "Buchungsart";

	public static final String COMBO_TRANSACTION_ACCOUNT_ALL = "Alle Konten";
	public static final String COMBO_TRANSACTION_ACCOUNT_COMMON = "Gemeinsam";
	public static final String COMBO_TRANSACTION_ACCOUNT_GIRO = "Girokonto";
	public static final String COMBO_TRANSACTION_ACCOUNT_INSTANT = "Tagesgeldkonto";

	public static final String COMBO_TRANSACTION_GROUPBY_ACCOUNT = "Konto";
	public static final String COMBO_TRANSACTION_GROUPBY_BRANCH = "Sparte";
	public static final String COMBO_TRANSACTION_GROUPBY_CATEGORY = "Kategorie";
	public static final String COMBO_TRANSACTION_GROUPBY_DATE = "Datum";
	public static final String COMBO_TRANSACTION_GROUPBY_MONTH = "Monat";
	public static final String COMBO_TRANSACTION_GROUPBY_NOTHING = "Keine Gruppierung";
	public static final String COMBO_TRANSACTION_GROUPBY_TRANSACTION_TYPE = "Buchungsart";
	public static final String COMBO_TRANSACTION_GROUPBY_WEEK = "Woche";

	public static final String COMBO_TRANSACTION_TIMESPAN_CURRENTDAY = "Heute";
	public static final String COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH = "Aktueller Monat";
	public static final String COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER = "Aktuelles Quartal";
	public static final String COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR = "Aktuelles Jahr";
	public static final String COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH = "Letzter Monat";
	public static final String COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER = "Letztes Quartal";
	public static final String COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR = "Letztes Jahr";
	public static final String COMBO_TRANSACTION_TIMESPAN_SELECT = "Freier Zeitraum";

	public static final String COMBO_TRANSACTION_TYPE_ALL = "Alle Buchungen";
	public static final String COMBO_TRANSACTION_TYPE_INCOME = "Einnahme";
	public static final String COMBO_TRANSACTION_TYPE_OUTCOME = "Ausgabe";
	public static final String COMBO_TRANSACTION_TYPE_TRANSFER = "Umbuchung";

	public static final String LABEL_PERSPECTIVE_FINANCE_TITLE = "Buchhaltung";
	public static final String LABEL_FILTER_COMPOSITE_ACCOUNT = "Konto:";
	public static final String LABEL_FILTER_COMPOSITE_GROUPBY = "Gruppierung:";
	public static final String LABEL_FILTER_COMPOSITE_SEARCH = "Suche:";
	public static final String LABEL_FILTER_COMPOSITE_TIMESPAN = "Zeitraum:";
	public static final String LABEL_FILTER_COMPOSITE_TRANSACTIONTYPE = "Buchungsart:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_CURRENTMONTH = "Aktueller Monat (Summe)";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE = "Differenz: ";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_FIXINCOME = "Fixe Einnahmen:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_FIXOUTCOME = "Fixe Ausgaben:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_VARINCOME = "Einmailige Einnahmen:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_VAROUTCOME = "Einmailige Ausgaben:";

	public static final String TABLE_TRANSACTION_DATE_FORMAT = "dd.MM.yyyy";

	/**
	 * String-Arrays
	 */
	public static final String[] TABLE_TRANSACTION_COLUMNS = {
			COLUMN_NAME_DATE, COLUMN_NAME_TRANSACTIONTYPE, COLUMN_NAME_CATEGORY, COLUMN_NAME_AMOUNT, COLUMN_NAME_DESCRIPTION
	};
	public static final String[] COMBO_TRANSACTION_ACCOUNT = {
			COMBO_TRANSACTION_ACCOUNT_ALL, COMBO_TRANSACTION_ACCOUNT_COMMON, COMBO_TRANSACTION_ACCOUNT_GIRO, COMBO_TRANSACTION_ACCOUNT_INSTANT,
			"Auflistung aller verfügbaren Konten" // TODO Statt Kontotypen, lieber alle gespeicherten Konten wählen
	};
	public static final String[] COMBO_TRANSACTION_GROUPBY = {
			COMBO_TRANSACTION_GROUPBY_NOTHING, COMBO_TRANSACTION_GROUPBY_DATE, COMBO_TRANSACTION_GROUPBY_WEEK, COMBO_TRANSACTION_GROUPBY_MONTH,
			COMBO_TRANSACTION_GROUPBY_BRANCH, COMBO_TRANSACTION_GROUPBY_CATEGORY, COMBO_TRANSACTION_GROUPBY_ACCOUNT,
			COMBO_TRANSACTION_GROUPBY_TRANSACTION_TYPE
	};
	public static final String[] COMBO_TRANSACTION_TYPE = {
			COMBO_TRANSACTION_TYPE_ALL, COMBO_TRANSACTION_TYPE_INCOME, COMBO_TRANSACTION_TYPE_OUTCOME, COMBO_TRANSACTION_TYPE_TRANSFER
	};
	public static final String[] COMBO_TRANSACTION_TIMESPAN = {
			COMBO_TRANSACTION_TIMESPAN_CURRENTDAY, COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH, COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER,
			COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR, COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH, COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER,
			COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR, COMBO_TRANSACTION_TIMESPAN_SELECT
	};
	/**
	 * Perspectives
	 */
	public static final String PERSPECTIVE_ID_FINANCE = "de.sb.plugin.finance.perspective";

	/**
	 * Views
	 */
	public static final String VIEW_ID_STATISTICS = "de.sb.plugin.finance.views.Statistics";
	public static final String VIEW_ID_TRANSACTIONLIST = "de.sb.plugin.finance.views.TransactionList";

	/**
	 * Formate
	 */
	public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.GERMANY);
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(TABLE_TRANSACTION_DATE_FORMAT);

}
