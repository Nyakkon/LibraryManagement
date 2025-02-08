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
import model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import utils.Validation;

public class UserManagement implements Manageable {

    public static HashMap<Integer, User> mapUser = new HashMap();

    @Override
    public void add() {
        int id;
        boolean confirm;
        do {
            id = loadIDUserFromFile();
            String name = Validation.getString("Enter Name: ");
            Date dateOfBirh = Validation.getDate("Enter Date Of Birth(dd-MM-yyyy): ");
            String phoneNumber = Validation.regexString("Enter Phone Number(0xx..): ", "^[0]\\d{9}$");
            String email = Validation.regexString("Enter Email: ", "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
            boolean active_user = true;

            mapUser.put(id, new User(id, name, dateOfBirh, phoneNumber, email, active_user));
            System.out.println("User's information has been added.");
            saveIDUserToFile();
            confirm = Validation.yesOrNo("Do you want to add continues(y/n): ");
        } while (confirm);
    }

    public int loadIDUserFromFile() {
        int idMax;
        try {
            FileReader fr = new FileReader("Data\\user_id.txt");
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

    public void saveIDUserToFile() {
        int maxID = 0;
        for (Integer id : mapUser.keySet()) {
            if (maxID < id) {
                maxID = id;
            }
        }
        try {
            FileWriter fw = new FileWriter("Data\\user_id.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(maxID));
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }

    public User getUserByID(int id) {
        User u = null;
        for (User x : mapUser.values()) {
            if (x.getId() == id) {
                u = x;
            }
        }
        return u;
    }

    public User getUserByIDIsActive(int id) {
        User u = null;
        for (User x : mapUser.values()) {
            if (x.getId() == id && x.isActive_user() == true) {
                u = x;
            }
        }
        return u;
    }

    @Override
    public void update() {
        int id = Validation.getAnInteger("Enter Number ID: ", 1);
        User user = getUserByID(id);
        if (user == null) {
            System.out.println("User does not exist!");
        } else {
            String newName = Validation.updateString("Enter New Name: ", user.getName());
            Date newDOB = Validation.updateDate("Enter New Date Of Birth: ", user.getDob());
            String newPhoneNumber = Validation.updateRegexString("Enter New Phone Number(0xx..): ", "^[0]\\d{9}$", user.getPhoneNumber());
            String newEmail = Validation.updateRegexString("Enter New Email: ",
                    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", user.getEmail());

            user.setName(newName);
            user.setDob(newDOB);
            user.setPhoneNumber(newPhoneNumber);
            user.setEmail(newEmail);

            System.out.println("User's information has been updated.Success.");
        }
    }

    @Override
    public void delete() {
        int id = Validation.getAnInteger("Enter Number ID: ", 1);
        User user = getUserByID(id);
        if (user == null) {
            System.out.println("User does not exist!");
        } else {
            boolean confirm = Validation.yesOrNo("Are you sure delete(y/n): ");
            if (confirm) {
                user.setActive_user(false);
                System.out.println("User's information has been updated active(false).");
            } else {
                System.out.println("You cancel.Fail.");
            }
        }
    }

    public void dislay() {
        if (mapUser.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            TreeMap<Integer, User> sortedUser = new TreeMap<>(mapUser);
            System.out.println(repeat("-", 107));
            System.out.printf("┃%-5s|%-25s|%-15s|%-15s|%-25s|%-15s┃\n", "ID", "NAME", "DOB", "PHONE NUMBER", "EMAIL", "ACTIVE USER");
            System.out.printf("┃%-5s|%-25s|%-15s|%-15s|%-25s|%-15s┃\n", repeat("-", 5), repeat("-", 25), repeat("-", 15), repeat("-", 15), repeat("-", 25), repeat("-",15));
            for (User x : sortedUser.values()) {
                String check;
                if(x.isActive_user()){
                    check = "TRUE";
                }else{
                    check = "FALSE";
                }
                System.out.printf("┃%-5d|%-25s|%-15s|%-15s|%-25s|%-15s┃\n", x.getId(), x.getName(), Validation.sdf.format(x.getDob()),
                        x.getPhoneNumber(), x.getEmail(), check);
            }
            System.out.println(repeat("*", 107));

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
