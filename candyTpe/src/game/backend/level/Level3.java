package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level3 extends Grid {
    //Cantidad de celdas wallBlast
    private static final int MAX_CELLS = 9;
    //Centro del tablero
    private static final int CENTER = 3;
    //Cantidad máxima de movimientos
    private static final int MAX_MOVES = 30;

    @Override
    protected void fillCells() {
        super.fillCells();
        //Seteamos las 9 celdas del medio como wallBlast
        for (int i = CENTER; i < 2*CENTER; i++)
            for (int j = CENTER; j < 2*CENTER; j++)
                g()[i][j].setWallBlast();
    }

    @Override
    protected GameState newState() {
        return new Level3.Level3State();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level3State extends GameState {
        
        //Seteamos la cantidad de special cells
        public Level3State(){
            this.setSpecialCells(MAX_CELLS);
            //Cambiamos la cantidad máxima de movimientos del default
            this.setMaxMoves(MAX_MOVES);
        }

        //El jugador gana cuando no hay mas specialCells
        public boolean playerWon() {
            return this.getSpecialCells() == 0;
        }
    }
}
