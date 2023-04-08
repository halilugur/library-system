package system;

import static system.models.Constants.*;

import java.util.List;
import system.controller.AuthorController;
import system.controller.BookController;
import system.controller.BorrowedController;
import system.controller.StudentController;
import system.utils.AuthorUtil;
import system.utils.BookUtil;
import system.utils.StudentUtil;
import system.utils.RelationUtil;

/**
 * This program manage the library system with simple functionality.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Main {

    private static void setup() {
        MENU.printBanner();
        MENU.printDataLoading();
        STUDENTS.addAll(StudentUtil.readFromCsv());
        BOOKS.addAll(BookUtil.readFromCsv());
        AUTHORS.addAll(AuthorUtil.readFromCsv());
        RelationUtil.loadBookAndAuthorRelation(BOOKS, AUTHORS);
        MENU.printDataLoaded();
        MENU.printControllers();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setup();
        StudentController studentController = new StudentController();
        BorrowedController borrowedController = new BorrowedController();
        BookController bookController = new BookController();
        AuthorController authorController = new AuthorController();
        int option;
        do {
            System.out.print("Please select an option: ");
            option = checkNumber(MAIN_MENU);
            switch (option) {
                case 1:
                    while (option != BACK) {
                        MENU.printStudentOptions();
                        System.out.print("Please select an option: ");
                        option = checkNumber(STUDENT_MENU_OPTIONS);
                        MENU.printLongSpace();
                        studentController.search(option);
                        MENU.printShortSpace();
                    }
                    break;
                case 2:
                    while (option != BACK) {
                        MENU.printBorrowedOptions();
                        System.out.print("Please select an option: ");
                        option = checkNumber(BARROWED_MENU_OPTIONS);
                        MENU.printLongSpace();
                        borrowedController.borrowed(option);
                        MENU.printShortSpace();
                    }
                    break;
                case 3:
                    while (option != BACK) {
                        MENU.printBookOptions();
                        System.out.print("Please select an option: ");
                        option = checkNumber(BOOK_MENU_OPTIONS);
                        MENU.printLongSpace();
                        bookController.search(option);
                        MENU.printShortSpace();
                    }
                    break;
                case 4:
                    while (option != BACK) {
                        MENU.printAuthorOptions();
                        System.out.print("Please select an option: ");
                        option = checkNumber(AUTHOR_MENU_OPTIONS);
                        MENU.printLongSpace();
                        authorController.search(option);
                        MENU.printShortSpace();
                    }
                    break;
            }
            printControllers(option);
        } while (option != EXIT);
    }

    /**
     * When user input 9 number to console print main menu to the console.
     *
     * @param number input value
     */
    public static void printControllers(int number) {
        if (number == BACK) {
            MENU.printLongSpace();
            MENU.printBanner();
            MENU.printControllers();
        }
    }

    /**
     * Show warning when user enters any value other than options.
     *
     * @param options available options list
     * @return number of option
     */
    public static int checkNumber(List<Integer> options) {
        try {
            int number = SCANNER.nextInt();
            if (!options.contains(number)) {
                System.out.println("This option is not available.");
                System.out.print("Please select an option: ");
                return checkNumber(options);
            }
            return number;
        } catch (Exception e) {
            System.out.println("This option is not available.");
            System.out.print("Please select an option: ");
            return checkNumber(options);
        }
    }
}
