package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	private int MAX_MOVES = 20;
	
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
	
	public void setMoves(int moves){
		MAX_MOVES = moves;
	}
	
	public abstract boolean gameOver();
	
	public abstract boolean playerWon();

}
