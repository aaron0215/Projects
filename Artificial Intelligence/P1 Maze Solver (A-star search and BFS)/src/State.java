import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the BFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            explored[i][j] is true if (i,j) is already explored
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
		// FILL THIS METHOD
		ArrayList<State> successors = new ArrayList<State>();
		// Check left state
		if (!explored[this.getX()][this.getY()-1] 
				&& maze.getSquareValue(this.getX(),this.getY()-1) != '%') {
			successors.add(new State(new Square(this.getX(),this.getY()-1), this, this.gValue+1, this.getDepth()+1));
		}
		// Check down state
		if (!explored[this.getX()+1][this.getY()]
				&& maze.getSquareValue(this.getX()+1,this.getY()) != '%') {
			successors.add(new State(new Square(this.getX()+1,this.getY()), this, this.gValue+1, this.getDepth()+1));
		}
		// Check right state
		if (!explored[this.getX()][this.getY()+1]
				&& maze.getSquareValue(this.getX(),this.getY()+1) != '%') {
			successors.add(new State(new Square(this.getX(),this.getY()+1), this, this.gValue+1, this.getDepth()+1));
		}
		// Check up state
		if (!explored[this.getX()-1][this.getY()]
				&& maze.getSquareValue(this.getX()-1,this.getY()) != '%') {
			successors.add(new State(new Square(this.getX()-1,this.getY()), this, this.gValue+1, this.getDepth()+1));
		}
		// TODO check all four neighbors in left, down, right, up order
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
		return successors;
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the BFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
}
