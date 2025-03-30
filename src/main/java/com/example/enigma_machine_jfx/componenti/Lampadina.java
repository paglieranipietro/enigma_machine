package com.example.enigma_machine_jfx.componenti;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.effect.Glow;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

/**
 * La classe Lampadina rappresenta una lampadina grafica che può essere accesa e spenta.
 * Estende StackPane per poter contenere un cerchio e una lettera contemporaneamente.
 */
public class Lampadina extends StackPane {
    private final Circle cerchio;
    private final Label lettera;
    private Timeline animazione;

    /**
     * Costruttore della classe Lampadina.
     *
     * @param simbolo il carattere da visualizzare all'interno della lampadina
     */
    public Lampadina(char simbolo) {
        this.cerchio = new Circle(25, Color.GRAY);
        this.lettera = new Label(String.valueOf(simbolo));
        this.lettera.setFont(Font.font(18));
        this.lettera.setTextFill(Color.BLACK);

        getChildren().addAll(cerchio, this.lettera);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));

        // Inizializza l'animazione una sola volta
        animazione = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(cerchio.fillProperty(), Color.ORANGE)),
                new KeyFrame(Duration.millis(100), new KeyValue(cerchio.fillProperty(), Color.YELLOW)),
                new KeyFrame(Duration.millis(200), new KeyValue(cerchio.fillProperty(), Color.GOLD))
        );
        animazione.setCycleCount(1);
    }

    /**
     * Accende la lampadina, cambiando il colore del cerchio e applicando un effetto di bagliore.
     * Interrompe qualsiasi animazione in corso e avvia una nuova animazione.
     */
    public void accendi() {
        // Interrompi qualsiasi animazione in corso
        animazione.stop();
        cerchio.setFill(Color.YELLOW);
        cerchio.setEffect(new Glow(0.8));
        animazione.play();
    }

    /**
     * Spegne la lampadina, ripristinando il colore del cerchio a grigio e rimuovendo qualsiasi effetto.
     * Interrompe qualsiasi animazione in corso.
     */
    public void spegni() {
        animazione.stop();
        cerchio.setFill(Color.GRAY);
        cerchio.setEffect(null);
    }
}