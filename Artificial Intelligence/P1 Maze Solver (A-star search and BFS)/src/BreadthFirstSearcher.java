import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 * 
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public BreadthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main breadth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		State initialState = new State(maze.getPlayerSquare(), null, 0, 0);
		ArrayList<State> successors;

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// Queue implementing the Frontier list
		LinkedList<State> queue = new LinkedList<State>();
		queue.add(initialState);
		maxSizeOfFrontier = queue.size();
		
		while (!queue.isEmpty()) {
			State expandNode = queue.pop();
			if(!explored[expandNode.getX()][expandNode.getY()]) {
				explored[expandNode.getX()][expandNode.getY()] = true;
				noOfNodesExpanded++;
				if (expandNode.getDepth() > maxDepthSearched) {
					maxDepthSearched = expandNode.getDepth();
				}
				
				// If found the goal
				if(expandNode.isGoal(maze)) {
					cost = expandNode.getDepth();
					maxDepthSearched = expandNode.getDepth();
					State drawNode = expandNode.getParent(); // Drawing backward
					while (drawNode.getParent() != null) {
				          maze.setOneSquare(drawNode.getSquare(), '.');
				          drawNode = drawNode.getParent();
				    }
					return true;
				} 
					
				// Otherwise
				successors = new ArrayList<State>();
				successors = expandNode.getSuccessors(explored, maze);
				for(int i = 0; i < successors.size(); i++) {
					State newState = successors.get(i);
					boolean existed = false;
					for(State existState : queue) { // Check if there is duplicate in frontiers
						if(existState.getX() == newState.getX() && existState.getY() == newState.getY()) {
							existed = true;
						}
					}
					if(!existed) {
						queue.add(newState);
					}
				}
				if(queue.size() > maxSizeOfFrontier) {
					maxSizeOfFrontier = queue.size();
				}
			}
		}

		return false;
	}
}
