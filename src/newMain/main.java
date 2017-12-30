package newMain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * 
 * @author Siddhant
 *
 */
public class main {
	static int nodesGenerated = 0;
	static int nodesExpanded = 0;
	
	static Comparator<Node> nodeComparator = new NodeComparator();
	//Create a priority queue for active nodes
	static PriorityQueue<Node> activeNodes = new PriorityQueue<>(nodeComparator);
	//Create a priority queue for visited nodes
	static ArrayList<Node> visitedNodes = new ArrayList<>();
	
	public static void main(String[] args) {
		Node initialState = new Node();
		Node goalState = new Node();
		HeuristicFunction heuristicFunction = new HeuristicFunction();
		
		//Take the start/initial state
		System.out.println("Enter start state \n");
		for(int i=0; i<3; i++) 
			for(int j=0;j<3;j++) {
			System.out.println("Enter value at [" + i + "][" + j + "]: ");
			Scanner in = new Scanner(System.in);
			initialState.state[i][j] = in.nextInt();
			}
		
		//Take the goal state
		System.out.println("Enter goal state \n");
		for(int i=0; i<3; i++) 
			for(int j=0;j<3;j++) {
			System.out.println("Enter value at [" + i + "][" + j + "]: ");
			Scanner in = new Scanner(System.in);
			goalState.state[i][j] = in.nextInt();
			}
		
		//Print the initial state
		System.out.println("This is the start state: \n");
		initialState.displayNode(initialState);
				
		//Print the goal state
		System.out.println("This is the goal state:\n");
		goalState.displayNode(goalState);
		
		//Calculate hOfn for initial state
		initialState.hOfn = heuristicFunction.calculateHeuristicFunctionValue(initialState, goalState);
		//Set gOfn=0 for root/initial node
		initialState.gOfn = 0;
		//Calculate fOfn for initial state
		initialState.fOfn = initialState.gOfn + initialState.hOfn;
		//Check blank row and col for initial state
		outerloop:
		for(int i=0; i<3; i++) {
			for(int j=0;j<3;j++) {
				if(initialState.state[i][j] == 0) {
					initialState.blankRow = i;
					initialState.blankCol = j;
					break outerloop;
				}
			}
		}
		//Initialize goalState for rootNode
		initialState.goalState = goalState;
		activeNodes.add(initialState);
		
		while(!activeNodes.isEmpty()){
			Node currentNode = activeNodes.remove();
			//Check if the node is the goal state
			if(currentNode.checkGoalState(currentNode, goalState)) 
				goalStateReached(nodesExpanded, nodesGenerated);
			
			//If node is already visited skip that node from expanding
			if(currentNode.isNodePresent(currentNode, visitedNodes))
				continue;
			
			//Mark node as visited and expand it
			visitedNodes.add(currentNode);
			expandNode(currentNode);
		}
		
	}
	
	/**
	 * Expand Method
	 * @param currentNode
	 */
	public static void expandNode(Node currentNode) {
		
		nodesExpanded++;
		moveLeft(currentNode, visitedNodes);
		moveRight(currentNode, visitedNodes);
		moveUp(currentNode, visitedNodes);
		moveDown(currentNode, visitedNodes);
	}
	
	/**
	 * UP Function
	 * @param currentNode
	 * @param visitedNodes
	 */
	public static void moveUp(Node currentNode, ArrayList<Node> visitedNodes) {
		int blankRow = currentNode.getBlankRow();
		int blankCol = currentNode.getBlankCol();
		int [][] tempArray = new int[3][3];
		int temp;
		HeuristicFunction heuristicFunction = new HeuristicFunction();
		
		if((blankRow-1) == -1)
			return;
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				tempArray[i][j] = currentNode.state[i][j];
		//Main Logic
		temp = tempArray[blankRow-1][blankCol];
		tempArray[blankRow-1][blankCol] = 0;
		tempArray[blankRow][blankCol] = temp;
		Node node = new Node(tempArray, currentNode, blankRow-1, blankCol);
		node.gOfn = currentNode.gOfn+1;
		node.hOfn = heuristicFunction.calculateHeuristicFunctionValue(node, currentNode.goalState);
		node.fOfn = node.gOfn + node.hOfn;
		node.goalState = currentNode.goalState;
		if(!node.isNodePresent(node, visitedNodes)) {
			nodesGenerated++;
			activeNodes.add(node);
		}
		return;
	}
	
