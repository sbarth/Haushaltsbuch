package de.sb.plugin.finance.listener;

import java.util.regex.Pattern;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Button;

public class ValueMatchCurrencyStrategy extends UpdateValueStrategy implements IUpdateValueStrategy {
	private IValidator validator;

	public ValueMatchCurrencyStrategy(final Button bOk) {
		validator = new IValidator() {
			@Override
			public IStatus validate(Object value) {
				IStatus status;

				if (!Pattern.matches("\\d{1,5}\\,\\d{2}", value.toString())) {
					status = ValidationStatus.error("Zahl entspricht nicht dem Muster 0,00");
					// bOk.setEnabled(false);
				} else {
					status = ValidationStatus.ok();
					// bOk.setEnabled(true);
				}

				return status;
			}
		};
		setAfterGetValidator(validator);
	}

	@Override
	public IValidator getValidator() {
		return validator;
	}
}
