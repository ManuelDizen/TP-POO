package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;

//BombMove extiende de SpecialMove
public class BombMove extends Move {
	
	public BombMove(Grid grid) {
		super(grid);
	}

	@Override
	public void removeElements() {
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		clearContent(i1, j1);
		clearContent(i2, j2);
		clearCells(candy);
	}
	
	//Creamos el método clearCells para que la puedan utilizar las clases hijas (por eso es protected)
	protected void clearCells(Candy candy){
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}
	}

}
