package de.sb.plugin.finance.listener;

import org.eclipse.core.databinding.validation.IValidator;

public interface IUpdateValueStrategy {
	IValidator getValidator();
}
