package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class guiController {

    @FXML
    private ColorPicker colorChosen;

    @FXML
    private TextField nameChosen;

    @FXML
    private Pane s1Box;

    @FXML
    private Text s1Name;

    @FXML
    private Pane s2Box;

    @FXML
    private Text s2Name;

    @FXML
    private Pane s3Box;

    @FXML
    private Text s3Name;

    @FXML
    private Pane s4Box;

    @FXML
    private Text s4Name;

    @FXML
    private Pane s5Box;

    @FXML
    private Text s5Name;

    @FXML
    private Pane s6Box;

    @FXML
    private Text s6Name;

    @FXML
    private Pane s7Box;

    @FXML
    private Text s7Name;

    @FXML
    private Pane s8Box;

    @FXML
    private Text s8Name;

    @FXML
    private Pane s9Box;

    @FXML
    private Text s9Name;

    @FXML
    private Label showError;

    private String[] std_name = new String[9];
    private Color[] chsn_clr = new Color[9];

    @FXML
    void addStudentButton(ActionEvent event) {
        String inputed_name = nameChosen.getText().trim().toLowerCase();
        Color inputed_color = colorChosen.getValue();

        if (belongsToData(std_name, inputed_name)) {
            showError.setText("Same student exist.");
            showError.setStyle("-fx-text-fill: red");
            return;
        }

        if (inputed_name.contains(" ")) {
            showError.setText("Space in your name.");
            showError.setStyle("-fx-text-fill: red");
            return;

        }

        if (inputed_name.length() < 1) {
            showError.setText("Name field is empty.");
            showError.setStyle("-fx-text-fill: red");
            return;

        }

        if (inputed_name.length() < 2) {
            showError.setText("Name must be more than one character.");
            showError.setStyle("-fx-text-fill: red");
            return;
        }


        if (inputed_color.equals(Color.WHITE)) {
            showError.setText("You can not select white color.");
            showError.setStyle("-fx-text-fill: red");
            return;

        }

        if (belongsToData(chsn_clr, inputed_color)) {
            showError.setText("Color exists.");
            showError.setStyle("-fx-text-fill: red");
            return;

        }

        int index_no = emptySeatAvailable();
        if (index_no != -1) {
            Pane[] box_student = {s1Box, s2Box, s3Box, s4Box, s5Box, s6Box, s7Box, s8Box, s9Box};
            Text[] txt_inputed_student = {s1Name, s2Name, s3Name, s4Name, s5Name, s6Name, s7Name, s8Name, s9Name};

            box_student[index_no].setStyle("-fx-background-color: #" + inputed_color.toString().substring(2));
            txt_inputed_student[index_no].setText(inputed_name);
            txt_inputed_student[index_no].setVisible(true);

            std_name[index_no] = inputed_name;
            chsn_clr[index_no] = inputed_color;

            showError.setText("Added successfully!");
            showError.setStyle("-fx-text-fill: white");


        } else {
            showError.setText("No seats Available.");
            showError.setStyle("-fx-text-fill: yellow");

        }
    }

    private int emptySeatAvailable() {
        List<Integer> seats_remain = new ArrayList<>();
        for (int i = 0; i < std_name.length; i++) {
            if (std_name[i] == null) {
                seats_remain.add(i);
            }
        }
        if (seats_remain.isEmpty()) {
            return -1;
        }
        Random ra = new Random();
        return seats_remain.get(ra.nextInt(seats_remain.size()));
    }

    private boolean belongsToData(Object[] array, Object obj) {
        for (Object element : array) {
            if (element != null && element.equals(obj)) {
                return true;
            }
        }
        return false;
    }


}