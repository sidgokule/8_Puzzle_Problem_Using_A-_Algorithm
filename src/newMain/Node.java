package newMain;

import java.util.ArrayList;

public class Node {
	int [][]state = new int[3][3];
	int gOfn;
	int hOfn;
	int fOfn;
	Node parent;
	Node link;
	Node goalState;
	int blankRow;
	int blankCol;
	
	//Default Constructor
	public Node() {
		
	}
	
	//Parametrized Constructor
	public Node(int[][] state, Node parent, int blankRow, int blankCol) {
		super();
		this.state = state;
		this.parent = parent;
		this.blankRow = blankRow;
		this.blankCol = blankCol;
	}
	public int getBlankRow() {
		return blankRow;
	}
	public int getBlankCol() {
		return blankCol;
	}
	
	//Method to print current node
	public void displayNode(Node currentNode) {
		for(int i=0; i<3; i++) {
			for(int j=0;j<3;j++) {
				System.out.print(currentNode.state[i][j]+" ");
		}
		System.out.println("\n");
		}
	}
	
	//Check if goalState is reached
	public boolean checkGoalState(Node currentState, Node goalState) {
		
		for(int i=0; i<3; i++) 
			for(int j=0;j<3;j++)
				if(currentState.state[i][j] != goalState.state[i][j])
					return false;
		return true;
		
	}
	
	//Check if node is already visited
	public boolean isNodePresent(Node currentNode, ArrayList<Node> visitedNodesList) {
		
		if(visitedNodesList.isEmpty())
			return false;
		
		for(Node checkNode: visitedNodesList) {
			if(currentNode.hOfn == checkNode.hOfn) {
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(currentNode.state[i][j] != checkNode.state[i][j])
							return false;
					}
				}
				return true;
				
			}
			
		}
		return false;
		
	}
	
	
	
}
