package com.example.enigma_machine_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class EnigmaMachineApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnigmaMachineApplication.class.getResource("enigmamachine-view.fxml"));

        // Dimensioni schermo dispositivo
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Dimensioni della finestra
        double windowWidth = screenWidth * 0.8;
        double windowHeight = screenHeight * 0.91;

        Scene scene = new Scene(fxmlLoader.load(), windowWidth, windowHeight);

        stage.setTitle("Enigma Machine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}