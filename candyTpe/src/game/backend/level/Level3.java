package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level3 extends Grid {

    private static final int MAX_CHERRIES = 2;
    private static final int MAX_HAZELNUTS = 1;
    private static final int MAX_FRUITS = MAX_CHERRIES + MAX_HAZELNUTS;
    private Cell[] fruits = new Cell[MAX_FRUITS];
    private int fruitsLeft = MAX_FRUITS;

    @Override
    protected void fillCells() {
        candyGenCell = new FruitGeneratorCell(this, new Cherry(), MAX_CHERRIES, new Hazelnut(), MAX_HAZELNUTS);
        super.fillCells();
    }

    @Override
    protected GameState newState() {
        return new Level3.Level3State();
    }

    private int getFruitsLeft(){
        return fruitsLeft;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            for (int j = 0; j < SIZE-1; j++){
                if (g()[SIZE-1][j].isFruit()){
                    g()[SIZE-1][j].fallUpperContent();
                }
            }
        }
        return ret;
    }

    private class Level3State extends GameState {

        public boolean playerWon() {
            return getFruitsLeft() == 0;
        }

        @Override
        public Integer getSpecialCells() {
            return fruitsLeft;
        }
    }
}
