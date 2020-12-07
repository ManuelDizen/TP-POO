package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.HorizontalStripedCandy;
import game.backend.element.VerticalStripedCandy;

//BombStippedMove extiende de BombMove
public class BombStrippedMove extends BombMove {

	public BombStrippedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		CandyColor color = candy.getColor();
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					setContent(i, j, createStriped(color));
				}
			}
		}
		wasUpdated();
		//Utilizamos clearCells ya definida en BombMove
		super.clearCells(candy);
	}
	
	private Candy createStriped(CandyColor color) {
		Candy c;
		if ((int)(Math.random() * 2) == 0) {
			c = new HorizontalStripedCandy();
		} else {
			c = new VerticalStripedCandy();
		}
		c.setColor(color);
		return c;
	}

}
