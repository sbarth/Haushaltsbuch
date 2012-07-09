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

import de.sb.plugin.finance.entities.Category;
import de.sb.plugin.finance.listener.CurrencyVerifyListener;
import de.sb.plugin.finance.ui.provider.CategoryLabelProvider;

public class SwtWidgetFactory {
	public static Button createButton(Composite parent, String text, GridData layoutData) {
		Button but = new Button(parent, SWT.PUSH);
		but.setLayoutData(layoutData);
		but.setText(text);

		return but;
	}

	public static Button createButtonImage(Composite parent, Image systemImage, GridData layoutData) {
		Button but = createButton(parent, "", layoutData);
		but.setImage(systemImage);

		return but;
	}

	public static ComboViewer createComboViewer(Composite parent, List<?> list, GridData layoutData, LabelProvider provider) {
		ComboViewer cv = new ComboViewer(parent, SWT.READ_ONLY);
		cv.getCombo().setLayoutData(layoutData);
		cv.setContentProvider(ArrayContentProvider.getInstance());
		cv.setInput(list);

		if (provider != null) {
			cv.setLabelProvider(provider);
		}

		return cv;
	}

	public static ComboViewer createComboViewerWritable(Composite parent, List<Category> list, GridData layoutData, CategoryLabelProvider provider) {
		ComboViewer cv = new ComboViewer(parent, SWT.BORDER);
		cv.getCombo().setLayoutData(layoutData);
		cv.setContentProvider(ArrayContentProvider.getInstance());
		cv.setInput(list);

		if (provider != null) {
			cv.setLabelProvider(provider);
		}

		return cv;
	}

	public static Text createCurrencyText(Composite parent, GridData layoutData) {
		Text txt = createText(parent, layoutData);
		txt.addVerifyListener(new CurrencyVerifyListener());

		return txt;
	}

	public static DateTime createDateTime(Composite parent, GridData layoutData) {
		DateTime dateTime = new DateTime(parent, SWT.NONE);
		dateTime.setLayoutData(layoutData);

		return dateTime;
	}

	public static Label createLabel(Composite parent, String text, GridData layoutData) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setLayoutData(layoutData);

		if (text != null) {
			lbl.setText(text);
		}

		return lbl;
	}

	public static Text createMultiText(Composite parent, GridData layoutData) {
		Text txt = new Text(parent, SWT.BORDER | SWT.MULTI);
		txt.setLayoutData(layoutData);

		return txt;
	}

	public static Text createText(Composite parent, GridData layoutData) {
		Text txt = new Text(parent, SWT.BORDER);
		txt.setLayoutData(layoutData);

		return txt;
	}
}
