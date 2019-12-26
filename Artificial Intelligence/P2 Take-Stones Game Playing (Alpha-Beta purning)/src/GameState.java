import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
  private int size; // The number of stones
  private boolean[] stones; // Game state: true for available stones, false for taken ones
  private int lastMove; // The last move
  public boolean maxPlayer; // Turn of max player

  /**
   * Class constructor specifying the number of stones.
   */
  public GameState(int size, int nTaken) {

    this.size = size;

    // For convenience, we use 1-based index, and set 0 to be unavailable
    this.stones = new boolean[this.size + 1];
    this.stones[0] = false;

    // Set default state of stones to available
    for (int i = 1; i <= this.size; ++i) {
      this.stones[i] = true;
    }

    // Set the last move be -1
    this.lastMove = -1;

    if (nTaken % 2 == 0) {
      maxPlayer = true;
    } else {
      maxPlayer = false;
    }
  }

  /**
   * Copy constructor
   */
  public GameState(GameState other) {
    this.size = other.size;
    this.stones = Arrays.copyOf(other.stones, other.stones.length);
    this.lastMove = other.lastMove;
  }


  /**
   * This method is used to compute a list of legal moves
   *
   * @return This is the list of state's moves
   */
  public List<Integer> getMoves() {
    List<Integer> legalMoves = new ArrayList<Integer>();
    if (lastMove == -1) { // Initial move
      for (int i = 1; i <= Math.floor((this.size) / 2); i++) { // Cut half
        if ((i % 2) != 0) { // Find all odd numbers
          legalMoves.add(i);
        }
      }
    } else { // Subsequent moves
      for (int i = 1; i < this.size + 1; i++) {
        if (stones[i]) {
          if (i < lastMove && (lastMove % i == 0)) { // Find factors
            legalMoves.add(i);
          }
          if (i > lastMove && (i % lastMove == 0)) { // Find multiples
            legalMoves.add(i);
          }
        }
      }
    }
    return legalMoves;
  }


  /**
   * This method is used to generate a list of successors using the getMoves() method
   *
   * @return This is the list of state's successors
   */
  public List<GameState> getSuccessors() {
    return this.getMoves().stream().map(move -> {
      var state = new GameState(this);
      state.removeStone(move);
      return state;
    }).collect(Collectors.toList());
  }


  /**
   * This method is used to evaluate a game state based on the given heuristic function
   *
   * @return int This is the static score of given state
   */
  public double evaluate() {
    maxPlayer = this.maxPlayer();
    if (this.getSuccessors().size() == 0) {
      if (maxPlayer) {
        return 1.0;
      } else {
        return -1.0;
      }
    }
    if (stones[1]) {
      return 0.0;
    }
    if (lastMove == 1) {
      int count = this.getSuccessors().size();
      if (maxPlayer) {
        if (count % 2 != 0) {
          return 0.5;
        } else {
          return -0.5;
        }
      } else {
        if (count % 2 != 0) {
          return -0.5;
        } else {
          return 0.5;
        }
      }
    }
    if (Helper.isPrime(lastMove)) { // Last move is prime
      int count = 0;
      List<Integer> successors = this.getMoves();
      for (int i = 0; i < successors.size(); i++) { // get all multiples
        if (successors.get(i) > lastMove) {
          count++;
        }
      }
      if (maxPlayer) {
        if (count % 2 != 0) {
          return 0.7;
        } else {
          return -0.7;
        }
      } else {
        if (count % 2 != 0) {
          return -0.7;
        } else {
          return 0.7;
        }
      }
    } else { // Last move is not prime
      int largestPrime = Helper.getLargestPrimeFactor(lastMove);
      int count; 
      if (stones[largestPrime]) {
        count = 1; // Include this prime itself if not taken
      } else {
        count = 0;
      }
      List<Integer> successors = this.getMoves();
      for (int i = 0; i < successors.size(); i++) {
        if (successors.get(i) > lastMove) {
          count++;
        }
      }
      if (maxPlayer) {
        if (count % 2 != 0) {
          return 0.6;
        } else {
          return -0.6;
        }
      } else {
        if (count % 2 != 0) {
          return -0.6;
        } else {
          return 0.6;
        }
      }
    }
  }

  /**
   * This method is used to take a stone out
   *
   * @param idx Index of the taken stone
   */
  public void removeStone(int idx) {
    this.stones[idx] = false;
    this.lastMove = idx;
  }

  /**
   * These are get/set methods for a stone
   *
   * @param idx Index of the taken stone
   */
  public void setStone(int idx) {
    this.stones[idx] = true;
  }

  public boolean getStone(int idx) {
    return this.stones[idx];
  }

  /**
   * These are get/set methods for lastMove variable
   *
   * @param move Index of the taken stone
   */
  public void setLastMove(int move) {
    this.lastMove = move;
  }

  public int getLastMove() {
    return this.lastMove;
  }

  /**
   * This is get method for game size
   *
   * @return int the number of stones
   */
  public int getSize() {
    return this.size;
  }

  public boolean maxPlayer() {
    int taken = 0; // number of stones have been taken
    for (int i = 1; i < this.size; i++) {
      if (!stones[i]) {
        taken++;
      }
    }
    if (taken % 2 != 0) {
      return true;
    } else {
      return false;
    }
  }

}
