/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cct;

import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Command and Control Tool (CCT) system.
 */
public class CCT {

    /**
     * Main method to run the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Create the system manager to manage users
        SystemManager systemManager = new SystemManager();

        // User type selection menu
        int userTypeChoice;
        do {
            System.out.println("Select User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Regular User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            userTypeChoice = scanner.nextInt();

            // Switch based on user type choice
            switch (userTypeChoice) {
                case 1:
                    // Admin
                    authenticateAdminAndProceed(systemManager, scanner);
                    break;
                case 2:
                    // Regular User
                    regularUserMenu(systemManager, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (userTypeChoice != 0);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    /**
     * Method to authenticate an admin and proceed to the admin menu.
     * @param systemManager the system manager
     * @param scanner the scanner for user input
     */
    private static void authenticateAdminAndProceed(SystemManager systemManager, Scanner scanner) {
        // Authenticate admin
        Admin admin = authenticateAdmin(systemManager, scanner);
        if (admin != null) {
            // Proceed to admin menu if authentication is successful
            adminMenu(admin, systemManager, scanner);
        } else {
            System.out.println("Authentication failed. Returning to the main menu.");
        }
    }

    /**
     * Method to authenticate an admin.
     * @param systemManager the system manager
     * @param scanner the scanner for user input
     * @return the authenticated admin or null if authentication fails
     */
    private static Admin authenticateAdmin(SystemManager systemManager, Scanner scanner) {
        System.out.println("\nAdmin Authentication");
        System.out.print("Username: ");
        String adminUsername = scanner.next();
        System.out.print("Password: ");
        String adminPassword = scanner.next();

        if (adminUsername.equals("CCT") && adminPassword.equals("Dublin")) {
            Admin admin = findAdminByUsername(systemManager.getUsers(), adminUsername);

            if (admin != null) {
                return admin;
            } else {
                System.out.println("Admin not found. Returning to the main menu.");
                return null;
            }
        } else {
            System.out.println("Authentication failed. Returning to the main menu.");
            return null;
        }
    }

    /**
     * Method to find an admin by username.
     * @param users the list of users
     * @param username the username to search for
     * @return the admin with the specified username, or null if not found
     */
    private static Admin findAdminByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user instanceof Admin && user.getUsername().equals(username)) {
                return (Admin) user;
            }
        }
        return null;
    }

    /**
     * Method to display the admin menu and handle admin-specific operations.
     * @param admin the authenticated admin
     * @param systemManager the system manager
     * @param scanner the scanner for user input
     */
    private static void adminMenu(Admin admin, SystemManager systemManager, Scanner scanner) {
        // Admin menu options
        int adminChoice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Modify Own Profile");
            System.out.println("2. Access List of Users");
            System.out.println("3. Remove User");
            System.out.println("4. Review Operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();

            // Switch based on admin menu choice
            switch (adminChoice) {
                case 1:
                    modifyProfile(admin, scanner);
                    break;
                case 2:
                    accessListOfUsers(systemManager.getUsers());
                    break;
                case 3:
                    removeUser(systemManager, scanner);
                    break;
                case 4:
                    reviewOperations(admin);
                    break;
                case 0:
                    System.out.println("Exiting Admin Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (adminChoice != 0);
    }

    /**
     * Method to handle regular user menu options.
     * @param systemManager the system manager
     * @param scanner the scanner for user input
     */
    private static void regularUserMenu(SystemManager systemManager, Scanner scanner) {
        // Regular user login or registration
        System.out.println("\nLogin or Registration for Regular User");
        System.out.print("Username: ");
        String regularUsername = scanner.next();
        System.out.print("Password: ");
        String regularPassword = scanner.next();

        // Find or register regular user
        User regularUser = findUserByUsername(systemManager.getUsers(), regularUsername);

        if (regularUser == null) {
            // Register new regular user
            System.out.print("Name: ");
            String regularName = scanner.next();
            System.out.print("Surname: ");
            String regularSurname = scanner.next();

            regularUser = new RegularUser(regularUsername, regularPassword, regularName, regularSurname);
            systemManager.addUser(regularUser);
            System.out.println("User registered successfully!");
        } else if (!regularUser.getPassword().equals(regularPassword)) {
            // Authentication failure
            System.out.println("Regular user authentication failed. Exiting the program.");
            return;
        }

        // Display regular user menu options
        regularUserMenuOptions(regularUser, scanner);
    }

    /**
     * Method to handle regular user menu options.
     * @param regularUser the authenticated regular user
     * @param scanner the scanner for user input
     */
    private static void regularUserMenuOptions(User regularUser, Scanner scanner) {
        // Regular user menu options
        int regularChoice;
        do {
            System.out.println("\nRegular User Menu:");
            System.out.println("1. Modify Profile");
            System.out.println("2. Save Equation");
            System.out.println("3. Display Equations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            regularChoice = scanner.nextInt();

            // Switch based on regular user menu choice
            switch (regularChoice) {
                case 1:
                    modifyProfile(regularUser, scanner);
                    break;
                case 2:
                    saveEquation(regularUser, scanner);
                    break;
                case 3:
                    displayEquations(regularUser);
                    break;
                case 0:
                    System.out.println("Exiting Regular User Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (regularChoice != 0);
    }

    /**
     * Method to modify the profile of a user.
     * @param user the user whose profile is to be modified
     * @param scanner the scanner for user input
     */
    private static void modifyProfile(User user, Scanner scanner) {
        System.out.print("New name: ");
        String newName = scanner.next();
        user.setName(newName);
        System.out.println("Profile modified successfully!");
    }

    /**
     * Method to save an equation for a regular user.
     * @param user the regular user
     * @param scanner the scanner for user input
     */
    private static void saveEquation(User user, Scanner scanner) {
        System.out.print("Enter the equation to save: ");
        String newEquation = scanner.next();
        user.addEquation(newEquation);
        System.out.println("Equation saved successfully!");
    }

    /**
     * Method to display equations for a regular user.
     * @param user the regular user
     */
    private static void displayEquations(User user) {
        user.displayEquations();
    }

    /**
     * Method to access and display the list of users.
     * @param users the list of users
     */
    private static void accessListOfUsers(List<User> users) {
        System.out.println("\nList of Users:");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Name: " + user.getName() + ", Surname: " + user.getSurname());
        }
    }

    /**
     * Method to remove a user by username.
     * @param systemManager the system manager
     * @param scanner the scanner for user input
     */
    private static void removeUser(SystemManager systemManager, Scanner scanner) {
        System.out.print("Enter the username to remove: ");
        String usernameToRemove = scanner.next();
        systemManager.removeUser(usernameToRemove);
        System.out.println("User removed successfully!");
    }

    /**
     * Method to find a user by username.
     * @param users the list of users
     * @param username the username to search for
     * @return the user with the specified username, or null if not found
     */
    private static User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Method to review operations for an admin.
     * @param admin the admin user
     */
    private static void reviewOperations(Admin admin) {
        System.out.println("\nReviewing Operations (Dummy):");
        System.out.println("No operations to review in this simulation.");
    }
}
