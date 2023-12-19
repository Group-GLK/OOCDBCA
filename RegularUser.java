/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cct;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leand
 */
public class RegularUser extends User {
    private List<String> equations;


    public RegularUser(String username, String password, String name, String surname) {
        super(username, password, name, surname);
        this.equations = new ArrayList<>();
    }

    @Override
    public void displayEquations() {
        System.out.println("Equations for " + getUsername() + ":");
        for (String equation : equations) {
            System.out.println(equation);
        }
    }

    public void addEquation(String equation) {
        equations.add(equation);
    }
}
