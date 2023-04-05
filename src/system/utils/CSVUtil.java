package system.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class read and write to CSV file.
 *
 * @author halilugur&tolga baris pinar
 */
public class CSVUtil {

    private final static String DELIMITER = ",";

    /**
     * This method read data from a CSV.
     *
     * @param path resource of CSV file path.
     * @param messageStart print message when started read data.
     * @param messageEnd print message when ended read data.
     * @return List of data as String[]
     */
    public static List<String[]> readCSV(String path, String messageStart, String messageEnd) {
        System.out.println(messageStart);
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String[]> dataList = new ArrayList<>();
            String line;
            boolean skipFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }
                String[] values = line.split(DELIMITER);
                dataList.add(values);
            }
            System.out.println(messageEnd);
            return dataList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentUtil.class.getName()).log(Level.SEVERE, "File not found.", ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentUtil.class.getName()).log(Level.SEVERE, "Operation error.", ex);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * This method write data to a CSV.
     *
     * @param path CSV file path.
     * @param messageStart print message when started write data.
     * @param messageEnd print message when ended write data.
     */
    public static void writeCSV(String path, String messageStart, String messageEnd) {
        System.out.println(messageStart);

        try {
            // Create a FileWriter and BufferedWriter to write to the CSV file
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Prompt the user to enter new data to add to the CSV file
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new data to add to the CSV file: ");
            String newData = scanner.nextLine();

            //write the new data to the CSV file
            bufferedWriter.write(newData);
            bufferedWriter.newLine();

            //Close the file
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Successfully wrote to the CSV file.");

        } catch (IOException e) {
            System.out.println("An error occurred while wirting to CSV file:");
            e.printStackTrace();
        }

        System.out.println(messageEnd);
    }
}
