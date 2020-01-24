package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Altex {

    public static void getLaptop() {
        getInfo("https://altex.ro/laptopuri/cpl/filtru/p/");
    }

    public static void getTelefoane() {
        getInfo("https://altex.ro/telefoane/cpl/filtru/p/");
    }

    public static void getPc() {
        getInfo("https://altex.ro/sisteme-pc-calculatoare/cpl/filtru/p/");
    }

    public static void getTelevizoare() {
        getInfo("https://altex.ro/televizoare/cpl/filtru/p/");
    }


    private static void getInfo(String Nume) {
        Document doc;
        LocalDate DataCurenta = LocalDate.now();

        File file = new File(DataCurenta + ".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File has been created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                PrintWriter shout = new PrintWriter(DataCurenta + ".txt")) {

            try {
                String Site = null;
                for (int i = 1; i <= 501; i++) {

                    doc = Jsoup.connect(Nume + i + "/").get();
                    Elements images = doc.select("img[src~=(?i)\\.(jpe?g|gif)]");
                    Elements pret = doc.getElementsByClass("Price-int");
                    //** Elements pretInitial = doc.getElementsByClass("Price-old");
                    int j = 0, k = 0;
                    if (images.isEmpty() || pret.isEmpty()) {
                        i = 61;
                        break;
                    }
                    for (Element image : images) {
                        /*
                        System.out.println(Nume + i + "/");
                        System.out.println("\nsrc : " + image.attr("src"));
                        System.out.println("height : " + image.attr("height"));
                        System.out.println("width : " + image.attr("width"));
                        System.out.println("alt : " + image.attr("alt"));
                        */

                        String ok = image.attr("alt");

                        if (ok != null && !ok.isEmpty()) {
                            String[] arrOfSuma = pret.text().split(" ");
                            shout.write(image.attr("alt") + ", " + arrOfSuma[j]);
                            j++;

                            shout.println("");
                        }
                    }
                    if (images.attr("alt").isEmpty())
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
