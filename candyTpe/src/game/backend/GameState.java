package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	private static final int MAX_MOVES = 20;
	
	public void addScore(long value) {
		this.score = this.score + value;
	}
	
	public Long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public Integer getMovesLeft() {
		return MAX_MOVES - moves;
	}
	
	public abstract Integer getSpecialCells();

	public boolean gameOver(){
		return playerWon() || getMoves() >= MAX_MOVES;
	}
	
	public abstract boolean playerWon();

}
