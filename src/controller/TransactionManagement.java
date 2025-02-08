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
import model.Book;
import model.Loan;
import model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import utils.Validation;

public class TransactionManagement {

    private BookManagement bm;
    private UserManagement um;
    public static HashMap<Integer, Loan> mapTransaction = new HashMap();

    public TransactionManagement(BookManagement bm, UserManagement um) {
        this.bm = bm;
        this.um = um;
    }

    public void borrowBooks() {
        int id = loadIDLoanFromFile();

        um.dislay();
        int userID = Validation.getAnInteger("Enter Mumber ID User: ", 1);
        User us = um.getUserByIDIsActive(userID);
        if (us == null) {
            System.out.println("User does not exist or not active.Not borrow.");
            return;
        }

        bm.dislay();
        int bookID = Validation.getAnInteger("Enter Number ID Book: ", 1);
        Book bk = bm.getBookByIDIsActive(bookID);
        if (bk == null) {
            System.out.println("Book does not exist or not active.Not borrow.");
        } else {
            Date borrowDate = Validation.getDate("Enter Borrow Date(dd-MM-yyyy): ");
            Date returnDate = null;
            boolean check = true;
            while (check) {
                Date returnDatecheck = Validation.getDate("Enter End Date(dd-MM-yyyy): ");
                if (returnDatecheck.compareTo(borrowDate) > 0) {
                    returnDate = returnDatecheck;
                    check = false;
                    break;
                } else {
                    System.out.println("The return date cannot be less than or equal to the date the book was borrowed!");
                }
            }
            mapTransaction.put(id, new Loan(id, bookID, userID, borrowDate, returnDate));
            System.out.println("Loan's information has been added.");
        }
        saveIDLoanToFile();
    }

