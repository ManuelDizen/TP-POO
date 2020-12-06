package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;


public class FruitGeneratorCell extends CandyGeneratorCell{
    private static final double FREQUENCY = 0.01;
    private Element fruit1, fruit2;
    private int max_fruits1, max_fruits2;

    public FruitGeneratorCell(Grid grid, Element fruit1, int max_fruits1, Element fruit2, int max_fruits2) {
        super(grid);
        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        this.max_fruits1 = max_fruits1;
        this.max_fruits2 = max_fruits2;

    }

    @Override
    public Element getContent(){
        int i = (int)(Math.random() * CandyColor.values().length);
        if (max_fruits1 > 0 && Math.random() < FREQUENCY) {
            max_fruits1--;
            this.setFruit();
            return fruit1;
        }
        if (max_fruits2 > 0 && Math.random() < FREQUENCY) {
            max_fruits2--;
            this.setFruit();
            return fruit2;
        }
        return new Candy(CandyColor.values()[i]);
    }
}
