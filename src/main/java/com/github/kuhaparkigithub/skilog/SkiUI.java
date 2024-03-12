package com.github.kuhaparkigithub.skilog;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Tämä luokka on graafinen käyttöliittymä ohjelmalle
 */
public class SkiUI extends Application {

    /**
     * start-metodi aloittaa käyttöliittymän graafisten komponenttien näyttämisen. ListViewin yhteydessä käytetty
     * poikkeustenkäsittelyä. Poikkeustenkäsittelyä voi odottaa, jos asettaa päivämäärän tai kilometrit väärässä muodossa.
     * @param primaryStage Ottaa parametrinaan primaryStagen, joka toimii pohjana kaikelle
     */
    @Override
    public void start(Stage primaryStage) {
        // Luodaan BorderPane
        BorderPane borderPane = new BorderPane();


        // HBoxin luominen. Asetetaan HBoxiin kolme Buttonia, joilla voi syöttää uuden lenkin, poistaa lenkin ja
        // saada yhteenvedon lenkeistä
        HBox hBox = new HBox(20);
        Button uusiLenkki = new Button("Syötä uusi lenkki");
        Button poistaLenkki = new Button("Poista lenkki");
        Button yhteenveto = new Button("Yhteenveto");
        hBox.getChildren().addAll(uusiLenkki, poistaLenkki, yhteenveto);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));


        // TextArean luominen. Tässä näytetään tiedot hiihtolenkistä keskellä näyttöä. Se on asetettu niin, ettei sitä
        // voi muokata graafisesta käyttöliittymästä käsin
        TextArea tekstiAlueKeskelle = new TextArea();
        tekstiAlueKeskelle.setEditable(false);
        tekstiAlueKeskelle.setMaxSize(500, 800);


        // Luodaan näkymä eri hiihtolenkeille ohjelmaan. Mallin ObservableListiin katsoin Javan manuaalista seuraavasta
        // linkistä: https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/ListView.html
        ObservableList<String> lenkkiID = FXCollections.observableArrayList();
        for (int i = 0; i < new SkiKilometerFile().lenkit.size(); i++) {
            lenkkiID.add(i, "Lenkki " + (i + 1));
        }

        // Asetetaan ListView olioon ObservableList, joka on ylempänä määritelty. Näytetään lista ohjelmassa niin, että
        // kun lenkkiID:n elementti valitaan ListViewistä, niin näytetään samassa indeksissä olevan lenkin tiedot
        // lenkit-ArrayLististä. Jos lenkki on valitty, se voidaan sitten myös poistaa poistaLenkki-Buttonilla, jonka
        // toiminnallisuus on määritelty kuuntelijan sisällä.
        ListView<String> lv = new ListView<String>(lenkkiID);
        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
            for (String str : lv.getSelectionModel().getSelectedItems()) {
                for (int i = 0; i < lenkkiID.size(); i++) {
                    if(Objects.equals(str, ("Lenkki " + (i + 1)))){
                        tekstiAlueKeskelle.setText(new SkiKilometerFile().lenkinTiedot(i));
                        int finalI = i;
                        poistaLenkki.setOnAction(eve -> {
                            new SkiKilometerFile().poistaLenkki(finalI);
                            lenkkiID.remove("Lenkki " + (finalI + 1));
                        });
                    }
                }
            }
        });


        // uusiLenkki-Buttonin toiminnallisuus. Katsottu uuden ikkunan tekemiseen vinkkiä sivustolta:
        // https://stackoverflow.com/questions/15041760/javafx-open-new-window
        // Kun uusiLenkki-Buttonia painetaan, ohjelma antaa käyttäjälle uuden ikkunan, jossa tiedot voi asettaa.
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


            // aseta-Buttonin toiminnallisuus. aseta-Buttonilla voi asettaa hiihtolenkin tiedot ohjelmaan, mutta vain
            // jos kilometritieto on numeraalinen, päivämäärä on syötetty oikein ja kaikkiin kenttiin on asetettu tietoa.
            // Kun asettaminen on onnistuneesti tehty, ohjelma laittaa ikkunaan tekstin: "Asetus onnistui, voit sulkea ikkunan"
            Button aseta = new Button("Aseta tiedot");
            aseta.setOnAction(event -> {
                try {
                    if (Objects.equals(kmTextField.getText(), "") || Objects.equals(pvmTextField.getText(), "") ||
                            Objects.equals(sijaintiTextField.getText(), "") ||
                            Objects.equals(kommentitTextArea.getText(), "")) {
                        Label virhe = new Label("Kaikkiin kenttiin tulee syöttää tietoa!");
                        virhe.setFont(Font.font(20));
                        virhe.setTextFill(Color.RED);
                        borderPane1.setBottom(virhe);
                    }
                    else if (Double.parseDouble(kmTextField.getText()) < 0) {
                        Label virhe = new Label("Kilometritiedon tulee olla positiivinen luku!");
                        virhe.setFont(Font.font(20));
                        virhe.setTextFill(Color.RED);
                        borderPane1.setBottom(virhe);
                    }
                    else {
                        new SkiKilometerFile().uusiLenkkiListaan(new SkiKilometer(Double.parseDouble(kmTextField.getText()),
                                LocalDate.parse(pvmTextField.getText()), sijaintiTextField.getText(), kommentitTextArea.getText()));
                        lenkkiID.addLast("Lenkki " + (new SkiKilometerFile().lenkit.size()));
                        BorderPane borderPane3 = new BorderPane();
                        Label asetusOnnistui = new Label("Asetus onnistui, voit sulkea ikkunan");
                        borderPane3.setCenter(asetusOnnistui);
                        Scene scene = new Scene(borderPane3, 500, 500);
                        stage.setScene(scene);
                        stage.setTitle("Asetus onnistui!");
                        stage.show();
                    }
                }
                catch (NumberFormatException ex) {
                    Label intVirhe = new Label("Kilometrien tulee olla numeraalinen!");
                    intVirhe.setFont(Font.font(20));
                    intVirhe.setTextFill(Color.RED);
                    borderPane1.setBottom(intVirhe);
                }
                catch (DateTimeParseException exception) {
                    Label intVirhe = new Label("Tarkista päivämäärän muotoilu! (VVVV-KK-PP)");
                    intVirhe.setFont(Font.font(20));
                    intVirhe.setTextFill(Color.RED);
                    borderPane1.setBottom(intVirhe);
                }
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
            for (int i = 0; i < new SkiKilometerFile().lenkit.size(); i++) {
                total += new SkiKilometerFile().lenkit.get(i).getKilometrit();
            }

            DecimalFormat f = new DecimalFormat("##.00");
            Label tiedot = new Label("Kilometrit yhteensä: " + total + "km\nLenkkejä yhteensä: " +
                    new SkiKilometerFile().lenkit.size() + "\nKeskimääräinen lenkin pituus: " +
                    f.format(total / new SkiKilometerFile().lenkit.size()) + "km");

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
     * Main-metodi graafiselle käyttöliittymälle
     * @param args Vakioargumentti
     */
    public static void main(String[] args) {
        launch(args);
    }
}


