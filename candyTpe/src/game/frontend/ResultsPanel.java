package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

//Creamos la clase ResultsPanel para mostrar los resultados en la parte inferior del juego
public class ResultsPanel extends BorderPane {
    private Label label;
    private String text;

    public ResultsPanel(String text) {
        //Seteamos el titulo con el texto que recibimos por parámetro
        label = new Label(text);
        //Guardamos el título en una variable
        this.text = text;
        //Seteamos la posición y el tamaño
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 18");
        setBottom(label);
        setStyle("-fx-background-color: #5490ff");
    }
    
    //Definimos el método updateLabel que cambia el texto de la etiqueta con el título original y el resultado
    public void updateLabel(String value) {
        label.setText(text + value);
    }
}
