package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MovesPanel extends BorderPane {
    private Label movesLabel;
    private static final String MAX_MOVES = "20";

    public MovesPanel() {
        movesLabel = new Label();
        setLabel(MAX_MOVES);
        movesLabel.setAlignment(Pos.CENTER_LEFT);
        movesLabel.setStyle("-fx-font-size: 18");
        setBottom(movesLabel);
        setStyle("-fx-background-color: #5490ff");
    }
    public void updateMoves(String text) {
        setLabel(text);
    }

    public void setLabel(String text){
        movesLabel.setText("Movimientos restantes: " + text);
    }
}
