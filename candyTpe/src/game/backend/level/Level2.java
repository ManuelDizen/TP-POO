package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level2 extends Grid {

    private static int MAX_CELLS = SIZE * SIZE;
    private int filledCells = 0;
    protected boolean isGolden[][] = new boolean[SIZE][SIZE];

    @Override
    protected GameState newState() {
        return new Level2.Level2State(MAX_CELLS);
    }

    private int getFilledCells(){
        return filledCells;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            if (j1 == j2){
                for (int i = 0; i < SIZE; i++) {
                    if (!g[i][j1].isGolden()) {
                        g[i][j1].setGolden();
                        filledCells++;
                    }
                }
            }
            else{
                for (int j = 0; j < SIZE; j++) {
                    if (!g[i1][j].isGolden()) {
                        g[i1][j].setGolden();
                        filledCells++;
                    }
                }
            }
        }
        return ret;
    }

    private class Level2State extends GameState {
        private long maxCells;

        public Level2State(int maxCells) {
            this.maxCells = maxCells;
        }

        public boolean playerWon() {
            return getFilledCells() == maxCells;
        }
        
        @Override
        public Integer getSpecialCells() {
            return (SIZE*SIZE) - filledCells;
        }
    }
}
