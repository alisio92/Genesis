package com.alisio.genesis.util;

import java.util.Comparator;
import com.alisio.genesis.level.Node;

public class NodeComparator implements Comparator<Node>{

	public int compare(Node n0, Node n1) {
		if (n1.fCost < n0.fCost) return 1;
		if (n1.fCost > n0.fCost) return -1;
		return 0;
	}
}
