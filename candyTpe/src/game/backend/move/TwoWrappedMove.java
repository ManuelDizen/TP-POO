package game.backend.move;

import game.backend.Grid;

//TwoWrappedMove extiende de SpecialMove
public class TwoWrappedMove extends SpecialMove {
	private int currI, currJ;
	
	public TwoWrappedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		if (i1 == i2) {
			if (j1 < j2)
				setCurrent(i1, j1);
			else
				setCurrent(i2, j2);
		} else {
			if (i1 < i2)
				setCurrent(i1, j1);
			else
				setCurrent(i2, j2);
		}
		clear(currI, currJ);
	}
	
	//Creamos los métodos setCurrent y clear para modularizar y no repetir código

	private void setCurrent(int i, int j){
		currI = i;
		currJ = j;
	}

	private void clear(int i, int j){
		clearContent(i,j-1);
		clearContent(i, j + 2);
		for(int n = -1; n < 3; n++) {
			clearContent(i - 1, j + n);
			clearContent(i + 1, j + n);
		}
	}

}
