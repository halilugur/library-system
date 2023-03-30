package system;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import system.screens.Menu;
import system.utils.StudentUtil;

/**
 *
 * @author halilugur
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Menu menu = Menu.getInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu.printBanner();
        // LOAD DATA START
        StudentUtil.readFromCsv();
        // LOAD DATA END
        menu.printControllers();
        int option;
        do {
            System.out.print("Please select an option: ");
            option = SCANNER.nextInt();
            switch (option) {
                case 1:
                    while (option != 9) {
                        menu.printStudentOptions();
                        option = checkNumber(Arrays.asList(1, 2, 3, 4, 9));
                        menu.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 2:
                    while (option != 9) {
                        menu.printBorrowedOptions();
                        option = checkNumber(Arrays.asList(1, 2, 9));
                        menu.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 3:
                    while (option != 9) {
                        menu.printBookOptions();
                        option = checkNumber(Arrays.asList(1, 2, 3, 4, 9));
                        menu.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
                case 4:
                    while (option != 9) {
                        menu.printAuthorOptions();
                        option = checkNumber(Arrays.asList(1, 2, 9));
                        menu.printLongSpace();
                        // Operation will implemented here
                        System.out.println("Operation doing... " + option);
                    }
                    break;
            }
            printControllers(option);
        } while (option != 0);
    }

    public static void printControllers(int number) {
        if (number == 9) {
            menu.printLongSpace();
            menu.printBanner();
            menu.printControllers();
        }
    }

    public static int checkNumber(List<Integer> options) {
        int number = SCANNER.nextInt();
        if (!options.contains(number)) {
            System.out.println("This option is not available.");
            System.out.print("Please select an option: ");
            return checkNumber(options);
        }
        return number;
    }
}
