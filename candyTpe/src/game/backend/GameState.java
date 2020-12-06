package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	private static final int MAX_MOVES = 20;
	
	public void addScore(long value) {
		this.score = this.score + value;
	}
	
	public long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public int getMovesLeft() {
		return MAX_MOVES - moves;
	}

	public boolean gameOver(){
		return playerWon() || getMoves() >= MAX_MOVES;
	}
	
	public abstract boolean playerWon();

}
