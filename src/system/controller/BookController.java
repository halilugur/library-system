/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import java.util.Scanner;
import system.screens.Menu;

/**
 *
 * @author Tolga Baris Pinar
 */
public class BookController {

    private Menu view;
    private Scanner scanner;

    public BookController() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice = 9;

        while (choice != 9) {
            view.printBookOptions();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
        }
        
        switch(choice){
            case 1:
                System.out.println("");
                break;
            case 2:
                System.out.println("");
                break;
            case 3:
                System.out.println("");
                break;
            case 4:
                System.out.println("");
                break;
            case 9:
                System.out.println("Going back to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, try again!");
                break;
        }
    }

}
