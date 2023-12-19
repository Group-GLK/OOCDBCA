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
  


public class SystemManager {
    private List<User> users;



    public SystemManager() {
        this.users = new ArrayList<>();
        // Admin initialization (placeholder)
        users.add(new Admin("CCT", "Dublin", "Admin", "Admin"));
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(String username) {
        User userToRemove = findUserByUsername(users, username);
        if (userToRemove != null) {
            users.remove(userToRemove);
        }
    }

    private User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
