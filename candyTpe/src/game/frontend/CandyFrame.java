package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	//Agregamos los resultsPanel para mostrar en la parte inferior del juego los resultados parciales
	private ResultsPanel scorePanel, movesPanel, specialCellsPanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;
	private GameApp app;

	public CandyFrame(CandyGame game) {
		this.game = game;
		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		//Seteo el panel de Score
		scorePanel = new ResultsPanel("Score: ");
		getChildren().add(scorePanel);
		//Seteo el panel de Movimientos Restantes
		movesPanel = new ResultsPanel("Moviemientos restantes: ");
		getChildren().add(movesPanel);
		//Seteo el panel de Celdas especiales restantes
		specialCellsPanel = new ResultsPanel("Celdas especiales restantes: ");
		getChildren().add(specialCellsPanel);
		game.initGame();
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						//Agregamos los últimos dos booleanos en falso
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null, false, false)));
						//Llamamos a los métodos isGolden e isWallBlast para setear los booleanos y ver si hay que agregar el efecto o no
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image, cell.isGolden(), cell.isWallBlast())));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					String score = game().getScore().toString();
					String moves = game().getMovesLeft().toString();
					String specialCells = game().getSpecialCellsLeft().toString();
					if (game().isFinished()) {
						String message;
						if (game().playerWon()) {
							score = score + " Finished - Player Won!";
							message = "¡Ganaste!";
						} else {
							score = score + " Finished - Loser !";
							message = "¡Perdiste!";
						}
						//Llamamos al método display para terminar el juego
						display(message);
					}
					//Updateamos los resultados
					scorePanel.updateLabel(score);
					movesPanel.updateLabel(moves);
					specialCellsPanel.updateLabel(specialCells);
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}
	
	public void addApp(GameApp app) {
		this.app = app;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}
	
	//Definimos el método display para mostrar un popUp cuando termina el juego para que el usuario no pueda seguir jugando.
	public void display(String message) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("GAME OVER");
		Label label1= new Label(message);
		Button button1= new Button("Salir del juego.");
		button1.setOnAction(e -> Platform.exit());
		Button button2= new Button("Volver a jugar");
		EventHandler<ActionEvent> event = e -> {
			app.restart(app.getStage());
			popupwindow.close();
		};
		button2.setOnAction(event);
		VBox layout= new VBox(10);
		layout.getChildren().addAll(label1, button1, button2);
		layout.setAlignment(Pos.CENTER);
		Scene scene1= new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}
	

}
