package com.github.kuhaparkigithub.skilog;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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

    /**
     * Alustaja, joka ottaa ottaa parametrinaan kilometrit, päivämäärän, sijainnin ja kommentit.
     * @param kilometrit
     * @param pvm
     * @param sijainti
     * @param kommentit
     */
    public SkiKilometer(double kilometrit, LocalDate pvm, String sijainti, String kommentit) {
        this.kilometrit = kilometrit;
        this.pvm = pvm;
        this.sijainti = sijainti;
        this.kommentit = kommentit;
    }

    /**
     * Olion kopioiva SkiKilometer alustaja
     */
    public SkiKilometer(SkiKilometer lenkki) {
        this.kilometrit = lenkki.getKilometrit();
        this.pvm = lenkki.getPvm();
        this.sijainti = lenkki.getSijainti();
        this.kommentit = lenkki.getKommentit();
    }

    /**
     * Palauttaa kilometrit
     * @return
     */
    public double getKilometrit() {
        return kilometrit;
    }

    /**
     * Asettaa kilometrit
     * @param kilometrit
     */
    public void setKilometrit(double kilometrit) {
        this.kilometrit = kilometrit;
    }

    /**
     * Palauttaa päivämäärän
     *
     * @return
     */
    public LocalDate getPvm() {
        return pvm;
    }

    /**
     * Asettaa päivämäärän
     * @param pvm
     */
    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }

    /**
     * Palauttaa sijainnin
     * @return
     */
    public String getSijainti() {
        return sijainti;
    }

    /**
     * Asettaa sijainnin
     * @param sijainti
     */
    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    /**
     * Palauttaa kommentit
     * @return
     */
    public String getKommentit() {
        return kommentit;
    }

    /**
     * Asettaa kommentit
     * @param kommentit
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
        if (maara > 0) {
            this.kilometrit += maara;
        }
        else {
            System.out.println("Kilometrimäärän tulee olla positiivinen!");
        }
    }
}
