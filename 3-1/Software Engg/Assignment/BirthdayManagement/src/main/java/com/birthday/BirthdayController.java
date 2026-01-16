package com.birthday;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;


import java.sql.*;
import java.time.LocalDate;

public class BirthdayController {

    @FXML private TextField nameField, searchField;
    @FXML private DatePicker datePicker;
    @FXML private Label statusLabel;
    @FXML private TableView<Classmate> tableView;
    @FXML private TableColumn<Classmate, String> nameCol;
    @FXML private TableColumn<Classmate, Object> dateCol;

    private ObservableList<Classmate> data =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameCol.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getName()));

        dateCol.setCellValueFactory(
                c -> new SimpleObjectProperty<>(c.getValue().getBirthdate()));

        tableView.setItems(data);
        loadFromDatabase();
        checkTodayBirthday();
    }

    // 🔹 Load + Upcoming Sort
    private void loadFromDatabase() {
        data.clear();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(
                    "SELECT * FROM classmates " +
                            "ORDER BY MONTH(birthday), DAY(birthday)");

            while (rs.next()) {
                data.add(new Classmate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("❌ ডাটাবেস লোড ব্যর্থ");
        }
    }

    // 🔹 Add
    @FXML
    void addBirthday() {
        if (nameField.getText().isEmpty()
                || datePicker.getValue() == null) {
            statusLabel.setText("⚠️ সব তথ্য দিন");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO classmates(name, birthday) VALUES (?, ?)");

            ps.setString(1, nameField.getText());
            ps.setDate(2, Date.valueOf(datePicker.getValue()));
            ps.executeUpdate();

            statusLabel.setText("✔ জন্মদিন সংরক্ষণ হয়েছে");
            loadFromDatabase();
            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("❌ সংরক্ষণ ব্যর্থ");
        }
    }

    // 🔹 Delete
    @FXML
    void deleteBirthday() {
        Classmate c =
                tableView.getSelectionModel().getSelectedItem();

        if (c == null) {
            statusLabel.setText("⚠️ একটি নাম নির্বাচন করুন");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM classmates WHERE id=?");

            ps.setInt(1, c.getId());
            ps.executeUpdate();

            statusLabel.setText("🗑️ মুছে ফেলা হয়েছে");
            loadFromDatabase();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("❌ মুছে ফেলা ব্যর্থ");
        }
    }

    // 🔹 Search (Name or Month)
    @FXML
    void search() {
        String key = searchField.getText().trim();

        FilteredList<Classmate> filtered =
                new FilteredList<>(data, c ->
                        c.getName().contains(key) ||
                                String.valueOf(
                                        c.getBirthdate().getMonthValue()
                                ).equals(key));

        tableView.setItems(filtered);
    }

    // 🔹 Today Birthday Notification
    private void checkTodayBirthday() {
        LocalDate today = LocalDate.now();

        for (Classmate c : data) {
            if (c.getBirthdate().getMonth() == today.getMonth()
                    && c.getBirthdate().getDayOfMonth()
                    == today.getDayOfMonth()) {

                statusLabel.setText(
                        "🎉 আজ " + c.getName() + " এর জন্মদিন!");
                return;
            }
        }
    }

    @FXML
    void clearForm() {
        nameField.clear();
        datePicker.setValue(null);
    }
}
