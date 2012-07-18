package de.sb.plugin.finance.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

public class R {
	/**
	 * Farben
	 */

	public static final Color COLOR_BG_TABLE_ITEM_INCOME = new Color(null, 127, 255, 212); // aquamarine
	public static final Color COLOR_BG_TABLE_ITEM_OUTCOME = new Color(null, 255, 193, 193); // RosyBrown1
	public static final Color COLOR_BG_TABLE_ITEM_TRANSFER = new Color(null, 187, 255, 255); // PaleTurquoise1

	public static final Color COLOR_FONT_TABLE_ITEM_INCOME = new Color(null, 69, 139, 116); // aquamarine4
	public static final Color COLOR_FONT_TABLE_ITEM_OUTCOME = new Color(null, 205, 0, 0); // red3
	public static final Color COLOR_FONT_TABLE_ITEM_TRANSFER = new Color(null, 0, 0, 128); // navy

	public static final Color COLOR_FONT_SUMMARIZATION_DIFF = new Color(null, 0, 0, 0); // black
	public static final Color COLOR_FONT_SUMMARIZATION_INCOME = new Color(null, 69, 139, 116); // aquamarine4
	public static final Color COLOR_FONT_SUMMARIZATION_OUTCOME = new Color(null, 205, 0, 0); // red3
	/**
	 * Integer-Arrays
	 */
	public static final int[] TABLE_TRANSACTION_COLUMN_ALIGNMENT = {
			SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.RIGHT, SWT.LEFT
	};
	public static final int[] TABLE_TRANSACTION_COLUMN_WIDTHS = {
			150, 150, 150, 100, 350
	};

	/**
	 * Fonts
	 */

	public static final Font FONT_ARIAL_9_BOLD = new Font(null, new FontData("Arial", 9, SWT.BOLD));

	/**
	 * Labels
	 */
	public static final String COLUMN_NAME_AMOUNT = "Betrag";
	public static final String COLUMN_NAME_CATEGORY = "Kategorie";
	public static final String COLUMN_NAME_DATE = "Datum";
	public static final String COLUMN_NAME_DESCRIPTION = "Beschreibung";
	public static final String COLUMN_NAME_TRANSACTIONTYPE = "Buchungsart";

	public static final String COMBO_TIMESERIES_CHART_CATEGORIES_ALL = "Alle Kategorien";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_ALL = "Alle";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_FIX = "Fixe Buchungen";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_FIX_INCOME = "Fixe Einnahmen ";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_FIX_OUTCOME = "Fixe Ausgaben";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_INCOME = "Einnahme";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_OUTCOME = "Ausgabe";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_VAR = "Variable Buchungen";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_VAR_INCOME = "Variable Einnahmen";
	public static final String COMBO_TIMESERIES_CHART_TRANSACTION_VAR_OUTCOME = "Variable Ausgaben";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_CURRENT_MONTH = "Aktueller Monat";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_CURRENT_YEAR = "Aktuelles Jahr";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_FREE = "Selbst wählen";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_LAST_HALFYEAR = "Letzten 6 Monate";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_LAST_SEVEN_DAYS = "Letzten 7 Tage";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_LAST_QUARTER = "Letzten 3 Monate";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_LAST_THIRTY_DAYS = "Letzten 30 Tage";
	public static final String COMBO_TIMESERIES_CHART_TIMESPAN_LAST_YEAR = "Letzten 12 Monate";
	public static final String COMBO_TRANSACTION_ACCOUNT_ALL = "Alle Konten";
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

	public static final String LABEL_DIALOG_NEW_ACCOUNT_DESCRIPTION = "Beschreibung:";
	public static final String LABEL_DIALOG_NEW_ACCOUNT_LOGO = "Logo:";
	public static final String LABEL_DIALOG_NEW_ACCOUNT_NAME = "Name:";
	public static final String LABEL_DIALOG_NEW_ACCOUNT_STARTAMOUNT = "Startbetrag:";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_ACCOUNT = "Konto: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_AMOUNT = "Betrag: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_CATEGORY = "Kategorie: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_DATE = "Datum: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_DESCRIPTION = "Beschreibung: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_TO = "auf: ";
	public static final String LABEL_DIALOG_NEW_TRANSACTION_TRANSACTION_TYPE = "Buchungstyp: ";
	public static final String LABEL_FILTER_COMPOSITE_ACCOUNT = "Konto:";
	public static final String LABEL_FILTER_COMPOSITE_GROUPBY = "Gruppierung:";
	public static final String LABEL_FILTER_COMPOSITE_SEARCH = "Suche:";
	public static final String LABEL_FILTER_COMPOSITE_TIMESPAN = "Zeitraum:";
	public static final String LABEL_FILTER_COMPOSITE_TRANSACTIONTYPE = "Buchungsart:";
	public static final String LABEL_PERSPECTIVE_FINANCE_TITLE = "Buchhaltung";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_COM = "Zusammenfassung";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_DIFFERENCE = "Differenz: ";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_FIXINCOME = "Fixe Einnahmen:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_FIXOUTCOME = "Fixe Ausgaben:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_VARINCOME = "Einmalige Einnahmen:";
	public static final String LABEL_SUMMARIZATION_COMPOSITE_VAROUTCOME = "Einmalige Ausgaben:";

