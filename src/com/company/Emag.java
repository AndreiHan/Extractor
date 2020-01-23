package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Emag {

    public static void getTelefoane() {
        getInfo("https://www.emag.ro/telefoane-mobile/p");
    }

    public static void getLaptop() {
        getInfo("https://www.emag.ro/laptopuri/p");
    }

    public static void getPc() {
        getInfo("https://www.emag.ro/desktop-pc/p");
    }

    public static void getTelevizoare() {
        getInfo("https://www.emag.ro/televizoare/p");
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
                for (int i = 1; i <= 28; i++) {

                    doc = Jsoup.connect(Nume + i + "/c").get();
                    Elements images = doc.select("img[src~=(?i)\\.(jpe?g|gif)]");
                    Elements pret = doc.getElementsByClass("product-new-price");

                    //** Elements pretInitial = doc.getElementsByClass("Price-old");
                    int j = 0, k = 0;
                    if (images.isEmpty() || pret.isEmpty()) {
                        i = 61;
                        break;
                    }
                    for (Element image : images) {
                        if (pret.is(" Lei")) {
                            pret = doc.getElementsByClass("product-old-price");
                        }

                        System.out.println("\nsrc : " + image.attr("src"));
                        System.out.println("height : " + image.attr("height"));
                        System.out.println("width : " + image.attr("width"));
                        System.out.println("alt : " + image.attr("alt"));

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
