package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.element.*;

public class Level4 extends Grid {
    private static final int MAX_CELLS = 9;
    private static final int CENTER = 3;
    private int cellsLeft = MAX_CELLS;

    @Override
    protected void fillCells() {
        super.fillCells();
        for (int i = CENTER; i < 2*CENTER; i++)
            for (int j = CENTER; j < 2*CENTER; j++)
                g()[i][j].setWallBlast();
    }

    @Override
    protected GameState newState() {
        return new Level4.Level4State();
    }

    private int getCellsLeft(){
        return cellsLeft;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        Element e1 = g()[i1][j1].getContent();
        Element e2 = g()[i2][j2].getContent();
        checkForBomb(e1, e2);
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            checkForSpecialCandy(e1, i2, j2);
            checkForSpecialCandy(e2, i1, j1);
        }
        return ret;
    }

    private void checkForBomb(Element e1, Element e2){
        if (e1 instanceof Bomb && e2 instanceof Bomb){
            deleteAll();
        }
        else if (e1 instanceof Bomb && e2 instanceof Candy) {
            Candy e = (Candy) e2;
            checkGridForWallBlast(e.getColor());
        }
        else if (e2 instanceof Bomb && e1 instanceof Candy) {
            Candy e = (Candy) e1;
            checkGridForWallBlast(e.getColor());
        }
    }

    private void deleteAll(){
        for (int i = CENTER; i < 2 * CENTER; i++){
            for (int j = CENTER; j < 2 * CENTER; j++) {
                g()[i][j].setWallBlastFalse();
                cellsLeft--;
            }
        }
    }

    private void checkForSpecialCandy(Element e, int row, int col){
        if (e instanceof HorizontalStripedCandy)
            checkRowForWallBlast(row);
        else if (e instanceof VerticalStripedCandy)
            checkColumnForWallBlast(col);
        else if (e instanceof WrappedCandy)
            checkSquareForWallBlast(row, col);
    }

    private void checkRowForWallBlast(int i){
        for (int j = 0; j < SIZE; j++){
            if (g()[i][j].isWallBlast()){
                g()[i][j].setWallBlastFalse();
                cellsLeft--;
            }
        }
    }

    private void checkColumnForWallBlast(int j){
        for (int i = 0; i < SIZE; i++){
            if (g()[i][j].isWallBlast()){
                g()[i][j].setWallBlastFalse();
                cellsLeft--;
            }
        }
    }

    private void checkSquareForWallBlast(int i, int j){
        if (i > 0 && i < SIZE && j > 0 && j < SIZE) {
            for (int row = i - 1; row < i + i; i++)
                for (int col = j - 1; col < j + 1; j++)
                    if (g()[row][col].isWallBlast()) {
                        g()[row][col].setWallBlastFalse();
                        cellsLeft--;
                    }
        }
    }

    private void checkGridForWallBlast(CandyColor color){
        for (int i = CENTER; i < 2 * CENTER; i++){
            for (int j = CENTER; j < 2 * CENTER; j++) {
                if (g()[i][j].isWallBlast() && g()[i][j].getContent() instanceof Candy) {
                    Candy aux = (Candy) g()[i][j].getContent();
                    if (aux.getColor().equals(color)){
                        g()[i][j].setWallBlastFalse();
                        cellsLeft--;
                    }
                }
            }
        }
    }

    private class Level4State extends GameState {

        public boolean playerWon() {
            return getCellsLeft() == 0;
        }

        @Override
        public Integer getSpecialCells() {
            return cellsLeft;
        }
    }
}
