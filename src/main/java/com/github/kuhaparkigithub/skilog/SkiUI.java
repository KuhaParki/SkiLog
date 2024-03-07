package com.github.kuhaparkigithub.skilog;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Tämä luokka on graafinen käyttöliittymä ohjelmalle
 */
public class SkiUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Luodaan BorderPane
        BorderPane borderPane = new BorderPane();

        // Luodaan HBox
        VBox vBox = new VBox(20);

        // TextFieldien ja Labeleitten määrittelyä
        Label kmLabel = new Label("Anna lenkin kilometrit:");
        TextField kmTextField = new TextField();
        kmTextField.setMaxWidth(100);
        Label pvmLabel = new Label("Anna päivämäärä (VVVV-KK-PP)");
        TextField pvmTextField = new TextField();
        pvmTextField.setMaxWidth(100);
        Label sijaintiLabel = new Label("Anna hiihtolenkin sijainti");
        TextField sijaintiTextField = new TextField();
        sijaintiTextField.setMaxWidth(100);
        Label kommentitLabel = new Label("Kommentit hiihtolenkistä");
        TextArea kommentitTextArea = new TextArea();
        kommentitTextArea.setMaxWidth(200);

        // vBoxin määrittelyä
        vBox.getChildren().addAll(kmLabel, kmTextField, pvmLabel, pvmTextField, sijaintiLabel, sijaintiTextField,
                kommentitLabel, kommentitTextArea);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER_RIGHT);

        // HBoxin luominen
        HBox hBox = new HBox(20);
        Button uusiLenkki = new Button("Syötä uusi lenkki");
        hBox.getChildren().add(uusiLenkki);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        // Toiminnallisuus uusiLenkki-Buttonille
        uusiLenkki.setOnAction(e -> {

        });

        // TextArean luominen
        TextArea tekstiAlueKeskelle = new TextArea();
        tekstiAlueKeskelle.setMaxSize(500, 800);

        // Luodaan näkymä eri hiihtolenkeille ohjelmaan. Samankaltainen ratkaisu löytyy viikon 7 tehtävästä 6


        ArrayList<String> lenkkiID = new ArrayList<String>();
        for (int i = 0; i < new SkiKilometerMain().lenkkienMaara; i++) {
            if (new SkiKilometerMain().lenkit[i].getPvm() != null) {
                lenkkiID.add("Lenkki: " + new SkiKilometerMain().lenkit[i].getPvm());
            }
            else {
                lenkkiID.add("Lenkki: " + i);
            }
        }

        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(lenkkiID));

        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
            for (String str : lv.getSelectionModel().getSelectedItems()) {

            }
        });

        // Osien asettelua BorderPaneen
        borderPane.setCenter(tekstiAlueKeskelle);
        borderPane.setLeft(lv);
        borderPane.setRight(vBox);
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 1000, 1000);
        primaryStage.setTitle("Hiihtokilometrisovellus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


