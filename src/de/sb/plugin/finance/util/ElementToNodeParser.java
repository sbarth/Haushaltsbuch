package de.sb.plugin.finance.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import de.sb.plugin.finance.entities.Transaction;
import de.sb.plugin.finance.entities.TransactionCompareByDate;

public class ElementToNodeParser {
	private enum LabelType {
		DATE,
		DAY_OF_WEEK,
		FROM_TO_DATE,
		MONTH_OF_YEAR,
		WEEK_OF_YEAR,
	}

	private List<Transaction> transactions;

	public ElementToNodeParser(final List<Transaction> transactions) {
		this.transactions = transactions;
	}

	private String getLabel(final Calendar date, final LabelType type) {
		String result = "";
		SimpleDateFormat format = null;

		switch (type) {
			case DATE:
				format = new SimpleDateFormat("dd.MM.yyyy");
				result = format.format(date.getTime());
				break;
			case DAY_OF_WEEK:
				format = new SimpleDateFormat("dd.MM.yyyy (EE)");
				result = format.format(date.getTime());
				break;
			case FROM_TO_DATE:
				break;
			case MONTH_OF_YEAR:
				format = new SimpleDateFormat("MMMM yyyy");
				result = format.format(date.getTime());
				break;
			case WEEK_OF_YEAR:
				format = new SimpleDateFormat("ww");
				result = "KW " + format.format(date.getTime());
				break;
			default:
				break;
		}

		return result;
	}

	private TreeNode[] listToArray(final List<TreeNode> list) {
		TreeNode[] array = new TreeNode[list.size()];

		int index = 0;
		for (TreeNode node : list) {
			array[index++] = node;
		}

		return array;
	}

	public TreeNode parseByDay() {
		TreeNode root = new TreeNode("ROOT");
		List<TreeNode> rootChildren = new ArrayList<TreeNode>();
		List<TreeNode> nodeChildren = new ArrayList<TreeNode>();

		if (transactions.size() == 0) {
			return root;
		}

		Collections.sort(transactions, new TransactionCompareByDate());
		Calendar old = transactions.get(0).getDate();

		TreeNode node = new TreeNode(getLabel(transactions.get(0).getDate(), LabelType.DAY_OF_WEEK));
		node.setParent(root);

		for (int index = 0; index < transactions.size(); index++) {
			Transaction transaction = transactions.get(index);
			Calendar current = transaction.getDate();

			if (!Compare.areOnSameDay(old, current)) {
				node.setChildren(listToArray(nodeChildren));
				nodeChildren = new ArrayList<>();
				rootChildren.add(node);
				node = new TreeNode(getLabel(transaction.getDate(), LabelType.DAY_OF_WEEK));
				node.setParent(root);
				old = current;
			}

			TreeNode subNode = new TreeNode(transaction);
			subNode.setParent(node);
			nodeChildren.add(subNode);
		}

		node.setChildren(listToArray(nodeChildren));
		nodeChildren = new ArrayList<>();
		rootChildren.add(node);

		root.setChildren(listToArray(rootChildren));

		return root;
	}

	public TreeNode parseByNothing() {
		TreeNode root = new TreeNode("ROOT");
		List<TreeNode> rootChildren = new ArrayList<TreeNode>();

		Collections.sort(transactions, new TransactionCompareByDate());

		for (Transaction transaction : transactions) {
			TreeNode node = new TreeNode(transaction);
			node.setParent(root);
			rootChildren.add(node);
		}

		root.setChildren(listToArray(rootChildren));

		return root;
	}

	public TreeNode parseByWeek() {
		TreeNode root = new TreeNode("ROOT");
		List<TreeNode> rootChildren = new ArrayList<TreeNode>();
		List<TreeNode> nodeChildren = new ArrayList<TreeNode>();

		if (transactions.size() == 0) {
			return root;
		}

		Collections.sort(transactions, new TransactionCompareByDate());
		Calendar old = transactions.get(0).getDate();

		TreeNode node = new TreeNode(getLabel(transactions.get(0).getDate(), LabelType.WEEK_OF_YEAR));
		node.setParent(root);

		for (int index = 0; index < transactions.size(); index++) {
			Transaction transaction = transactions.get(index);
			Calendar current = transaction.getDate();

			if (!Compare.areInSameWeek(old, current)) {
				node.setChildren(listToArray(nodeChildren));
				nodeChildren = new ArrayList<>();
				rootChildren.add(node);
				node = new TreeNode(getLabel(transaction.getDate(), LabelType.WEEK_OF_YEAR));
				node.setParent(root);
				old = current;
			}

			TreeNode subNode = new TreeNode(transaction);
			subNode.setParent(node);
			nodeChildren.add(subNode);
		}

		node.setChildren(listToArray(nodeChildren));
		nodeChildren = new ArrayList<>();
		rootChildren.add(node);

		root.setChildren(listToArray(rootChildren));

		return root;
	}
}
