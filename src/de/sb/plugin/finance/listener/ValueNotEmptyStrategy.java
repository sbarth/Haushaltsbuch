package de.sb.plugin.finance.listener;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Button;

public class ValueNotEmptyStrategy extends UpdateValueStrategy {
	public ValueNotEmptyStrategy(final Button bOk) {
		setAfterGetValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				IStatus status;

				if (value.toString().isEmpty()) {
					status = ValidationStatus.error("Feld darf nicht leer sein!");
					// bOk.setEnabled(false);
				} else {
					status = ValidationStatus.ok();
					// bOk.setEnabled(true);
				}

				return status;
			}
		});
	}
}
