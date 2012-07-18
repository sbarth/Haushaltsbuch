package de.sb.plugin.finance.ui.common;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.sb.plugin.finance.listener.CurrencyVerifyListener;

public class SwtWidgetFactory {
	public static Button createButton(final Composite parent, final String text, final GridData layoutData) {
		Button but = new Button(parent, SWT.PUSH);
		but.setLayoutData(layoutData);
		but.setText(text);

		return but;
	}

	public static Button createButtonImage(final Composite parent, final Image systemImage, final GridData layoutData) {
		Button but = createButton(parent, "", layoutData);
		but.setImage(systemImage);

		return but;
	}

	public static Button createCheckBox(final Composite parent, final String text, final GridData layoutData) {
		Button but = new Button(parent, SWT.CHECK);
		but.setLayoutData(layoutData);
		but.setText(text);

		return but;
	}

	public static ComboViewer createComboViewer(final Composite parent, final List<?> list, final GridData layoutData, final LabelProvider provider) {
		ComboViewer cv = new ComboViewer(parent, SWT.READ_ONLY);
		cv.getCombo().setLayoutData(layoutData);
		cv.setContentProvider(ArrayContentProvider.getInstance());
		cv.setInput(list);

		if (provider != null) {
			cv.setLabelProvider(provider);
		}

		return cv;
	}

	public static Text createCurrencyText(final Composite parent, final GridData layoutData) {
		Text txt = createText(parent, layoutData);
		txt.addVerifyListener(new CurrencyVerifyListener());

		return txt;
	}

	public static DateTime createDateTime(final Composite parent, final GridData layoutData) {
		DateTime dateTime = new DateTime(parent, SWT.NONE);
		dateTime.setLayoutData(layoutData);

		return dateTime;
	}

	public static Label createLabel(final Composite parent, final String text, final GridData layoutData) {
		Label lbl = new Label(parent, SWT.NONE);
		if (layoutData != null) {
			lbl.setLayoutData(layoutData);
		}

		if (text != null) {
			lbl.setText(text);
		}

		return lbl;
	}

	public static Label createLabelCenter(final Composite parent, final String text, final GridData layoutData) {
		Label lbl = createLabel(parent, text, layoutData);
		lbl.setAlignment(SWT.CENTER);

		return lbl;
	}

	public static Label createLabelRight(final Composite parent, final String text, final GridData layoutData) {
		Label lbl = createLabel(parent, text, layoutData);
		lbl.setAlignment(SWT.RIGHT);

		return lbl;
	}

	public static Label createLabelSeparatorHorizontal(final Composite parent, final GridData layoutData) {
		Label lbl = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);

		if (layoutData != null) {
			lbl.setLayoutData(layoutData);
		}

		return lbl;
	}

	public static Text createMultiText(final Composite parent, final GridData layoutData) {
		Text txt = new Text(parent, SWT.BORDER | SWT.MULTI);
		txt.setLayoutData(layoutData);

		return txt;
	}

	public static Text createText(final Composite parent, final GridData layoutData) {
		Text txt = new Text(parent, SWT.BORDER);
		txt.setLayoutData(layoutData);

		return txt;
	}
}
