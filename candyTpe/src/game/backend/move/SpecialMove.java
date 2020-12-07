package game.backend.move;

import game.backend.Grid;

//Creamos la clase SpecialMove para encapsular a los movimientos especiales
public abstract class SpecialMove extends Move{
    public SpecialMove(Grid grid){
        super(grid);
    }
}
