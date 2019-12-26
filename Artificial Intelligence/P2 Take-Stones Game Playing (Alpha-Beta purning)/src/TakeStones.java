import java.util.List;


public class TakeStones {

	/**
	* This is the main method.
	* @param args A sequence of integer numbers, including the number of stones,
	* the number of taken stones, a list of taken stone and search depth
	* @exception IndexOutOfBoundsException On input error.
	*/
	public static void main (String[] args) {
		try {
			// Read input from command line
//			int n = Integer.parseInt(args[0]);		// the number of stones
//			int nTaken = Integer.parseInt(args[1]);	// the number of taken stones
			int n = 10;
			int nTaken = 1;
			
			// Initialize the game state
			GameState state = new GameState(n, nTaken);		// game state
//			int stone;
//			for (int i = 0; i < nTaken; i++) {
//				stone = Integer.parseInt(args[i + 2]);
//				state.removeStone(stone);
//			}
			state.removeStone(1);
//			state.removeStone(4);
//			state.removeStone(2);
//			state.removeStone(6);
//			state.removeStone(7);
//			state.removeStone(21);
//			state.removeStone(3);
//			state.removeStone(6);

			//int depth = Integer.parseInt(args[nTaken + 2]);	// search depth
			int depth = 0;
			// Process for depth being -1
			if (0 == depth)
				depth = n + 1;

			// Get next move
			var searcher = new AlphaBetaPruning();
			searcher.run(state, depth);

			// Print search stats
			searcher.printStats();

		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}