package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level2 extends Grid {

    //Cantidad máxima de celdas del tablero
    private static int MAX_CELLS = SIZE * SIZE;

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
            //Si j1 = j2, significa que el movimiento fue vertical por lo cual seteamos la columna en dorado
            if (j1 == j2){
                for (int i = 0; i < SIZE; i++) {
                    if (!g()[i][j1].isGolden()) {
                        g()[i][j1].setGolden();
                        this.deleteSpecialCell();
                    }
                }
            }
            //Si j1 != j2, significa que el movimiento fue horizontal por lo cual seteamos la fila en dorado
            else{
                for (int j = 0; j < SIZE; j++) {
                    if (!g()[i1][j].isGolden()) {
                        g()[i1][j].setGolden();
                        this.deleteSpecialCell();
                    }
                }
            }
        }
        return ret;
    }

    private class Level2State extends GameState {
        //Seteamos las specialCells (tomamos como que una celda normal es especial y las doradas son las normales)
        public Level2State(int maxCells) {
            this.setSpecialCells(maxCells);
        }

        //El jugador gana si no hay mas celdas normales (en este caso sería si las especiales son 0)
        public boolean playerWon() {
            return this.getSpecialCells() == 0;
        }
    }
}
