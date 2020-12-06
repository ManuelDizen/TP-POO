package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ResultsPanel extends BorderPane {
    private Label label;
    private String text;

    public ResultsPanel(String text) {
        label = new Label(text);
        this.text = text;
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 18");
        setBottom(label);
        setStyle("-fx-background-color: #5490ff");
    }
    public void updateLabel(String value) {
        label.setText(text + value);
    }
}
