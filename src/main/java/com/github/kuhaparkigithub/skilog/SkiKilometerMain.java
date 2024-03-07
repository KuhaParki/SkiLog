package com.github.kuhaparkigithub.skilog;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tässä luokassa on ohjelman toiminnallisuus määritelty, sekä pääohjelma.
 */
public class SkiKilometerMain {
    public int lenkkienMaara = 1;
    /**
     * Kenttänä SkiKilometer luokan taulukkotyyppinen olio. Samanlainen ratkaisu löytyy Pankkiautomaattiluokasta.
     */
    public SkiKilometer[] lenkit = new SkiKilometer[lenkkienMaara];

    //public ArrayList<SkiKilometer> lenkit= new ArrayList<>();
    /**
     * Alustaja, jossa on mukana tiedoston lukeminen. Samankaltainen ratkaisu löytyy Pankkiautomaattiluokasta
     */
    public SkiKilometerMain() {
        try {
            File file = new File("lenkit.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream("lenkit.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                for (int i = 0; i < lenkkienMaara; i++) {
                    lenkit[i] = (SkiKilometer) ois.readObject();
                }
                fis.close();
            }
            else {
                for (int i = 0; i < lenkkienMaara; i++) {
                    lenkit[i] = new SkiKilometer(0, LocalDate.now(), "Tähän kirjoitetaan sijainti",
                            "Tähän kirjoitetaan kommentit lenkistä");
                }
                System.out.println("Et ole vielä syöttänyt tietoja ohjelmaan");

            }

        }
        catch (IOException e) {
            System.out.println("Tiedoston lukemisessa ongelmia");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Palauttaa lenkit-taulukon, jossa lenkit kirjattuna
     * @return palauttaa taulukon
     */
    public SkiKilometer[] getLenkit() {
        return lenkit;
    }

    /**
     * Metodi summaa kilometrit yhteen SkiKilometer-luokan lisaaKilometriSummaan-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     */
    public void summaa(int i, double d) {
        this.lenkit[i].lisaaKilometriSummaan(d);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion kilometrimäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     */
    public void kilometri(int i, double d) {
        this.lenkit[i].setKilometrit(d);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion päivämäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param da päivämäärä
     */
    public void paivamaara(int i, LocalDate da) {
        this.lenkit[i].setPvm(da);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion sijainnin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono sijainnille
     */
    public void sijainti(int i, String s) {
        this.lenkit[i].setSijainti(s);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion kommentin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono kommentille
     */
    public void kommentti(int i, String s) {
        this.lenkit[i].setKommentit(s);
        tiedostonKirjoitus();
    }
    /**
     * Metodi kirjoittaa taulukon tiedostoon. Samankaltainen metodi Pankkiautomaattiluokassa
     */
    public void tiedostonKirjoitus() {
        try {
            FileOutputStream fos = new FileOutputStream("lenkit.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < lenkit.length; i++) {
                oos.writeObject(lenkit[i]);
            }
            oos.close();
            System.out.println("Kirjoitus onnistui");
        }
        catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen ei onnistunut");
        }
    }

    @Override
    public String toString() {
        return "SkiKilometerMain{" +
                "lenkit=" + Arrays.toString(lenkit) +
                '}';
    }

    public static void main(String[] args) {
        SkiKilometerMain olio = new SkiKilometerMain();

        olio.kilometri(0, 12);
        olio.paivamaara(0, LocalDate.now());
        olio.sijainti(0, "Kuopio");
        olio.kommentti(0, "Pakkasta 10 astetta. Luisto hyvö. Kävin Ala-Antikkalassa");

        System.out.println(olio.lenkit[0].getKommentit());
        olio.kilometri(1, 12);
        olio.paivamaara(1, LocalDate.now());
        olio.sijainti(1, "Kuopio");
        olio.kommentti(1, "Pakkasta 10 astetta. Luisto hyvö. Kävin Ala-Antikkalassa");

        System.out.println(olio.lenkit[1].getKommentit());

    }
}
