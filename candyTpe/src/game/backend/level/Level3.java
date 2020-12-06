package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level3 extends Grid {

    private static int CENTER = 4;
    private int jailsLeft = 8;
    private boolean hasJail[] = new boolean[SIZE];

    public Level3(){
        for (int i = 0; i < SIZE; i++)
            if (i != CENTER)
                hasJail[i] = true;
    }

    @Override
    protected GameState newState() {
        return new Level3.Level3State();
    }

    private int getJailsLeft(){
        return jailsLeft;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if ((i1 == CENTER && hasJail[j1]) || (i2 == CENTER && hasJail[j2]))
            return false;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level3State extends GameState {

        public boolean playerWon() {
            return getJailsLeft() == 0;
        }
        
        @Override
        public Integer getSpecialCells() {
            return jailsLeft;
        }
    }
}
