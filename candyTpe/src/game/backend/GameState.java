package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	//Cantiad de movimientos por default (de todas formas, definimos el método setMaxMoves para que se pueda modificar en los niveles)
	private static int MAX_MOVES = 20;
	//Cantiad de celdas especiales por default (de todas formas, definimos el método setSpecialCells para que se pueda modificar en los niveles)
	private int specialCells = 0;
	
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
	
	//devuelve la cantidad de movimientos que faltan para perder
	public Integer getMovesLeft() {
		return MAX_MOVES - moves;
	}
	
	//devuelve la cantidad de special cells en el tablero
	public Integer getSpecialCells() {
		return specialCells;
	}
	
	//decrementa las special cells
	public void deleteSpecialCells(){
		specialCells--;
	}

	//Seteamos la cantidad inicial de special cells
	public void setSpecialCells(int specialCells){
		this.specialCells = specialCells;
	}

	public boolean gameOver(){
		return playerWon() || getMoves() >= MAX_MOVES;
	}
	
	public abstract boolean playerWon();

	public void setMaxMoves(int moves){
		this.MAX_MOVES = moves;
	}
}
