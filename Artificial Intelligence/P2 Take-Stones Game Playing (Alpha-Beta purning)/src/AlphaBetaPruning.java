public class AlphaBetaPruning {

  double alpha;
  double beta;
  int bestMove; // Current best move
  double endState;
  boolean maxPlayer;
  int maxDepth;
  double evaluated;
  double visited;
  int reachedDepth;
  double v;
  double notPruned;


  public AlphaBetaPruning() {

  }

  /**
   * This function will print out the information to the terminal, as specified in the homework
   * description.
   */
  public void printStats() {
    System.out.println("Move: " + bestMove);
    System.out.println("Value: " + endState);
    System.out.println("Number of Nodes Visited: " + (int)visited);
    System.out.println("Number of Nodes Evaluated: " + (int)evaluated);
    System.out.println("Max Depth Reached: " + reachedDepth);
    double branchingFactor = notPruned/(visited-evaluated);
    System.out.println("Avg Effective Branching Factor: " + (double)Math.round(branchingFactor*10)/10);
  }

  /**
   * This function will start the alpha-beta search
   * 
   * @param state This is the current game state
   * @param depth This is the specified search depth
   */
  public void run(GameState state, int depth) {
    alpha = Double.NEGATIVE_INFINITY;
    beta = Double.POSITIVE_INFINITY;
    maxDepth = 0;
    evaluated = 0;
    visited = 0;
    notPruned = 0;
    if (depth > state.getSize()) { // Search until the end
      maxDepth = state.getSize();
    } else { // run until the desired depth
      maxDepth = depth;
    }
    endState = alphabeta(state, 0, alpha, beta, state.maxPlayer);
  }

  /**
   * This method is used to implement alpha-beta pruning for both 2 players
   * 
   * @param state     This is the current game state
   * @param depth     Current depth of search
   * @param alpha     Current Alpha value
   * @param beta      Current Beta value
   * @param maxPlayer True if player is Max Player; Otherwise, false
   * @return int This is the number indicating score of the best next move
   */
  private double alphabeta(GameState state, int depth, double alpha, double beta, boolean maxPlayer) {
    
    visited++;
    
    if(depth > reachedDepth) { // Update depth
      reachedDepth = depth;
    }

    if(depth == maxDepth || state.getSuccessors().size() == 0) { // Terminal state
      evaluated++;
      return state.evaluate();
    }

    if (maxPlayer) { // Max player's turn
      v = Double.NEGATIVE_INFINITY;
      double tempBest = Double.NEGATIVE_INFINITY;
      for (int i = 0; i < state.getSuccessors().size(); i++) {
        notPruned++;
        GameState temp = state.getSuccessors().get(i);
        v = Math.max(v, alphabeta(state.getSuccessors().get(i), depth + 1, alpha, beta, false));
        if(tempBest < v && depth == 0) {
          tempBest = v;
          bestMove = temp.getLastMove();
        }
        if(v >= beta) {
          return v;
        }
        alpha = Math.max(alpha, v);
      }
      return v;
    } else {
      v = Double.POSITIVE_INFINITY;
      double tempBest = Double.POSITIVE_INFINITY;
      for (int i = 0; i < state.getSuccessors().size(); i++) {
        notPruned++;
        GameState temp = state.getSuccessors().get(i);
        v = Math.min(v, alphabeta(state.getSuccessors().get(i), depth + 1, alpha, beta, true));
        if(tempBest > v && depth == 0) {
          tempBest = v;
          bestMove = temp.getLastMove();
        }
        if(v <= alpha) {
          return v;
        }
        beta = Math.min(beta, v);
      }
      return v;
    }
  }

}
