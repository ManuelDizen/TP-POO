package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class GameApp extends Application {
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;

		ChoiceBox<String> levels = new ChoiceBox<>();
		levels.getItems().addAll("Nivel 1","Nivel 2", "Nivel 3");
		Button button = new Button("Jugar");
		button.setOnAction(e -> levelSelection(levels.getValue()));

		Label title = new Label("Seleccione un nivel:   ");
		HBox menu = new HBox(title, levels, button);

		StackPane stackScreen = new StackPane(menu);
		stage.setScene(new Scene(stackScreen));

		stage.setResizable(false);
		stage.show();
	}
	
	private void levelSelection(String level){
		Class selectedLevel;
		if (level.equals("Nivel 1"))
			selectedLevel = Level1.class;
		else if (level.equals("Nivel 2"))
			selectedLevel = Level2.class;
		else
			selectedLevel = Level3.class;

		CandyGame game = new CandyGame(selectedLevel);
		stage.setScene(new Scene(new CandyFrame(game)));
	}

}
