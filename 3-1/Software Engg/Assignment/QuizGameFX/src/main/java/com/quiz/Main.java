package com.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/quiz/quiz.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Quiz Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
