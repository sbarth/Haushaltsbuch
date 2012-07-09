package de.sb.plugin.finance.ui.strategy;

import org.eclipse.core.databinding.conversion.Converter;

import de.sb.plugin.finance.entities.TransactionType;

public class TransactionTypeIntegerToStringStrategy extends ValueNotEmptyStrategy {
	public TransactionTypeIntegerToStringStrategy() {
		setConverter(new Converter(Integer.class, String.class) {
			@Override
			public Object convert(Object fromObject) {
				Integer index = (Integer) fromObject;
				return TransactionType.values()[index].getName();
			}
		});
	}
}
