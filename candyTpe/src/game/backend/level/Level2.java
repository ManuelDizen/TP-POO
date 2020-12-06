package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level2 extends Grid {

    private static int MAX_MOVES = 20;
    private static int MAX_CELLS = SIZE * SIZE;
    private int filledCells = 0;
    protected boolean isGolden[][] = new boolean[SIZE][SIZE];

    @Override
    protected GameState newState() {
        return new Level2.Level2State(MAX_MOVES, MAX_CELLS);
    }

    private int getFilledCells(){
        return filledCells;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            if (i1 == i2){
                for (int i = 0; i < SIZE; i++) {
                    if (isGolden[i1][i])
                       // setGolden(i1, i);
                    isGolden[i1][i] = true;
                }
            }
            else{
                for (int i = 0; i < SIZE; i++) {
                    if (isGolden[i][j1])
                      //  setGolden(i, j1);
                    isGolden[i][j1] = true;
                }
            }
            //ACA APLICAR EL VOLVER LA FILA O LA COLUMNA DORADA
        }
        return ret;
    }

    private class Level2State extends GameState {
        private long maxCells;
        private long maxMoves;

        public Level2State(int maxMoves, int maxCells) {
            this.maxMoves = maxMoves;
            this.maxCells = maxCells;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getFilledCells() == maxCells;
        }
    }
}
