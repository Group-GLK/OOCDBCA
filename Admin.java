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
  public class Admin extends User {
    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname);
    }

    @Override
    public void addEquation(String equation) {
        // Admin doesn't have equations, implement if necessary
    }

    @Override
    public void displayEquations() {
        // Admin doesn't have equations, implement if necessary
    }
}


    



