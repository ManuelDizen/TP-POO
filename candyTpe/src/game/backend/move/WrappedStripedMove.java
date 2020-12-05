package game.backend.move;

import game.backend.Grid;

public class WrappedStripedMove extends Move {

	public WrappedStripedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = -1; i < 2; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (i2 + i >= 0 && i2 + i < Grid.SIZE) {
					clearContent(i2 + i, j);
				}
				if (j2 + i >= 0 && j2 + i < Grid.SIZE) {
					clearContent(j, j2 + i);
				}
			}
		}

	}

}
