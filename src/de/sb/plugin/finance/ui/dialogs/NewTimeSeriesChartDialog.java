package de.sb.plugin.finance.ui.dialogs;

import java.util.Arrays;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

import de.sb.plugin.finance.io.DatabaseOperations;
import de.sb.plugin.finance.ui.common.LayoutFactory;
import de.sb.plugin.finance.ui.common.SwtWidgetFactory;
import de.sb.plugin.finance.ui.provider.AccountLabelProvider;
import de.sb.plugin.finance.ui.provider.CategoryLabelProvider;
import de.sb.plugin.finance.util.R;

public class NewTimeSeriesChartDialog extends AbstractDialog {
	private Button butSplitCategories;
	private ComboViewer cvAccounts;
	private ComboViewer cvCategories;
	private ComboViewer cvTimespan;
	private ComboViewer cvTransactionType;
	private DateTime dtTimespanFrom;
	private DateTime dtTimespanTo;

	public NewTimeSeriesChartDialog(final Shell parentShell) {
		super(parentShell, R.TITLE_DIALOG_NEW_TIMESERIES_CHART);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(4, false));
		comp.setLayoutData(LayoutFactory.createGridData(true, true, GridData.FILL, GridData.FILL));

		GridData gdLabels = LayoutFactory.createGridData(false, false, GridData.GRAB_HORIZONTAL, GridData.GRAB_VERTICAL, 1);
		GridData gdRight = LayoutFactory.createGridData(true, false, GridData.FILL, GridData.GRAB_VERTICAL, 3);
		GridData gdTimespan = LayoutFactory.createGridData(true, false, SWT.FILL, GridData.GRAB_VERTICAL);

		SwtWidgetFactory.createLabel(comp, "Konto: ", gdLabels);
		cvAccounts = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllAccounts(true), gdRight, new AccountLabelProvider());

		SwtWidgetFactory.createLabel(comp, "Kategorie: ", gdLabels);
		cvCategories = SwtWidgetFactory.createComboViewer(comp, DatabaseOperations.getInstance().getAllCategories(true), gdRight, new CategoryLabelProvider());

		SwtWidgetFactory.createLabel(comp, null, gdLabels);
		butSplitCategories = SwtWidgetFactory.createCheckBox(comp, "Aufsplitten ", gdRight);

		SwtWidgetFactory.createLabel(comp, "Buchungsart: ", gdLabels);
		cvTransactionType = SwtWidgetFactory.createComboViewer(comp, Arrays.asList(R.COMBO_TIMESERIES_CHART_TRANSACTION_TYPE), gdRight, null);

		SwtWidgetFactory.createLabel(comp, "Zeitraum: ", gdLabels);
		cvTimespan = SwtWidgetFactory.createComboViewer(comp, Arrays.asList(R.COMBO_TIMESERIES_CHART_TIMESPAN), gdRight, null);

		SwtWidgetFactory.createLabel(comp, null, null);
		dtTimespanFrom = SwtWidgetFactory.createDateTime(comp, gdTimespan);
		SwtWidgetFactory.createLabel(comp, "bis", null);
		dtTimespanTo = SwtWidgetFactory.createDateTime(comp, gdTimespan);

		return comp;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(320, 320);
	}

	@Override
	protected void setBinding() {
		DataBindingContext ctx = new DataBindingContext();

	}
}
