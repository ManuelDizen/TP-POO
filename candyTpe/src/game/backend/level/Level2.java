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

    private Cell wallCell;
    private Cell candyGenCell;

    @Override
    protected GameState newState() {
        return new Level2.Level2State(MAX_MOVES, MAX_CELLS);
    }

    @Override
    protected void fillCells() {

        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCell = new CandyGeneratorCell(this);

        //corners
        g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
        g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
        g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

        for (int j = 1; j < SIZE-1; j++) {
            //upper line cells
            g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
            //bottom line cells
            g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
            //left line cells
            g()[j][0].setAround(g()[j-1][0],g()[j+1][0], wallCell ,g()[j][1]);
            //right line cells
            g()[j][SIZE-1].setAround(g()[j-1][SIZE-1],g()[j+1][SIZE-1], g()[j][SIZE-2], wallCell);
            //central cells
            for (int i = 1; i < SIZE-1; i++) {
                g()[j][i].setAround(g()[j-1][i],g()[j+1][i],g()[j][i-1],g()[j][i+1]);
            }
        }
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
