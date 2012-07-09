package de.sb.plugin.finance.ui.dialogs;

import java.util.List;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingStatus;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public abstract class AbstractDialog extends TitleAreaDialog {
	private final String title;

	public AbstractDialog(Shell parentShell, String title) {
		super(parentShell);
		this.title = title;
	}

	protected boolean checkSeverity(List<BindingStatus> statusList) {
		for (BindingStatus status : statusList) {
			if (status.getSeverity() != ValidationStatus.ok().getSeverity()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void create() {
		super.create();

		setBinding();
	}

	@Override
	protected Control createContents(final Composite parent) {
		Control contents = super.createContents(parent);

		setTitle(title);

		return contents;
	}

	protected abstract void setBinding();
}
