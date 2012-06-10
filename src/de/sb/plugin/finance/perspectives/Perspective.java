package de.sb.plugin.finance.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.sb.plugin.finance.util.R;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(final IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		IFolderLayout content = layout.createFolder("content", IPageLayout.TOP, 1.0f, layout.getEditorArea());
		content.addView(R.VIEW_ID_TRANSACTIONLIST);
		content.addView(R.VIEW_ID_STATISTICS);

		layout.getViewLayout(R.VIEW_ID_TRANSACTIONLIST).setCloseable(false);
		layout.getViewLayout(R.VIEW_ID_STATISTICS).setCloseable(false);
	}
}
