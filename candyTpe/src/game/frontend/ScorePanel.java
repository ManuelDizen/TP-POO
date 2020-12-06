package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel;

	public ScorePanel() {
		scoreLabel = new Label();
		setLabel("0");
		scoreLabel.setAlignment(Pos.CENTER_LEFT);
		scoreLabel.setStyle("-fx-font-size: 24");
		setBottom(scoreLabel);
		setStyle("-fx-background-color: #5490ff");
	}
	
	public void updateScore(String text) {
		scoreLabel.setText(text);
	}
	
	public void setLabel(String text){
		scoreLabel.setText("Score: " + text);
	}

}
