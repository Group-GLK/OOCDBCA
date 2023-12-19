/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cct;


import java.util.List;
import java.util.Scanner;
/**
 *
 * @author leand
 */
public class CCT {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemManager systemManager = new SystemManager();

        // Menu de Seleção
        int userTypeChoice;
        do {
            System.out.println("Select User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Regular User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            userTypeChoice = scanner.nextInt();

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

        scanner.close();
    }

    private static void authenticateAdminAndProceed(SystemManager systemManager, Scanner scanner) {
        Admin admin = authenticateAdmin(systemManager, scanner);
        if (admin != null) {
            adminMenu(admin, systemManager, scanner);
        } else {
            System.out.println("Authentication failed. Returning to the main menu.");
        }
    }

    private static Admin authenticateAdmin(SystemManager systemManager, Scanner scanner) {
        System.out.println("\nAdmin Authentication");
        System.out.print("Username: ");
        String adminUsername = scanner.next();
        System.out.print("Password: ");
        String adminPassword = scanner.next();

        if (adminUsername.equals("CCT") && adminPassword.equals("Dublin")) {
            // in the admin credentials are corrects
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

    private static Admin findAdminByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user instanceof Admin && user.getUsername().equals(username)) {
                return (Admin) user;
            }
        }
        return null;
    }

    private static void adminMenu(Admin admin, SystemManager systemManager, Scanner scanner) {
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

    private static void regularUserMenu(SystemManager systemManager, Scanner scanner) {
        System.out.println("\nLogin or Register of User Regular");
        System.out.print("Username: ");
        String regularUsername = scanner.next();
        System.out.print("Password: ");
        String regularPassword = scanner.next();

        User regularUser = findUserByUsername(systemManager.getUsers(), regularUsername);

        if (regularUser == null) {
            System.out.print("Name: ");
            String regularName = scanner.next();
            System.out.print("Surname: ");
            String regularSurname = scanner.next();

            regularUser = new RegularUser(regularUsername, regularPassword, regularName, regularSurname);
            systemManager.addUser(regularUser);
            System.out.println("User register with success!");
        } else if (!regularUser.getPassword().equals(regularPassword)) {
            System.out.println("Regular user authentication failed. Closing the program.");
            return;
        }

        regularUserMenuOptions(regularUser, scanner);
    }

    private static void regularUserMenuOptions(User regularUser, Scanner scanner) {
        int regularChoice;
        do {
            System.out.println("\nRegular User Menu:");
            System.out.println("1. Modify Profile");
            System.out.println("2. Save Equation");
            System.out.println("3. Display Equations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            regularChoice = scanner.nextInt();

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

    private static void modifyProfile(User user, Scanner scanner) {
        System.out.print("New name: ");
        String newName = scanner.next();
        user.setName(newName);
        System.out.println("Profile modified successfully!");
    }

    private static void saveEquation(User user, Scanner scanner) {
        System.out.print("Enter the equation to save: ");
        String newEquation = scanner.next();
        user.addEquation(newEquation);
        System.out.println("Equation saved successfully!");
    }

    private static void displayEquations(User user) {
        user.displayEquations();
    }

    private static void accessListOfUsers(List<User> users) {
        System.out.println("\nList of Users:");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Name: " + user.getName() + ", Surname: " + user.getSurname());
        }
    }

    private static void removeUser(SystemManager systemManager, Scanner scanner) {
        System.out.print("Enter the username to remove: ");
        String usernameToRemove = scanner.next();
        systemManager.removeUser(usernameToRemove);
        System.out.println("User removed successfully!");
    }

    private static User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private static void reviewOperations(Admin admin) {
        System.out.println("\nReviewing Operations (Dummy):");
        System.out.println("No operations to review in this simulation.");
    }
}
