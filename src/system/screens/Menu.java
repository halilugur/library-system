package system.screens;

import java.util.Objects;
import system.models.Constants;

/**
 * 
 * The Menu class provides a set of methods to print different menus and messages to the console.
 * It is a singleton class, meaning that only one instance of it can exist at a time.
 * The class contains methods to print a banner, different menus for different options, and messages to indicate
 * the loading and loaded state of data.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public final class Menu {

    /**
     * Banner generate website.
     *
     * https://springhow.com/spring-boot-banner-generator/
     */
    private final String BANNER
            = "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n"
            + "  _     _ _                            ____            _                 \n"
            + " | |   (_) |__  _ __ __ _ _ __ _   _  / ___| _   _ ___| |_ ___ _ __ ___  \n"
            + " | |   | | '_ \\| '__/ _` | '__| | | | \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n"
            + " | |___| | |_) | | | (_| | |  | |_| |  ___) | |_| \\__ \\ ||  __/ | | | | |\n"
            + " |_____|_|_.__/|_|  \\__,_|_|   \\__, | |____/ \\__, |___/\\__\\___|_| |_| |_|\n"
            + "                               |___/         |___/                       "
            + "\n⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯";

    private static Menu instance;

    private Menu() {
    }

    /**
     * Returns the singleton instance of the Menu class.
     *
     * @return The Menu instance.
     */
    public static Menu getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Menu();
        }
        return instance;
    }

    /**
     * Prints a long space to the console, equivalent to 20 new lines.
     */
    public void printLongSpace() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Prints a short space to the console, equivalent to 2 new lines.
     */
    public void printShortSpace() {
        System.out.println("\n\n");
    }

    /**
     * Prints a loading message to the console.
     */
    public void printDataLoading() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|          DATA LOADING         |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints a loaded message to the console.
     */
    public void printDataLoaded() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|          DATA LOADED          |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the banner to the console.
     */
    public void printBanner() {
        System.out.println(BANNER);
    }

    /**
     * Prints the available options for the user to select from.
     * The options include:
     * 1) Student Search
     * 2) Borrowed Book
     * 3) Book Search
     * 4) Author Search
     * 0) Exit
     */
    public void printControllers() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("1) Student Search");
        System.out.println("2) Borrowed Book");
        System.out.println("3) Book Search");
        System.out.println("4) Author Search");
        System.out.println("0) Exit");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the student available options for the user to select from.
     * The options include:
     * 1) Search by id
     * 2) Search by name
     * 3) Search by surname
     * 4) Find all borrowed book by student id
     * 5) Find all waiting book by student id
     * 6) List all student by name
     * 7) List all student by surname
     * 99) Back
     */
    public void printStudentOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|         Student Search        |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(" 1) Search by id");
        System.out.println(" 2) Search by name");
        System.out.println(" 3) Search by surname");
        System.out.println(" 4) Find all borrowed book by student id");
        System.out.println(" 5) Find all waiting book by student id");
        System.out.println(" 6) List all student by name");
        System.out.println(" 7) List all student by surname");
        System.out.println(Constants.BACK + ") Back");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }
    
    /**
     * Prints the borrowed book available options for the user to select from.
     * The options include:
     * 1) Borrow a book by id
     * 2) Give back a book by id
     * 99) Back
     */
    public void printBorrowedOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|         Borrowed Book         |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(" 1) Borrow a book by id");
        System.out.println(" 2) Give back a book by id");
        System.out.println(Constants.BACK + ") Back");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the book available options for the user to select from.
     * The options include:
     * 1) Search by id
     * 2) Search by code
     * 3) Search by title
     * 4) Search by genre
     * 5) Find all book by author name
     * 6) Find all book by author surname
     * 7) List all book order by title
     * 8) List all book order by author name
     * 9) List all book order by author surname
     * 99) Back
     */
    public void printBookOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|          Book Search          |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(" 1) Search by id");
        System.out.println(" 2) Search by code");
        System.out.println(" 3) Search by title");
        System.out.println(" 4) Search by genre");
        System.out.println(" 5) Find all book by author name");
        System.out.println(" 6) Find all book by author surname");
        System.out.println(" 7) List all book order by title");
        System.out.println(" 8) List all book order by author name");
        System.out.println(" 9) List all book order by author surname");
        System.out.println(Constants.BACK + ") Back");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }
    
    /**
     * Prints the author available options for the user to select from.
     * The options include:
     * 1) Search by id
     * 2) Search by name
     * 3) Search by surname
     * 4) List all author by name
     * 5) List all author by surname
     * 99) Back
     */
    public void printAuthorOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|         Author Search         |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(" 1) Search by id");
        System.out.println(" 2) Search by name");
        System.out.println(" 3) Search by surname");
        System.out.println(" 4) List all author by name");
        System.out.println(" 5) List all author by surname");
        System.out.println(Constants.BACK + ") Back");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints a list of available book genres to the console.
     * Genres are printed in a table format with columns for each genre type.
     */
    public void printGenreOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("|          Genre Types          |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("Action    | Adventure   | Fantasy");
        System.out.println("Romance   | Children    | Horror");
        System.out.println("Mystery   | Thriller    | Comedy");
        System.out.println("Western   | Animation   | Musical");
        System.out.println("Film-Noir | Documentary | Crime ");
        System.out.println("Sci-Fi    | IMAX        | War");
        System.out.println("Drama     |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

}
