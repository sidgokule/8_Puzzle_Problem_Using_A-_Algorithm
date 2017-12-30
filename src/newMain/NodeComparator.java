package newMain;

import java.util.Comparator;
/**
 * 
 * @author Siddhant
 *
 */
public class NodeComparator implements Comparator<Node>{
	
	@Override
	public int compare(Node n1, Node n2) {
		if(n1.fOfn < n2.fOfn)
			return -1;
		if(n1.fOfn > n2.fOfn)
			return 1;
		return 0;
		
	}

}
