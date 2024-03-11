package com.github.kuhaparkigithub.skilog;

/*
Lisättäviä ominaisuuksia:
- Automaagisesti päivittyvä ListView
- Kokoomatiedot -- tehty
- Lenkin poistaminen -- tehty
- Aseta-Buttoniin poistumisominaisuus -- tehty
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Tämä luokka on graafinen käyttöliittymä ohjelmalle
 */
public class SkiUI extends Application {

    /**
     * start-metodi aloittaa käyttöliittymän graafisten komponenttien näyttämisen
     * @param primaryStage Ottaa parametrinaan primaryStagen, joka toimii pohjana kaikelle
     */
    @Override
    public void start(Stage primaryStage) {
        // Luodaan BorderPane
        BorderPane borderPane = new BorderPane();

        // HBoxin luominen
        HBox hBox = new HBox(20);
        Button uusiLenkki = new Button("Syötä uusi lenkki");
        Button poistaLenkki = new Button("Poista lenkki");
        Button yhteenveto = new Button("Yhteenveto");
        hBox.getChildren().addAll(uusiLenkki, poistaLenkki, yhteenveto);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        // TextArean luominen
        TextArea tekstiAlueKeskelle = new TextArea();
        tekstiAlueKeskelle.setMaxSize(500, 800);

        // Luodaan näkymä eri hiihtolenkeille ohjelmaan. Mallin ObservableListiin katsoin Javan manuaalista seuraavasta
        // linkistä: https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/ListView.html

        ObservableList<String> lenkkiID = FXCollections.observableArrayList();
        for (int i = 0; i < new SkiKilometerMain().lenkit.size(); i++) {
            lenkkiID.add(i, "Lenkki " + (i + 1));
        }

        ListView<String> lv = new ListView<String>(lenkkiID);

        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
            for (String str : lv.getSelectionModel().getSelectedItems()) {
                for (int i = 0; i < lenkkiID.size(); i++) {
                    if(Objects.equals(str, ("Lenkki " + (i + 1)))){
                        tekstiAlueKeskelle.setText(new SkiKilometerMain().lenkinTiedot(i));
                        int finalI = i;
                        poistaLenkki.setOnAction(eve -> {
                            new SkiKilometerMain().poistaLenkki(finalI);
                            lenkkiID.remove("Lenkki " + (finalI + 1));
                        });
                    }
                }
            }
        });

        // uusiLenkki-Buttonin toiminnallisuus. Katsottu uuden ikkunan tekemiseen vinkkiä sivustolta:
        // https://stackoverflow.com/questions/15041760/javafx-open-new-window
        uusiLenkki.setOnAction(e -> {
            Stage stage = new Stage();

            BorderPane borderPane1 = new BorderPane();
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

            // aseta-Buttonin toiminnallisuus
            Button aseta = new Button("Aseta tiedot");
            aseta.setOnAction(event -> {
                new SkiKilometerMain().uusiLenkkiListaan(new SkiKilometer(Double.parseDouble(kmTextField.getText()),
                        LocalDate.parse(pvmTextField.getText()), sijaintiTextField.getText(), kommentitTextArea.getText()));
                lenkkiID.addLast("Lenkki " + (new SkiKilometerMain().lenkit.size()));
                BorderPane borderPane3 = new BorderPane();
                Label asetusOnnistui = new Label("Asetus onnistui, voit sulkea ikkunan");
                borderPane3.setCenter(asetusOnnistui);
                Scene scene = new Scene(borderPane3, 500, 500);
                stage.setScene(scene);
                stage.setTitle("Asetus onnistui!");
                stage.show();
            });

            // Syötetään borderPane1lle aseta-Button ja vBox
            borderPane1.setCenter(aseta);
            borderPane1.setRight(vBox);

            // Määritellään uuden ikkunan avautuminen
            stage.setTitle("Hiihtolenkin tiedot");
            stage.setScene(new Scene(borderPane1, 500, 500));
            stage.show();
        });

        // yhteenveto-Buttonin toiminnallisuus. Näyttää uudessa ikkunassa kilometrit yhteensä, lenkkien määrän sekä
        // keskimääräisen lenkin pituuden
        yhteenveto.setOnAction(e -> {
            BorderPane borderPane2 = new BorderPane();
            double total = 0;
            for (int i = 0; i < new SkiKilometerMain().lenkit.size(); i++) {
                total += new SkiKilometerMain().lenkit.get(i).getKilometrit();
            }

            DecimalFormat f = new DecimalFormat("##.00");
            Label tiedot = new Label("Kilometrit yhteensä: " + total + "km\nLenkkejä yhteensä: " +
                    new SkiKilometerMain().lenkit.size() + "\nKeskimääräinen lenkin pituus: " + 
                    f.format(total / new SkiKilometerMain().lenkit.size()) + "km");

            borderPane2.setCenter(tiedot);

            Stage stage = new Stage();
            Scene scene = new Scene(borderPane2, 300, 300);
            stage.setTitle("Yhteenveto lenkeistä");
            stage.setScene(scene);
            stage.show();
        });



        // Osien asettelua BorderPaneen
        borderPane.setCenter(tekstiAlueKeskelle);
        borderPane.setLeft(lv);
        borderPane.setTop(hBox);

        // Ikkunan määrittelyä
        Scene scene = new Scene(borderPane, 1000, 1000);
        primaryStage.setTitle("Hiihtokilometrisovellus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Pääohjelma graafiselle käyttöliittymälle
     * @param args Vakioargumentti
     */
    public static void main(String[] args) {
        launch(args);
    }
}


