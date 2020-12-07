package game.backend.move;

import game.backend.Grid;

//TwoBombMove extiende de SpecialMove (no de BombMove porque tienen funcionalidades distintas)
public class TwoBombMove extends SpecialMove {
	
	public TwoBombMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				clearContent(i,j);
			}
		}
	}

}
