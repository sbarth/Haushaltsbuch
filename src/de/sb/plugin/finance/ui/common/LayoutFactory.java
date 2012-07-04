package de.sb.plugin.finance.ui.common;

import org.eclipse.swt.layout.GridData;

public class LayoutFactory {
	public static GridData createGridData(
			final boolean grabExcessHorizontalSpace,
			final boolean grabExcessVerticalSpace,
			final int horizontalAlignment,
			final int verticalAlignment) {
		GridData gd = new GridData();

		gd.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		gd.grabExcessVerticalSpace = grabExcessVerticalSpace;
		gd.horizontalAlignment = horizontalAlignment;
		gd.verticalAlignment = verticalAlignment;

		return gd;
	}

	public static GridData createGridData(
			final boolean grabExcessHorizontalSpace,
			final boolean grabExcessVerticalSpace,
			final int horizontalAlignment,
			final int verticalAlignment,
			final int horizontalSpan) {
		GridData gd = createGridData(grabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalAlignment, verticalAlignment);
		gd.horizontalSpan = horizontalSpan;

		return gd;
	}

	public static GridData createGridData(
			final boolean grabExcessHorizontalSpace,
			final boolean grabExcessVerticalSpace,
			final int horizontalAlignment,
			final int verticalAlignment,
			final int horizontalSpan,
			final int verticalSpan) {
		GridData gd = createGridData(grabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalAlignment, verticalAlignment);
		gd.horizontalSpan = horizontalSpan;
		gd.verticalSpan = verticalSpan;

		return gd;
	}
}
