package system.utils;

import java.util.List;
import static system.models.Constants.SCANNER;

/**
 * A utility class for scanner operation.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class ScannerUtil {

    /**
     * Prompts the user to enter a number and checks if it is in the given list
     * of options. If the number is not in the list, the user is prompted again.
     *
     * @param options A list of integers representing the available options
     * @return The selected option
     * @throws NumberFormatException if the user enters a non-numeric value
     */
    public static int checkInListOptions(List<Integer> options) {
        try {
            String numberStr = SCANNER.nextLine();
            Integer number = Integer.valueOf(numberStr);
            if (!options.contains(number)) {
                System.out.println("This option is not available.");
                System.out.println("Please select an option: ");
                return checkInListOptions(options);
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println("Entered value should be number value!");
            System.out.println("Please select an option: ");
            return checkInListOptions(options);
        }
    }

    /**
     * Prompts the user to enter a number and returns it as an integer. If the
     * user enters a non-numeric value, the method will recursively call itself
     * until a valid number is entered.
     *
     * @param message The message to display to the user when prompting for
     * input
     * @return The integer value entered by the user
     */
    public static int checkNumber(String message) {
        try {
            System.out.print(message);
            String numberStr = SCANNER.nextLine();
            Integer number = Integer.valueOf(numberStr);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("Entered value should be number value!");
            return checkNumber(message);
        }
    }
}
