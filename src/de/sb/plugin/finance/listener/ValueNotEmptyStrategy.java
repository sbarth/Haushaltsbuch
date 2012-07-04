package de.sb.plugin.finance.listener;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ValueNotEmptyStrategy extends UpdateValueStrategy {
	public ValueNotEmptyStrategy() {
		setAfterGetValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				IStatus status;

				if (value.toString().isEmpty()) {
					status = ValidationStatus.error("Feld darf nicht leer sein!");
				} else {
					status = ValidationStatus.ok();
				}

				return status;
			}
		});
	}
}
