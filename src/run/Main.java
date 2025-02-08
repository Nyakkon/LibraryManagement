/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */
import view.*;
import controller.BookManagement;
import controller.TransactionManagement;
import controller.UserManagement;
import filemanagement.FileManagement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu("Library Management System ");
        menu.addNewOption("┃     1. Manage Books.                           ┃");
        menu.addNewOption("┃     2. Manage Users.                           ┃");
        menu.addNewOption("┃     3. Managing Borrowing and Returning Books. ┃");
        menu.addNewOption("┃     4. Reporting.                              ┃");
        menu.addNewOption("┃     5. Exit!                                   ┃");
        BookManagement bookmanager = new BookManagement();
        UserManagement usermanage = new UserManagement();
        TransactionManagement tm = new TransactionManagement(bookmanager, usermanage);
        FileManagement fileMng = new FileManagement();
        fileMng.loadDataFromFile();

        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    Menu menuBook = new Menu("     Book Management      ");
                    menuBook.addNewOption("┃     1. Add a New Book.                         ┃");
                    menuBook.addNewOption("┃     2. Update Book Information.                ┃");
                    menuBook.addNewOption("┃     3. Delete a Book.                          ┃");
                    menuBook.addNewOption("┃     4. Show All Books.                         ┃");
                    menuBook.addNewOption("┃     5. Return main menu!                       ┃");
                    int choiceBook;
                    do {
                        menuBook.printMenu();
                        choiceBook = menuBook.getChoice();
                        switch (choiceBook) {
                            case 1:
                                bookmanager.add();
                                break;
                            case 2:
                                bookmanager.update();
                                break;
                            case 3:
                                bookmanager.delete();
                                break;
                            case 4:
                                bookmanager.dislay();
                                System.out.print("Enter for exit:");
                                String enter = sc.nextLine();
                                break;
                            case 5:
                                System.out.println("Exit.Return main menu!");
                                break;
                        }
                    } while (choiceBook != 5);
                    break;
                case 2:
                    Menu menuUser = new Menu("     User Management      ");
                    menuUser.addNewOption("┃     1. Add a New User.                         ┃");
                    menuUser.addNewOption("┃     2. Update User Information.                ┃");
                    menuUser.addNewOption("┃     3. Delete a User.                          ┃");
                    menuUser.addNewOption("┃     4. Show All Users.                         ┃");
                    menuUser.addNewOption("┃     5. Return main menu!                       ┃");
                    int choiceUser;
                    do {
                        menuUser.printMenu();
                        choiceUser = menuUser.getChoice();
                        switch (choiceUser) {
                            case 1:
                                usermanage.add();
                                break;
                            case 2:
                                usermanage.update();
                                break;
                            case 3:
                                usermanage.delete();
                                break;
                            case 4:
                                usermanage.dislay();
                                System.out.print("Enter for exit:");
                                String enter = sc.nextLine();
                                break;
                            case 5:
                                System.out.println("Exit.Return main menu!");
                                break;
                        }
                    } while (choiceUser != 5);
                    break;
                case 3:
                    Menu menuLoan = new Menu("     Loan Management      ");
                    menuLoan.addNewOption("┃     1. Borrow Books.                           ┃");
                    menuLoan.addNewOption("┃     2. Update Loan Information.                ┃");
                    menuLoan.addNewOption("┃     3. Display All Borrowed Books.             ┃");
                    menuLoan.addNewOption("┃     4. Return main menu!                       ┃");
                    int choiceLoan;
                    do {
                        menuLoan.printMenu();
                        choiceLoan = menuLoan.getChoice();
                        switch (choiceLoan) {
                            case 1:
                                tm.borrowBooks();
                                System.out.print("Enter for exit:");
                                String enter1 = sc.nextLine();
                                break;
                            case 2:
                                tm.displayAllBorrowedBooks();
                                tm.updateLoanInformation();
                                System.out.print("Enter for exit:");
                                String enter2 = sc.nextLine();
                                break;
                            case 3:
                                tm.displayAllBorrowedBooks();
                                System.out.print("Enter for exit:");
                                String enter3 = sc.nextLine();
                                break;
                            case 4:
                                System.out.println("Exit.Return main menu!");
                                break;
                        }
                    } while (choiceLoan != 4);
                    break;
                case 4:
                    Menu menuReport = new Menu("     Report Management    ");
                    menuReport.addNewOption("┃     1. Report on Borrowed Books.               ┃");
                    menuReport.addNewOption("┃     2. Report on Overdue Books.                ┃");
                    menuReport.addNewOption("┃     3. Report on Total Borrowing Activities.   ┃");
                    menuReport.addNewOption("┃     4. Return main menu!                       ┃");
                    int choiceReport;
                    do {
                        menuReport.printMenu();
                        choiceReport = menuReport.getChoice();
                        switch (choiceReport) {
                            case 1:
                                tm.reportOnBorrowedBooks();
                                System.out.print("Enter for exit:");
                                String enter1 = sc.nextLine();
                                break;
                            case 2:
                                tm.reportOnOverdueBooks();
                                System.out.print("Enter for exit:");
                                String enter2 = sc.nextLine();
                                break;
                            case 3:
                                tm.reportOnTotalBorrowingActivities();
                                System.out.print("Enter for exit:");
                                String enter3 = sc.nextLine();
                                break;
                            case 4:
                                System.out.println("Exit.Return main menu!");
                                break;
                        }
                    } while (choiceReport != 4);
                    break;
                case 5:
                    fileMng.saveDataToFile();
                    System.out.print("Enter for exit:");
                    String enter = sc.nextLine();
                    break;
                case 6:
                    System.out.println("Exist!");
                    break;
            }
        } while (choice != 6);
    }
}
