package system;

import static system.models.Constants.AUTHORS;
import static system.models.Constants.AUTHOR_MENU_OPTIONS;
import static system.models.Constants.BACK;
import static system.models.Constants.BOOKS;
import static system.models.Constants.BOOK_MENU_OPTIONS;
import static system.models.Constants.BORROWED_MENU_OPTIONS;
import static system.models.Constants.EXIT;
import static system.models.Constants.MAIN_MENU;
import static system.models.Constants.MENU;
import static system.models.Constants.STUDENTS;
import static system.models.Constants.STUDENT_MENU_OPTIONS;
import static system.utils.ScannerUtil.checkInListOptions;

import system.controller.AuthorController;
import system.controller.BookController;
import system.controller.BorrowedController;
import system.controller.StudentController;
import system.utils.AuthorUtil;
import system.utils.BookUtil;
import system.utils.RelationUtil;
import system.utils.StudentUtil;

/**
 * This program manages the library system with simple functionality.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Main {

    public static final String PLEASE_SELECT_AN_OPTION = "Please select an option: ";

    /**
     * Sets up the application by loading data from CSV files and initializing
     * the menu. This method reads student, book, and author data from CSV files
     * and loads them into their respective lists. It also loads the
     * relationship between books and authors. Finally, it prints the menu and
     * controllers to the console.
     */
    private static void setup() {
        MENU.printBanner();
        MENU.printDataLoading();
        // READ AND STORAGE DATA ON THE RAM
        STUDENTS.addAll(StudentUtil.readFromCsv());
        BOOKS.addAll(BookUtil.readFromCsv());
        AUTHORS.addAll(AuthorUtil.readFromCsv());
        // MAKE RELATION BETWEEN BOOK AND AUTHOR
        RelationUtil.loadBookAndAuthorRelation(BOOKS, AUTHORS);
        MENU.printDataLoaded();
        MENU.printControllers();
    }

    /**
     * The main method of the library management system. It sets up the system
     * and initialises the controllers for students, borrowed books, books, and
     * authors. It then prompts the user to select an option from the main menu
     * and executes the corresponding operation based on the user's input. The
     * user can navigate through the different options until they choose to exit
     * the system.
     *
     * @param args The command line arguments passed to the program
     */
    public static void main(String[] args) {
        setup();
        StudentController studentController = new StudentController();
        BorrowedController borrowedController = new BorrowedController();
        BookController bookController = new BookController();
        AuthorController authorController = new AuthorController();
        int option;
        do {
            System.out.print(PLEASE_SELECT_AN_OPTION);
            option = checkInListOptions(MAIN_MENU);
            switch (option) {
                case 1:
                    while (option != BACK) {
                        option = studentOperations(studentController);
                    }
                    break;
                case 2:
                    while (option != BACK) {
                        option = borrowedOperations(borrowedController);
                    }
                    break;
                case 3:
                    while (option != BACK) {
                        option = bookOperations(bookController);
                    }
                    break;
                case 4:
                    while (option != BACK) {
                        option = authorOperations(authorController);
                    }
                    break;
            }
            printControllers(option);
        } while (option != EXIT);
    }

    /**
     * Handles the operations related to the student menu.
     *
     * @param studentController The controller for the student entity.
     * @return The option selected by the user.
     */
    private static int studentOperations(StudentController studentController) {
        MENU.printStudentOptions();
        System.out.print(PLEASE_SELECT_AN_OPTION);
        int option = checkInListOptions(STUDENT_MENU_OPTIONS);
        MENU.printLongSpace();
        studentController.search(option);
        MENU.printShortSpace();
        return option;
    }

    /**
     * Displays the menu of options for borrowed operations, prompts the user to
     * select an option, and executes the selected option using the provided
     * BorrowedController.
     *
     * @param borrowedController The controller for borrowed operations
     * @return The user's selected option
     */
    private static int borrowedOperations(BorrowedController borrowedController) {
        MENU.printBorrowedOptions();
        System.out.print(PLEASE_SELECT_AN_OPTION);
        int option = checkInListOptions(BORROWED_MENU_OPTIONS);
        MENU.printLongSpace();
        borrowedController.borrowed(option);
        MENU.printShortSpace();
        return option;
    }

    /**
     * Handles the operations related to the book controller based on the user's
     * selected option.
     *
     * @param bookController The book controller object
     * @return The user's selected option
     */
    private static int bookOperations(BookController bookController) {
        MENU.printBookOptions();
        System.out.print(PLEASE_SELECT_AN_OPTION);
        int option = checkInListOptions(BOOK_MENU_OPTIONS);
        MENU.printLongSpace();
        bookController.search(option);
        MENU.printShortSpace();
        return option;
    }

    /**
     * Handles the author operations based on the selected option.
     *
     * @param authorController The controller for author operations.
     * @return The selected option.
     */
    private static int authorOperations(AuthorController authorController) {
        MENU.printAuthorOptions();
        System.out.print(PLEASE_SELECT_AN_OPTION);
        int option = checkInListOptions(AUTHOR_MENU_OPTIONS);
        MENU.printLongSpace();
        authorController.search(option);
        MENU.printShortSpace();
        return option;
    }

    /**
     * Prints the controllers if the given number is equal to the BACK constant.
     *
     * @param number The number to check against the BACK constant.
     */
    public static void printControllers(int number) {
        if (number == BACK) {
            MENU.printLongSpace();
            MENU.printBanner();
            MENU.printControllers();
        }
    }
}
