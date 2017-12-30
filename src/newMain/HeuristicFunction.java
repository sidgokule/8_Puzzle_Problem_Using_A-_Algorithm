package newMain;
/**
 * 
 * @author Siddhant
 *
 */
public class HeuristicFunction {

	/**
	 * Calculate total Manhattan distance
	 * @param currentState
	 * @param goalState
	 * @return
	 */
	int calculateHeuristicFunctionValue(Node currentState, Node goalState) {
		int manhattanDistance = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int temp = currentState.state[i][j];
				if(temp != 0) {
					manhattanDistance = manhattanDistance + locationInGoalState(temp, i, j, goalState);

				}
			}
		}
		
		return manhattanDistance;
	}
	/**
	 * Calculate Manhattan distance of particular element
	 * @param number
	 * @param row
	 * @param column
	 * @param goalState
	 * @return
	 */
	int locationInGoalState(int number, int row, int column, Node goalState) {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(number == goalState.state[i][j]) {
					return Math.abs(i - row) + Math.abs(j - column);
				}
			}
		}
		return 0;
	}
	
}
