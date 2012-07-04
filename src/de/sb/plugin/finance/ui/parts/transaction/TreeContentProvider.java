package de.sb.plugin.finance.ui.parts.transaction;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.Viewer;

import de.sb.plugin.finance.entities.Transaction;

public class TreeContentProvider implements ITreeContentProvider {
	@Override
	public void dispose() {

	}

	@Override
	public Object[] getChildren(final Object parentElement) {
		if (parentElement instanceof TreeNode) {
			return ((TreeNode) parentElement).getChildren();
		} else if (parentElement instanceof List<?>) {
			return ((List<?>) parentElement).toArray();
		} else if (parentElement instanceof Transaction) {
			return null;
		}

		return null;
	}

	@Override
	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object getParent(final Object element) {
		return ((TreeNode) element).getParent();
	}

	@Override
	public boolean hasChildren(final Object element) {
		return ((TreeNode) element).hasChildren();
	}

	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {

	}
}
