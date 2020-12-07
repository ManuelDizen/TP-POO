package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level1 extends Grid {
	
	private static int REQUIRED_SCORE = 5000; 
	
	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE);
	}

	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		return ret;
	}
	
	private class Level1State extends GameState {
		private long requiredScore;
		
		public Level1State(long requiredScore) {
			this.requiredScore = requiredScore;
		}

		public boolean playerWon() {
			return getScore() > requiredScore;
		}
		
		@Override
		public Integer getSpecialCells() {
			return 0;
		}
	}

}
