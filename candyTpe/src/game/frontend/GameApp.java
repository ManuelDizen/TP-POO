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
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class GameApp extends Application {
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		//Creamos un choiceBox para que el usuario decida a qué nivel jugar
		ChoiceBox<String> levels = new ChoiceBox<>();
		//Añadimos los niveles
		levels.getItems().addAll("Básico","Golden Board", "Wall Blast");
		//Creamos el boton jugar
		Button button = new Button("Jugar");
		//Cuando se apriete el botón, se obtiene el valor del choiceBox
		button.setOnAction(e -> levelSelection(levels.getValue()));
		//Ponemos un título antes del choiceBox
		Label title = new Label("Seleccione un nivel:   ");
		//Ponemos el titulo, el choiceBox y el botón en formato horizontal
		HBox menu = new HBox(title, levels, button);
		//Usamos un BorderPane para mostrar el menú
		Pane display = new BorderPane(menu);
		//Seteamos el escenario con los elementos de antes
		stage.setScene(new Scene(display));

		stage.setResizable(false);
		stage.show();
	}
	
	public void restart(Stage primaryStage) {
		start(primaryStage);	
	}
	
	public Stage getStage() {
		return Stage;	
	}
	
	//Recibe la opción del choiceBox e inicia el nivel correspondiente
	private void levelSelection(String level){
		Class selectedLevel;
		if (level.equals("Básico"))
			selectedLevel = Level1.class;
		else if (level.equals("Golden Board"))
			selectedLevel = Level2.class;
		else
			selectedLevel = Level3.class;

		CandyGame game = new CandyGame(selectedLevel);
		CandyFrame frame = new CandyFrame(game);
		frame.addApp(this);
		stage.setScene(new Scene(frame));
	}

}
