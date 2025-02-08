/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */
import interfacemanagement.Manageable;
import model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import utils.Validation;

public class BookManagement implements Manageable {

    public static HashMap<Integer, Book> mapBook = new HashMap<>();

    @Override
    public void add() {
        int id;
        boolean confirm;
        do {
            id = loadIDBookFromFile();
            String title = Validation.getString("Enter Title: ");
            String author = Validation.getString("Enter Author: ");
            int publication_year = Validation.getAnInteger("Enter Publication Year: ", 1);
            String publisher = Validation.getString("Enter Publisher: ");
            String ISBN = generateISBN();
            boolean active_book = true;

            mapBook.put(id, new Book(id, title, author, publication_year, publisher, ISBN, active_book));
            System.out.println("Book's information has been added.");
            saveIDBookToFile();
            confirm = Validation.yesOrNo("Do you want to add continues(y/n): ");
        } while (confirm);
    }

    private String generateISBN() {
        String isbn;
        Random random = new Random();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 13; i++) {
                sb.append(random.nextInt(10));
            }
            isbn = sb.toString();
        } while (isISBNExists(isbn));
        return isbn;
    }

    private boolean isISBNExists(String isbn) {
        for (Book book : mapBook.values()) {
            if (book.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public int loadIDBookFromFile() {
        int idMax;
        try {
            FileReader fr = new FileReader("Data\\book_id.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine().trim();
            idMax = Integer.parseInt(line);
            br.close();
            fr.close();
        } catch (Exception e) {
            idMax = 0;
        }
        return idMax + 1;
    }

    public void saveIDBookToFile() {
        int maxID = 0;
        for (Integer id : mapBook.keySet()) {
            if (maxID < id) {
                maxID = id;
            }
        }
        try {
            FileWriter fw = new FileWriter("Data\\book_id.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(maxID));
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }

    public Book getBookByID(int id) {
        Book b = null;
        for (Book x : mapBook.values()) {
            if (x.getId() == id) {
                b = x;
            }
        }
        return b;
    }

    public Book getBookByIDIsActive(int id) {
        Book b = null;
        for (Book x : mapBook.values()) {
            if (x.getId() == id && x.isActive_book() == true) {
                b = x;
            }
        }
        return b;
    }

    @Override
    public void update() {
        int id = Validation.getAnInteger("Enter Number ID: ", 1);
        Book bk = getBookByID(id);
        if (bk == null) {
            System.out.println("Book does not exist!");
        } else {
            String newTitle = Validation.updateString("Enter New Title: ", bk.getTitle());
            String newAuthor = Validation.updateString("Enter New Author: ", bk.getAuthor());
            int newPublicationYear = Validation.updateAnInteger("Enter New Publication Year: ", 1, bk.getPublication_year());
            String newPublisher = Validation.updateString("Enter New Publisher: ", bk.getPublisher());

            bk.setTitle(newTitle);
            bk.setAuthor(newAuthor);
            bk.setPublication_year(newPublicationYear);
            bk.setPublisher(newPublisher);

            System.out.println("Book's information has been updated.");
        }
    }

    @Override
    public void delete() {
        int id = Validation.getAnInteger("Enter Number ID: ", 1);
        Book bk = getBookByID(id);
        if (bk == null) {
            System.out.println("Book does not exist!");
        } else {
            boolean confirm = Validation.yesOrNo("Are you sure delete(y/n): ");
            if (confirm) {
                bk.setActive_book(false);
                System.out.println("Book's information has been updated active(false).");
            } else {
                System.out.println("You cancel.Fail.");
            }
        }
    }

    public void dislay() {
        if (mapBook.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            TreeMap<Integer, Book> sortedBook = new TreeMap<>(mapBook);
            System.out.println(repeat("-", 123));
            System.out.printf("┃%-5s|%-15s|%-15s|%-20s|%-20s|%-25s|%-15s┃\n", "ID", "TITLE", "AUTHOR", "PUBLICATION_YEAR", "PUBLISHER", "ISBN", "ACTIVE_BOOK");
            System.out.printf("┃%-5s|%-15s|%-15s|%-20s|%-20s|%-25s|%-15s┃\n", repeat("-", 5), repeat("-", 15), repeat("-", 15), repeat("-", 20), repeat("-", 20), repeat("-", 25), repeat("-", 15));
            for (Book x : sortedBook.values()) {
                String check;
                if (x.isActive_book()) {
                    check = "TRUE";
                } else {
                    check = "FALSE";
                }
                System.out.printf("┃%-5d|%-15s|%-15s|%-20d|%-20s|%-25s|%-15s┃\n", x.getId(), x.getTitle(), x.getAuthor(), x.getPublication_year(),
                        x.getPublisher(), x.getISBN(), check);
            }
            System.out.println(repeat("*", 123));
        }
    }

    private String repeat(String str, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
}
