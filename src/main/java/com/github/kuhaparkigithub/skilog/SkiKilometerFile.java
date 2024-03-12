package com.github.kuhaparkigithub.skilog;

import java.io.*;
import java.util.ArrayList;

/**
 * Tässä luokassa on ohjelman toiminnallisuus ja tiedostonkäsittely määritelty.
 */
public class SkiKilometerFile {

    /**
     * Kenttänä SkiKilometer luokan ArrayList-tyyppinen olio
     */
    public ArrayList<SkiKilometer> lenkit = new ArrayList<>();


    /**
     * Alustaja, jossa on mukana tiedoston lukeminen. Samankaltainen ratkaisu löytyy Pankkiautomaattiluokasta. Metodissa
     * mukana poikkeustenkäsittelyä. Poikkeustenkäsittelyä voi odottaa tapahtuvan tiedoston lukemisen aikana. Tässä
     * metodissa käsiteltävän tiedoston nimi on lenkit.dat ja siihen voi tallentaa olioita.
     */
    public SkiKilometerFile() {
        try {
            File file = new File("lenkit.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream("lenkit.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                for (int i = 0; i < lenkit.size() + 1; i++) {
                    lenkit.add(i, (SkiKilometer) ois.readObject());
                }
                fis.close();
            }
            else {
                System.out.println("Lenkkejä ei vielä ole");
            }

        }
        catch (IOException ignored) {
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Metodi kirjoittaa taulukon tiedostoon. Samankaltainen metodi Pankkiautomaattiluokassa. Metodissa käytetään
     * poikkeustenkäsittelyä, joka voi tapahtua tiedostoonkirjoittamisen aikana. Metodi tallentaa oliotietoa tiedostoon
     * nimeltä lenkit.dat
     */
    public void tiedostonKirjoitus() {
        try {
            FileOutputStream fos = new FileOutputStream("lenkit.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < lenkit.size(); i++) {
                oos.writeObject(lenkit.get(i));
            }
            oos.close();
            System.out.println("Kirjoitus onnistui");
        }
        catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen ei onnistunut");
        }
    }


    /**
     * Muuttaa listan tiedot merkkijonomuotoon
     * @return palauttaa merkkijonon
     */
    @Override
    public String toString() {
        return "SkiKilometerMain{" +
                "lenkit=" + lenkit.toString() +
                '}';
    }


    /**
     * Metodi tulostaa halutun lenkin tiedot
     * @param i kuvastaa ArrayListin indeksiä
     * @return Palauttaa merkkijonon, jossa on lenkin tiedot aseteltuna halutulla tavalla
     */
    public String lenkinTiedot(int i) {
        return "Lenkin tiedot:\nKilometrit: " + this.lenkit.get(i).getKilometrit() + "km\nPäivämäärä: " +
                this.lenkit.get(i).getPvm() + "\nSijainti: " + this.lenkit.get(i).getSijainti() +
                "\nKommentit: " +  this.lenkit.get(i).getKommentit();
    }


    /**
     * Metodi lisää uuden hiihtolenkin ArrayListiin
     * @param x Olio, joka kuvastaa hiihtolenkkiä ja sisältää sen tiedot
     */
    public void uusiLenkkiListaan(SkiKilometer x) {
        lenkit.addLast(new SkiKilometer(x));
        tiedostonKirjoitus();
    }


    /**
     * Poistaa halutun lenkin ArrayLististä
      * @param i kuvastaa ArrayListin indeksiä, josta olio poistetaan
     */
    public void poistaLenkki(int i) {
        lenkit.remove(i);
        tiedostonKirjoitus();
        System.out.println("Lenkki poistettu");
    }
}