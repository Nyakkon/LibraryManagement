/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */

import java.util.ArrayList;
import utils.Validation;
public class Menu {

    private String title;
    private ArrayList<String> optionList = new ArrayList();

    public Menu(String title) {
        this.title = title;
    }

    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        System.out.println("<------------------------------------------------>");
        System.out.println("┃           " + title + "           ┃");
        System.out.println("┃==================== MENU ======================┃");
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(optionList.get(i));
        }
        System.out.println("<------------------------------------------------>");
    }

    public int getChoice() {
        String inputMsg = "Choose [1-" + optionList.size() + "]:";
        return Validation.getAnInteger(inputMsg, 1, optionList.size());
    }
}
