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
    //public SkiKilometer[] lenkit = new SkiKilometer[lenkkienMaara];

    public ArrayList<SkiKilometer> lenkit= new ArrayList<>();
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
                    lenkit.add(i, (SkiKilometer) ois.readObject());
                }
                fis.close();
            }
            else {
                for (int i = 0; i < 1; i++) {
                    lenkit.add(i, new SkiKilometer(0, LocalDate.now(), "Tähän kirjoitetaan sijainti",
                            "Tähän kirjoitetaan kommentit lenkistä"));
                    if (lenkkienMaara != 1) {
                        lenkkienMaara++;
                    }
                    tiedostonKirjoitus();
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
     *
     * @return palauttaa taulukon
     */
    public ArrayList<SkiKilometer> getLenkit() {
        return lenkit;
    }

    /**
     * Metodi summaa kilometrit yhteen SkiKilometer-luokan lisaaKilometriSummaan-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     */
    public void summaa(int i, double d) {
        this.lenkit.get(i).lisaaKilometriSummaan(d);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion kilometrimäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     */
    public void kilometri(int i, double d) {
        this.lenkit.get(i).setKilometrit(d);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion päivämäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param da päivämäärä
     */
    public void paivamaara(int i, LocalDate da) {
        this.lenkit.get(i).setPvm(da);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion sijainnin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono sijainnille
     */
    public void sijainti(int i, String s) {
        this.lenkit.get(i).setSijainti(s);
        tiedostonKirjoitus();
    }

    /**
     * Asettaa olion kommentin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono kommentille
     */
    public void kommentti(int i, String s) {
        this.lenkit.get(i).setKommentit(s);
        tiedostonKirjoitus();
    }
    /**
     * Metodi kirjoittaa taulukon tiedostoon. Samankaltainen metodi Pankkiautomaattiluokassa
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
     */
    public void lenkinTiedot(int i) {
        System.out.println("Lenkin tiedot: " + this.lenkit.get(i).getKilometrit() + " " + this.lenkit.get(i).getPvm() +
         " " + this.lenkit.get(i).getSijainti() +  " " +  this.lenkit.get(i).getKommentit());
    }

    public void uusiLenkkiListaan(SkiKilometer x) {
        lenkit.addLast(new SkiKilometer(x));
    }

    /**
     * main-metodi ajaa ohjelman
     * @param args
     */
    public static void main(String[] args) {
        SkiKilometerMain olio = new SkiKilometerMain();

        olio.lenkinTiedot(0);

        System.out.println(olio.lenkit.getFirst().getKommentit());

        System.out.println(olio.lenkit.size());
    }
}







/*

package com.github.kuhaparkigithub.skilog;

import java.io.*;
        import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

*/
/**
 * Tässä luokassa on ohjelman toiminnallisuus määritelty, sekä pääohjelma.
 *//*

public class SkiKilometerMain {
    public int lenkkienMaara = 1;
    */
/**
     * Kenttänä SkiKilometer luokan taulukkotyyppinen olio. Samanlainen ratkaisu löytyy Pankkiautomaattiluokasta.
     *//*

    public SkiKilometer[] lenkit = new SkiKilometer[lenkkienMaara];

    //public ArrayList<SkiKilometer> lenkit= new ArrayList<>();
    */
/**
     * Alustaja, jossa on mukana tiedoston lukeminen. Samankaltainen ratkaisu löytyy Pankkiautomaattiluokasta
     *//*

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

    */
/**
     * Palauttaa lenkit-taulukon, jossa lenkit kirjattuna
     * @return palauttaa taulukon
     *//*

    public SkiKilometer[] getLenkit() {
        return lenkit;
    }

    */
/**
     * Metodi summaa kilometrit yhteen SkiKilometer-luokan lisaaKilometriSummaan-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     *//*

    public void summaa(int i, double d) {
        this.lenkit[i].lisaaKilometriSummaan(d);
        tiedostonKirjoitus();
    }

    */
/**
     * Asettaa olion kilometrimäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param d desimaaliluku kilometrimäärälle
     *//*

    public void kilometri(int i, double d) {
        this.lenkit[i].setKilometrit(d);
        tiedostonKirjoitus();
    }

    */
/**
     * Asettaa olion päivämäärän SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param da päivämäärä
     *//*

    public void paivamaara(int i, LocalDate da) {
        this.lenkit[i].setPvm(da);
        tiedostonKirjoitus();
    }

    */
/**
     * Asettaa olion sijainnin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono sijainnille
     *//*

    public void sijainti(int i, String s) {
        this.lenkit[i].setSijainti(s);
        tiedostonKirjoitus();
    }

    */
/**
     * Asettaa olion kommentin SkiKilometer-luokan set-metodin avulla
     * @param i kokonaisluku taulukon indeksille
     * @param s Merkkijono kommentille
     *//*

    public void kommentti(int i, String s) {
        this.lenkit[i].setKommentit(s);
        tiedostonKirjoitus();
    }
    */
/**
     * Metodi kirjoittaa taulukon tiedostoon. Samankaltainen metodi Pankkiautomaattiluokassa
     *//*

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
*/
