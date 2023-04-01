package system;

import static system.models.Constants.*;

import java.util.List;
import system.utils.BookUtil;
import system.utils.StudentUtil;

/**
 * This program manage the library system with simple functionality.
 *
 * @author halilugur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MENU.printBanner();
        MENU.printDataLoading();
        STUDENTS.addAll(StudentUtil.readFromCsv());
        BOOKS.addAll(BookUtil.readFromCsv());
        MENU.printDataLoaded();
        MENU.printControllers();
        int option;
        do {
            System.out.print("Please select an option: ");
            option = checkNumber(MAIN_MENU);
            switch (option) {
                case 1:
                    while (option != 9) {
                        MENU.printStudentOptions();
                        option = checkNumber(STUDENT_MENU);
                        MENU.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 2:
                    while (option != 9) {
                        MENU.printBorrowedOptions();
                        option = checkNumber(BARROWED_MENU);
                        MENU.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 3:
                    while (option != 9) {
                        MENU.printBookOptions();
                        option = checkNumber(BOOK_MENU);
                        MENU.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 4:
                    while (option != 9) {
                        MENU.printAuthorOptions();
                        option = checkNumber(AUTHOR_MENU);
                        MENU.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
            }
            printControllers(option);
        } while (option != 0);
    }

    /**
     * When user input 9 number to console print main menu to the console.
     *
     * @param number input value
     */
    public static void printControllers(int number) {
        if (number == 9) {
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