	/**
	 * DOWN Function
	 * @param currentNode
	 * @param visitedNodes
	 */
	public static void moveDown(Node currentNode, ArrayList<Node> visitedNodes) {
		int blankRow = currentNode.getBlankRow();
		int blankCol = currentNode.getBlankCol();
		int [][] tempArray = new int[3][3];
		int temp;
		HeuristicFunction heuristicFunction = new HeuristicFunction();
		
		if((blankRow+1) == 3)
			return;
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				tempArray[i][j] = currentNode.state[i][j];
		//Main Logic
		temp = tempArray[blankRow+1][blankCol];
		tempArray[blankRow+1][blankCol] = 0;
		tempArray[blankRow][blankCol] = temp;
		Node node = new Node(tempArray, currentNode, blankRow+1, blankCol);
		node.gOfn = currentNode.gOfn+1;
		node.hOfn = heuristicFunction.calculateHeuristicFunctionValue(node, currentNode.goalState);
		node.fOfn = node.gOfn + node.hOfn;
		node.goalState = currentNode.goalState;
		if(!node.isNodePresent(node, visitedNodes)) {
			nodesGenerated++;
			activeNodes.add(node);
		}
		return;
	}
		/**
		 * MOVE RIGHT 
		 * @param currentNode
		 * @param visitedNodes
		 */
		public static void moveRight(Node currentNode, ArrayList<Node> visitedNodes) {
			int blankRow = currentNode.getBlankRow();
			int blankCol = currentNode.getBlankCol();
			int [][] tempArray = new int[3][3];
			int temp;
			HeuristicFunction heuristicFunction = new HeuristicFunction();
			
			if((blankCol+1) == 3)
				return;
			
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					tempArray[i][j] = currentNode.state[i][j];
			
			temp = tempArray[blankRow][blankCol+1];
			tempArray[blankRow][blankCol+1] = 0;
			tempArray[blankRow][blankCol] = temp;
			Node node = new Node(tempArray, currentNode, blankRow, blankCol+1);
			node.gOfn = currentNode.gOfn+1;
			node.hOfn = heuristicFunction.calculateHeuristicFunctionValue(node, currentNode.goalState);
			node.fOfn = node.gOfn + node.hOfn;
			node.goalState = currentNode.goalState;
			if(!node.isNodePresent(node, visitedNodes)) {
				nodesGenerated++;
				activeNodes.add(node);
			}
			return;
		}
		
	/**
	 * LEFT Function
	 * @param currentNode
	 * @param visitedNodes
	 */
		public static void moveLeft(Node currentNode, ArrayList<Node> visitedNodes) {
					int blankRow = currentNode.getBlankRow();
					int blankCol = currentNode.getBlankCol();
					int [][] tempArray = new int[3][3];
					int temp;
					HeuristicFunction heuristicFunction = new HeuristicFunction();
					
					if((blankCol-1) == -1)
						return;
					
					for(int i=0;i<3;i++)
						for(int j=0;j<3;j++)
							tempArray[i][j] = currentNode.state[i][j];
					//Main Logic
					temp = tempArray[blankRow][blankCol-1];
					tempArray[blankRow][blankCol-1] = 0;
					tempArray[blankRow][blankCol] = temp;
					
					Node node = new Node(tempArray, currentNode, blankRow, blankCol-1);
					node.gOfn = currentNode.gOfn+1;
					node.hOfn = heuristicFunction.calculateHeuristicFunctionValue(node, currentNode.goalState);
					node.fOfn = node.gOfn + node.hOfn;
					node.goalState = currentNode.goalState;
					
					if(!node.isNodePresent(node, visitedNodes)) {
						nodesGenerated++;
						activeNodes.add(node);
					}
					return;
				}
		/**
		 * Check if goal state is reached
		 * @param nodesExpanded
		 * @param nodesGenerated
		 */
		public static void goalStateReached(int nodesExpanded, int nodesGenerated) {
			System.out.println("Goal state reached");
			System.out.println("Number of nodes expanded = "+nodesExpanded);
			System.out.println("Number of nodes generated = "+nodesGenerated);
			System.exit(0);
			
		}
}
