package com.github.kuhaparkigithub.skilog;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Tämä on olioluokka hiihtokilometriohjelmaa varten.
 */
public class SkiKilometer implements Serializable {

    /**
     * kilometrit desimaalilukuna
     */
    private double kilometrit;

    /**
     * Päivämäärä LocalDate-muodossa. LocalDate-luokan käyttöä tutkittu seuraavalla nettisivulla:
     * https://www.w3schools.com/java/java_date.asp
     */
    private LocalDate pvm;

    /**
     * Sijainti merkkijonona
     */
    private String sijainti;

    /**
     * Kommentit lenkistä merkkijonona
     */
    private String kommentit;

    private int totalKm;


    /**
     * Alustaja, joka ottaa ottaa parametrinaan kilometrit, päivämäärän, sijainnin ja kommentit.
     * @param kilometrit desimaalilukuarvo, joka kuvastaa kilometrien määrää
     * @param pvm LocalDate-luokan olio, joka kuvastaa päivämäärää
     * @param sijainti Merkkijono, joka kuvastaa hiihtolenkin sijaintia
     * @param kommentit Merkkijono, joka kuvastaa kommenttejä hiihtolenkistä
     */
    public SkiKilometer(double kilometrit, LocalDate pvm, String sijainti, String kommentit) {
        this.kilometrit = kilometrit;
        this.pvm = pvm;
        this.sijainti = sijainti;
        this.kommentit = kommentit;
    }


    /**
     * Olion kopioiva alustaja
     * @param lenkki SkiKilometer-tyyppinen olio
     */
    public SkiKilometer(SkiKilometer lenkki) {
        this.kilometrit = lenkki.getKilometrit();
        this.pvm = lenkki.getPvm();
        this.sijainti = lenkki.getSijainti();
        this.kommentit = lenkki.getKommentit();
    }


    /**
     * Palauttaa kilometrit
     * @return palauttaa desimaaliluvun, joka kuvastaa kilometrejä
     */
    public double getKilometrit() {
        return kilometrit;
    }


    /**
     * Asettaa kilometrit
     * @param kilometrit desimaaliluku, joka kuvastaa kilometrejä
     */
    public void setKilometrit(double kilometrit) {
        this.kilometrit = kilometrit;
    }


    /**
     * Palauttaa päivämäärän
     * @return Palauttaa LocalDate-tyyppisen olion, joka kuvastaa päivämäärää
     */
    public LocalDate getPvm() {
        return pvm;
    }


    /**
     * Asettaa päivämäärän
     * @param pvm LocalDate-tyyppinen olio, joka kuvastaa päivämäärää
     */
    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }

    /**
     * Palauttaa sijainnin
     * @return Palauttaa merkkijonon, joka kuvastaa hiihtolenkin sijaintia
     */
    public String getSijainti() {
        return sijainti;
    }

    /**
     * Asettaa sijainnin
     * @param sijainti Merkkijono, joka kuvastaa hiihtolenkin sijaintia
     */
    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    /**
     * Palauttaa kommentit
     * @return Palauttaa merkkijonon, joka kuvastaa kommentteja
     */
    public String getKommentit() {
        return kommentit;
    }

    /**
     * Asettaa kommentit
     * @param kommentit Merkkijono, joka kuvastaa hiihtolenkistä annettuna kommentteja
     */
    public void setKommentit(String kommentit) {
        this.kommentit = kommentit;
    }


    /**
     * Metodi laskee kokonaisuudessaan hiihdetyt kilometrit yhteen. Samankaltainen metodi on Pankkitililuokan
     * talleta-metodi.
     * @param maara asetetut hiihtokilometrit desimaalilukuna
     */
    public void lisaaKilometriSummaan(double maara) {
            this.totalKm += maara;
    }
}