    public int loadIDLoanFromFile() {
        int idMax;
        try {
            FileReader fr = new FileReader("Data\\loan_id.txt");
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

    public void saveIDLoanToFile() {
        int maxID = 0;
        for (Integer id : mapTransaction.keySet()) {
            if (maxID < id) {
                maxID = id;
            }
        }
        try {
            FileWriter fw = new FileWriter("Data\\loan_id.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(maxID));
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }

    public Book getBookIsActive(ArrayList<Book> listBook, int id) {
        Book b = null;
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getId() == id && listBook.get(i).isActive_book() == true) {
                b = listBook.get(i);
            }
        }
        return b;
    }

    public Loan getLoanByID(int id) {
        Loan loan = null;
        for (Loan x : mapTransaction.values()) {
            if (x.getTransactionID() == id) {
                loan = x;
            }
        }
        return loan;
    }

    public void updateLoanInformation() {
        int transctionID = Validation.getAnInteger("Enter Number Transaction ID: ", 1);
        if (!mapTransaction.containsKey(transctionID)) {
            System.out.println("Transaction does not exist!");
        } else {
            Loan loanObj = mapTransaction.get(transctionID);
            Date newBorrowDate = Validation.updateDate("Enter New Borrow Date(dd-MM-yyyy): ", loanObj.getBorrowDate());
            loanObj.setBorrowDate(newBorrowDate);
            boolean check = true;
            while (check) {
                Date returnDate = Validation.getDate("Enter Return Date(dd-MM-yyyy): ");
                if (returnDate.compareTo(newBorrowDate) > 0) {
                    loanObj.setReturnDate(returnDate);
                    check = false;
                    break;
                } else {
                    System.out.println("The return date cannot be less than or equal to the date the book was borrowed!");
                }
            }

            boolean confirmUser = Validation.yesOrNo("Do you want to change user(y/n): ");
            if (confirmUser) {
                um.dislay();
                int userID = Validation.getAnInteger("Enter Number ID User: ", 1);
                User us = um.getUserByIDIsActive(userID);
                if (us == null) {
                    System.out.println("User does not exist!");
                } else {
                    loanObj.setUserID(us.getId());
                }
            }

            boolean confirmBook = Validation.yesOrNo("Do you want to change book(y/n): ");
            if (confirmBook) {
                bm.dislay();
                int bookID = Validation.getAnInteger("Enter Number ID Book: ", 1);
                Book bk = bm.getBookByIDIsActive(bookID);
                if (bk == null) {
                    System.out.println("Book does not exist!");
                } else {
                    loanObj.setBookID(bookID);
                }
            }
            System.out.println("Transaction's information has been updated.");
        }

    }

    public void displayAllBorrowedBooks() {
        if (mapTransaction.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            System.out.println(repeat("-", 74));
            System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", "TRANSACTION_ID", "USER_ID", "BOOK_ID", "BORROW_DATE", "RETURN_DATE");
            System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", repeat("-", 17), repeat("-", 10), repeat("-", 10), repeat("-", 15), repeat("-", 15), repeat("-", 15));
            for (Loan x : mapTransaction.values()) {
//                String check1;
//                long baNgay = 3L * 24L * 60L * 60L * 1000L;
//                if (x.getReturnDate().getTime() - java.util.Calendar.getInstance().getTimeInMillis() <= baNgay) {
//                    check1 = "TRUE";
//                } else {
//                    check1 = "FALSE";
//                }
                System.out.printf("┃%-17d|%-10d|%-10d|%-15s|%-15s┃\n", x.getTransactionID(), x.getUserID(),
                        x.getBookID(), Validation.sdf.format(x.getBorrowDate()),
                        x.getReturnDate() != null ? Validation.sdf.format(x.getReturnDate()) : "null");
            }
            System.out.println(repeat("*", 74));
        }
    }

    public void reportOnBorrowedBooks() {
        HashMap<Integer, Book> mapBook = new HashMap();
        for (Loan x : mapTransaction.values()) {
            Book book = bm.getBookByID(x.getBookID());
            mapBook.put(book.getId(), book);
        }
        System.out.println(repeat("-", 123));
        System.out.printf("┃%-5s|%-15s|%-15s|%-20s|%-20s|%-25s|%-15s┃\n", "ID", "TITLE", "AUTHOR", "PUBLICATION_YEAR", "PUBLISHER", "ISBN", "ACTIVE_BOOK");
        System.out.printf("┃%-5s|%-15s|%-15s|%-20s|%-20s|%-25s|%-15s┃\n", repeat("-", 5), repeat("-", 15), repeat("-", 15), repeat("-", 20), repeat("-", 20), repeat("-", 25), repeat("-", 15));
        for (Book x : mapBook.values()) {
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

    public void reportOnOverdueBooks() {
        boolean check = false;
        Date now = new Date();
        for (Loan x : mapTransaction.values()) {
            if (x.getReturnDate() != null && x.getReturnDate().compareTo(now) < 0) {
//                String check1;
//                if ((x.getBorrowDate().compareTo(x.getReturnDate())) <= 3) {
//                    check1 = "TRUE";
//                } else {
//                    check1 = "FALSE";
//                };
                System.out.println(repeat("-", 74));
                System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", "TRANSACTION_ID", "USER_ID", "BOOK_ID", "BORROW_DATE", "RETURN_DATE");
                System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", repeat("-", 17), repeat("-", 10), repeat("-", 10), repeat("-", 15), repeat("-", 15));
                System.out.printf("┃%-17d|%-10d|%-10d|%-15s|%-15s┃\n", x.getTransactionID(), x.getUserID(),
                        x.getBookID(), Validation.sdf.format(x.getBorrowDate()), Validation.sdf.format(x.getReturnDate()));
                System.out.println(repeat("*", 74));
                check = true;
            }
        }
        if (check == false) {
            System.out.println("Not found overdue books.");
        }
    }

    public void reportOnTotalBorrowingActivities() {
        Date startDate = Validation.getDate("Enter Start Date(dd-MM-yyyy): ");
        Date endDate;
        do {
            endDate = Validation.getDate("Enter End Date(dd-MM-yyyy): ");
            if (endDate.compareTo(startDate) < 0) {
                System.out.println("End Date must be more than Start Date.");
            }
        } while (endDate.compareTo(startDate) < 0);
        boolean check = false;
        for (Loan x : mapTransaction.values()) {
            if (x.getBorrowDate().compareTo(startDate) >= 0 && x.getBorrowDate().compareTo(endDate) <= 0) {
                System.out.println(repeat("-", 73));
                System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", "TRANSACTION_ID", "USER_ID", "BOOK_ID", "BORROW_DATE", "RETURN_DATE");
                System.out.printf("┃%-17s|%-10s|%-10s|%-15s|%-15s┃\n", repeat("-", 17), repeat("-", 10), repeat("-", 10), repeat("-", 15), repeat("-", 15));
                System.out.printf("%-17d%-10d%-10d%-15s%-15s\n", x.getTransactionID(), x.getUserID(),
                        x.getBookID(), Validation.sdf.format(x.getBorrowDate()),
                        x.getReturnDate() != null ? Validation.sdf.format(x.getReturnDate()) : "null");
                System.out.println(repeat("*", 73));
                check = true;

            }
        }
        if (check == false) {
            System.out.println("Not found transaction.");
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