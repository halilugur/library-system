package system.screens;

import java.util.Arrays;
import java.util.Objects;

/**
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

    public static Menu getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Menu();
        }
        return instance;
    }

    public void printLongSpace() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void printDataLoading() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| DATA LOADING |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    public void printDataLoaded() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| DATA LOADED |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    public void printBanner() {
        System.out.println(BANNER);
    }

    public void printControllers() {
        System.out.println("1) Student Search");
        System.out.println("2) Borrowed Book");
        System.out.println("3) Book Search");
        System.out.println("4) Author Search");
        System.out.println("9) Exit");
    }

    public void printStudentOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| Student Search |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("1) Search by id");
        System.out.println("2) Search by name");
        System.out.println("3) Search by surname");
        System.out.println("4) Find all borrowed book by student id");
        System.out.println("5) Find all waiting book by student id");
        System.out.println("6) List all student by name");
        System.out.println("7) List all student by surname");
        System.out.println("9) Back");
    }

    public void printBorrowedOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| Borrowed Book |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("1) Borrow a book by id");
        System.out.println("2) Give back a book by id");
        System.out.println("9) Back");
    }

    public void printBookOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| Book Search |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("1) Search by title");
        System.out.println("2) Search by genre"); //TODO print genre lists so that user can choose one
        System.out.println("3) Find all by author name");
        System.out.println("4) Find all by author surname");
        System.out.println("9) Back");
    }

    public void printAuthorOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| Author Search |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("1) Search by name");
        System.out.println("2) Search by surname");
        System.out.println("9) Back");
    }

    public void printGenreOptions() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println("| Genre Types |");
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(" Action | Adventure | Fantasy | Romance | Children | Horror ");
        System.out.println(" Mystery | Thriller | Comedy | Western | Animation | Musical ");
        System.out.println(" Film-Noir | Documentary | Crime | Sci-Fi | IMAX | War | Drama ");
        System.out.println("0) Back");
    }

}
