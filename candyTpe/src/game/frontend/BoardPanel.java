package game.frontend;

import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;


public class BoardPanel extends TilePane {

	private ImageView[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
		this.cells = new ImageView[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new ImageView();
				getChildren().add(cells[i][j]);
			}
		}
	}
	
	//Agregamos dos booleanos para ver si hay que setear el efecto de golden o de wallBlast
	public void setImage(int row, int column, Image image, boolean golden, boolean wallBlast) {
		//Si hay que setear en golden, llamo a setColor con YELLOW
		if (golden)
			setColor(row, column, Color.YELLOW);
		//Si hay que setear en wallBlast, llamo a setColor con SANDYBROWN
		else if (wallBlast)
			setColor(row, column, Color.SANDYBROWN);
		//Si no hay que setear nada, seteo un efecto neutro para eliminar si la celda ya tenía un efecto anterior
		else
			cells[row][column].setEffect(new ColorAdjust());
		cells[row][column].setImage(image);
	}
	
	//Definimos el método setColor para modularizar
	private void setColor(int row, int column, Color color){
		Light.Distant spotLight = new Light.Distant();
		spotLight.setColor(color);
		spotLight.setElevation(100);
		Lighting lighting = new Lighting(spotLight);
		cells[row][column].setEffect(lighting);
	}

}
