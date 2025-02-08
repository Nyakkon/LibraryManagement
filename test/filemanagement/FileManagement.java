/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filemanagement;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */

import static controller.BookManagement.mapBook;
import static controller.TransactionManagement.mapTransaction;
import static controller.UserManagement.mapUser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import model.Book;
import model.Loan;
import model.User;

public class FileManagement {
    public void loadDataFromFile() {
        boolean check = true;
        try {
            FileInputStream fis = new FileInputStream("Data\\Books.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapBook = (HashMap<Integer, Book>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileInputStream fis = new FileInputStream("Data\\Users.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapUser =  (HashMap<Integer, User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileInputStream fis = new FileInputStream("Data\\Loans.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapTransaction = (HashMap<Integer, Loan>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
    }

    public void saveDataToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("Data\\Books.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapBook);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileOutputStream fos = new FileOutputStream("Data\\Users.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapUser);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileOutputStream fos = new FileOutputStream("Data\\Loans.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapTransaction);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        if (check) {
            System.out.println("Save data to file success.");
        }else{
            System.out.println("Save data to file fail.");
        }
    }
}
