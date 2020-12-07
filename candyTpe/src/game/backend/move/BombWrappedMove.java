package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;

//BombWrappedMove extiende de BombMove
public class BombWrappedMove extends BombMove {

	public BombWrappedMove(Grid grid) {
		super(grid);	
	}
	
	@Override
	public void removeElements() {
		//Utilizamos el remove elements de BombMove y después agregamos la funcionaliad nueva
		super.removeElements();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i1 + i >= 0 && i1 + i < Grid.SIZE && j1 + j >= 0 && j1 + j < Grid.SIZE) {
					clearContent(i1 + i, j1 + j);
				}
				if (i2 + i >= 0 && i2 + i < Grid.SIZE && j2 + j >= 0 && j2 + j < Grid.SIZE) {
					clearContent(i2 + i, j2 + j);
				}
			}
		}
	
	}

}
