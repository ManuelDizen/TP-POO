package game.backend.move;

import game.backend.Grid;

//TwoStripedMove extiende de SpecialMove
public class TwoStripedMove extends SpecialMove {

	public TwoStripedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = 0; i < Grid.SIZE; i++) {
			clearContent(i, j2);
			clearContent(i2, i);
		}
	}

}
