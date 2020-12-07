package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
	private ResultsPanel scorePanel, movesPanel, specialCellsPanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game) {
		this.game = game;
		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ResultsPanel("Score: ");
		getChildren().add(scorePanel);
		movesPanel = new ResultsPanel("Moviemientos restantes: ");
		getChildren().add(movesPanel);
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
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null, false, false)));
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
						if (game().playerWon()) {
							score = score + " Finished - Player Won!";
							display("¡Ganaste!");
						} else {
							score = score + " Finished - Loser !";
							display("¡Perdiste!");
						}
					}
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

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}
	
	public void display(String message) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("GAME OVER");
		Label label1= new Label(message);
		Button button1= new Button("Salir del juego.");
		button1.setOnAction(e -> Platform.exit());
		VBox layout= new VBox(10);
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
		Scene scene1= new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

}
