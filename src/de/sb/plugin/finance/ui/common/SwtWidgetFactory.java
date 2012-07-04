package de.sb.plugin.finance.ui.common;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SwtWidgetFactory {
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

	public static Label createLabel(Composite parent, String text, GridData layoutData) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setLayoutData(layoutData);
		lbl.setText(text);

		return lbl;
	}

	public static Text createText(Composite parent, GridData layoutData) {
		Text txt = new Text(parent, SWT.BORDER);
		txt.setLayoutData(layoutData);

		return txt;
	}
}
