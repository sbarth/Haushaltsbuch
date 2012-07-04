package de.sb.plugin.finance.listener;

import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class CurrencyVerifyListener implements VerifyListener {
	private String getText(VerifyEvent e) {
		String text = "";
		if (e.widget instanceof Text) {
			Text widget = (Text) e.widget;
			text = widget.getText().substring(0, e.start) + e.text + widget.getText().substring(e.end);
		}

		return text;
	}

	@Override
	public void verifyText(VerifyEvent e) {
		String text = getText(e);
		if (e.keyCode != SWT.DEL && e.keyCode != SWT.BS) {
			e.doit = Pattern.matches("\\d{1,5}\\,{0,1}\\d{0,2}", text);
		} else {
			e.doit = !text.startsWith(",");
		}
	}
}
