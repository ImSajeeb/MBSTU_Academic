package com.quiz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


import java.sql.*;
import java.util.*;

public class QuizController {

    @FXML TextField nameField;
    @FXML Label questionLabel, status;
    @FXML RadioButton opt1, opt2, opt3, opt4;
    @FXML VBox namePane, quizPane, resultPane;


    ToggleGroup group = new ToggleGroup();
    List<Question> questions = new ArrayList<>();
    int index = 0;
    int score = 0;

    @FXML
    public void initialize() {
        opt1.setToggleGroup(group);
        opt2.setToggleGroup(group);
        opt3.setToggleGroup(group);
        opt4.setToggleGroup(group);
    }
//    @FXML
//    private VBox namePane;
//
//    @FXML
//    private VBox quizPane;

    @FXML
    public void startQuiz() {

        if (nameField.getText().trim().isEmpty()) {
            status.setText("নাম লিখুন");
            return;
        }

        // 🔁 UI SWITCH
        namePane.setVisible(false);
        quizPane.setVisible(true);

        // 🔁 Reset quiz
        index = 0;
        score = 0;
        questions.clear();

        loadRandomQuestions();
        showQuestion();

        status.setText("Quiz Started");
    }



    void loadRandomQuestions() {
        try (Connection con = DBUtil.getConnection()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 5");

            while (rs.next()) {
                Question q = new Question();
                q.question = rs.getString("question");
                q.o1 = rs.getString("option1");
                q.o2 = rs.getString("option2");
                q.o3 = rs.getString("option3");
                q.o4 = rs.getString("option4");
                q.correct = rs.getInt("correct_option");
                questions.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showQuestion() {

        group.selectToggle(null);
        if (index >= questions.size()) {

            saveScore();

            quizPane.setVisible(false);
            resultPane.setVisible(true);

            status.setText("Quiz Finished! Your Score: " + score);
            return;
        }
        Question q = questions.get(index);
        questionLabel.setText(q.question);
        opt1.setText(q.o1);
        opt2.setText(q.o2);
        opt3.setText(q.o3);
        opt4.setText(q.o4);
    }

    @FXML
    void viewScore() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/quiz/score.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Top Scores");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void nextQuestion() {

        if (group.getSelectedToggle() == null) {
            status.setText("একটি উত্তর নির্বাচন করুন");
            return;
        }

        Question q = questions.get(index);

        if ((opt1.isSelected() && q.correct == 1) ||
                (opt2.isSelected() && q.correct == 2) ||
                (opt3.isSelected() && q.correct == 3) ||
                (opt4.isSelected() && q.correct == 4)) {
            score++;
        }

        index++;
        showQuestion();
    }


    void saveScore() {
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO scores(player_name, score) VALUES (?,?)");
            ps.setString(1, nameField.getText());
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void restart() {
        score = 0;
        index = 0;
        questions.clear();

        resultPane.setVisible(false);
        namePane.setVisible(true);

        nameField.clear();
    }


    @FXML void exit() {
        System.exit(0);
    }
}