	public static final String TITLE_DIALOG_NEW_ACCOUNT = "Erstelle neues Konto";
	public static final String TITLE_DIALOG_NEW_TIMESERIES_CHART = "Erstelle neuen Verlauf";
	public static final String TITLE_DIALOG_NEW_TRANSACTION = "Erstelle neue Buchung";

	public static final String TRANSACTION_TYPE_FIX_INCOME = "Einnahme (Fix)";
	public static final String TRANSACTION_TYPE_FIX_OUTCOME = "Ausgabe (Fix)";
	public static final String TRANSACTION_TYPE_INCOME = "Einnahme (Variabel)";
	public static final String TRANSACTION_TYPE_OUTCOME = "Ausgabe (Variabel)";
	public static final String TRANSACTION_TYPE_TRANSFER = "Umbuchung";

	/**
	 * String-Arrays
	 */
	public static final String[] COMBO_TIMESERIES_CHART_TIMESPAN = {
			COMBO_TIMESERIES_CHART_TIMESPAN_CURRENT_MONTH, COMBO_TIMESERIES_CHART_TIMESPAN_CURRENT_YEAR, COMBO_TIMESERIES_CHART_TIMESPAN_FREE,
			COMBO_TIMESERIES_CHART_TIMESPAN_LAST_SEVEN_DAYS, COMBO_TIMESERIES_CHART_TIMESPAN_LAST_THIRTY_DAYS,
			COMBO_TIMESERIES_CHART_TIMESPAN_LAST_QUARTER, COMBO_TIMESERIES_CHART_TIMESPAN_LAST_HALFYEAR, COMBO_TIMESERIES_CHART_TIMESPAN_LAST_YEAR
	};
	public static final String[] COMBO_TIMESERIES_CHART_TRANSACTION_TYPE = {
			COMBO_TIMESERIES_CHART_TRANSACTION_ALL, COMBO_TIMESERIES_CHART_TRANSACTION_FIX, COMBO_TIMESERIES_CHART_TRANSACTION_FIX_INCOME,
			COMBO_TIMESERIES_CHART_TRANSACTION_FIX_OUTCOME,
			COMBO_TIMESERIES_CHART_TRANSACTION_INCOME, COMBO_TIMESERIES_CHART_TRANSACTION_OUTCOME, COMBO_TIMESERIES_CHART_TRANSACTION_VAR,
			COMBO_TIMESERIES_CHART_TRANSACTION_VAR_INCOME,
			COMBO_TIMESERIES_CHART_TRANSACTION_VAR_OUTCOME
	};
	public static final String[] TABLE_TRANSACTION_COLUMNS = {
			COLUMN_NAME_DATE, COLUMN_NAME_TRANSACTIONTYPE, COLUMN_NAME_CATEGORY, COLUMN_NAME_AMOUNT, COLUMN_NAME_DESCRIPTION
	};
	public static final String[] COMBO_TRANSACTION_GROUPBY = {
			COMBO_TRANSACTION_GROUPBY_NOTHING, COMBO_TRANSACTION_GROUPBY_DATE, COMBO_TRANSACTION_GROUPBY_WEEK
	}; // TODO Weitere Gruppierungen implementieren , COMBO_TRANSACTION_GROUPBY_MONTH, COMBO_TRANSACTION_GROUPBY_BRANCH,
		// COMBO_TRANSACTION_GROUPBY_CATEGORY,
		// COMBO_TRANSACTION_GROUPBY_ACCOUNT, COMBO_TRANSACTION_GROUPBY_TRANSACTION_TYPE
	public static final String[] COMBO_TRANSACTION_TYPE = {
			COMBO_TRANSACTION_TYPE_ALL, COMBO_TRANSACTION_TYPE_INCOME, COMBO_TRANSACTION_TYPE_OUTCOME, COMBO_TRANSACTION_TYPE_TRANSFER
	};
	public static final String[] COMBO_TRANSACTION_TIMESPAN = {
			COMBO_TRANSACTION_TIMESPAN_CURRENTDAY, COMBO_TRANSACTION_TIMESPAN_CURRENTMONTH, COMBO_TRANSACTION_TIMESPAN_CURRENTQUARTER,
			COMBO_TRANSACTION_TIMESPAN_CURRENTYEAR, COMBO_TRANSACTION_TIMESPAN_PREVIOUSMONTH, COMBO_TRANSACTION_TIMESPAN_PREVIOUSQUARTER,
			COMBO_TRANSACTION_TIMESPAN_PREVIOUSYEAR, COMBO_TRANSACTION_TIMESPAN_SELECT
	};

	public static final String[] MONTH_NAMES_SHORT = {
			"Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez"
	};

	/**
	 * Perspective IDs
	 */
	public static final String PERSPECTIVE_ID_FINANCE = "de.sb.plugin.finance.perspective";

	/**
	 * View IDs
	 */
	public static final String VIEW_ID_STATISTICS = "de.sb.plugin.finance.views.Statistics";
	public static final String VIEW_ID_TRANSACTIONLIST = "de.sb.plugin.finance.views.TransactionList";

	/**
	 * Formate
	 */
	public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.GERMANY);
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
}
