/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Validation {

    public static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static int getAnInteger(String inputMsg, int min) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println("Just an integer and more than " + min);
            }
        }
    }

    public static int getAnInteger(String inputMsg,int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println("Just an integer and between " + min + " and " + max);
            }
        }
    }

    public static String getString(String inputMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if (id.length() == 0) {
                System.out.println("Not blank.Input again!");
            } else {
                return id;
            }
        }
    }

    public static String regexString(String inputMsg,String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || match == false) {
                System.out.println("Wrong format.Input again!");
            } else {
                return id;
            }
        }
    }
    
    public static boolean yesOrNo(String inputMsg) {
        String confirm = regexString(inputMsg, "^[Y|y|N|n]$");
        return confirm.equalsIgnoreCase("y");
    }

    public static Date getDate(String inputMsg) {
        String data;
        while (true) {
            System.out.print(inputMsg);
            data = sc.nextLine().trim();
            try {
                sdf.setLenient(false);
                return sdf.parse(data);
            } catch (ParseException e) {
                System.out.println("Wrong format Date.Input again!");
            }
        }
    }

    public static Date updateDate(String inputMsg, Date oldData) {
        Date result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            boolean check = true;
            try {
                sdf.setLenient(false);
                result = sdf.parse(tmp);
            } catch (ParseException e) {
                System.out.println("Wrong format.Input again!");
                check = false;
            }
            if (check == false) {
                result = getDate("Enter Date(Format as dd-MM-yyyy): ");
            }
        }
        return result;
    }

    public static String updateString(String inputMsg, String oldData) {
        String result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static String updateRegexString(String inputMsg, String format, String oldData) {
        String result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            boolean check = tmp.matches(format);
            if (check == false) {
                tmp = regexString(inputMsg, format);
            }
            result = tmp;
        }
        return result;
    }

    public static int updateAnInteger(String inputMsg, int min, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check == true || number < min);
        return number;
    }

}
