import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

  /**
   * Calls the parent class constructor.
   * 
   * @see Searcher
   * @param maze initial maze.
   */
  public AStarSearcher(Maze maze) {
    super(maze);
  }

  /**
   * Main a-star search algorithm.
   * 
   * @return true if the search finds a solution, false otherwise.
   */
  public boolean search() {

    State initialState = new State(maze.getPlayerSquare(), null, 0, 0);
    ArrayList<State> successors;
    boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
    PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();
    frontier.add(new StateFValuePair(initialState,
        distance(initialState.getSquare(), maze.getGoalSquare()) + initialState.getGValue()));
    maxSizeOfFrontier = frontier.size();

    while (!frontier.isEmpty()) {
      State expandNode = frontier.poll().getState();
      if (!explored[expandNode.getX()][expandNode.getY()]) {
        explored[expandNode.getX()][expandNode.getY()] = true;
        noOfNodesExpanded++;
        if (expandNode.getDepth() > maxDepthSearched) {
          maxDepthSearched = expandNode.getDepth();
        }

        // If expanded node is the goal
        if (expandNode.isGoal(maze)) {
          // (Copy from BFS file)
          cost = expandNode.getDepth();
          maxDepthSearched = expandNode.getDepth();
          State drawNode = expandNode.getParent();
          while (drawNode.getParent() != null) {
            maze.setOneSquare(drawNode.getSquare(), '.');
            drawNode = drawNode.getParent();
          }
          return true;
        }

        // Otherwise
        successors = new ArrayList<State>();
        successors = expandNode.getSuccessors(explored, maze);
        for (int i = 0; i < successors.size(); i++) {
          State newState = successors.get(i);
          boolean existed = false; // If the pair already been marked in frontier
          boolean replaced = false; // If the pair can be replaced with a better option
          StateFValuePair newPair = new StateFValuePair(newState,
              distance(newState.getSquare(), maze.getGoalSquare()) + newState.getGValue());
          // Iterate through frontiers
          Iterator<StateFValuePair> fIterator = frontier.iterator();
          while (fIterator.hasNext()) {
            StateFValuePair existPair = (StateFValuePair) fIterator.next();
            // If the new pair already in frontiers
            if (existPair.getState().getX() == newState.getX()
                && existPair.getState().getY() == newState.getY()) {
              existed = true;
              // If yes, compare with existed one to decide if
              // the existed one should be replaced
              if (newState.getGValue() < existPair.getState().getGValue()) {
                replaced = true;
              }
            }
          }
          if (!existed || replaced) {
            frontier.add(newPair);
          }
        }
        if (frontier.size() > maxSizeOfFrontier) {
          maxSizeOfFrontier = frontier.size();
        }
      }
    }
    return false;
  }

  /**
   * Private method to calculate Euclidean distance
   * 
   * @param current is the current square
   * @param goal    is the goal square
   * @return Euclidean distance between them
   */
  private double distance(Square current, Square goal) {

    double x_sqr = (current.X - goal.X) * (current.X - goal.X);
    double y_sqr = (current.Y - goal.Y) * (current.Y - goal.Y);

    return Math.sqrt(x_sqr + y_sqr);

  }
}
