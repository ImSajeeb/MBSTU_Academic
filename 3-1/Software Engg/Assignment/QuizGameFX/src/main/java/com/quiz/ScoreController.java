package com.quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;

public class ScoreController {

    @FXML TableView<Score> tableView;
    @FXML TableColumn<Score, String> nameCol;
    @FXML TableColumn<Score, Integer> scoreCol;

    ObservableList<Score> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        loadScores();
    }

    void loadScores() {
        try (Connection con = DBUtil.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT player_name, score FROM scores " +
                            "ORDER BY score DESC LIMIT 5");

            while (rs.next()) {
                list.add(new Score(
                        rs.getString("player_name"),
                        rs.getInt("score")
                ));
            }
            tableView.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void close() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
    }
}
