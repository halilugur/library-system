package system.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class for reading and writing CSV files.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class CSVUtil {

    private static final String DELIMITER = ",";

    private CSVUtil() {
    }

    /**
     * Reads a CSV file and returns its contents as a list of string arrays.
     *
     * @param path         The path to the CSV file.
     * @param messageStart The message to print before reading the file.
     * @param messageEnd   The message to print after reading the file.
     * @return A list of string arrays containing the contents of the CSV file.
     * If the file is not found or an error occurs, an empty list is returned.
     */
    public static List<String[]> readCSV(String path, String messageStart, String messageEnd) {
        System.out.println(messageStart);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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
        return Collections.emptyList();
    }

    /**
     * Reads a CSV file and returns its contents as a list of string arrays.
     *
     * @param path The path to the CSV file.
     * @return A list of string arrays representing the contents of the CSV
     * file.
     */
    public static List<String[]> readCSV(String path) {
        return readCSV(path, "", "");
    }

    /**
     * Writes a list of data to a CSV file at the specified path.
     *
     * @param dataList    The list of data to write to the CSV file
     * @param path        The path to the CSV file
     * @param titles      The titles of the columns in the CSV file
     * @param isOverwrite Whether to overwrite the file if it already exists
     */
    public static void writeCSV(List<String> dataList, String path, String titles, boolean isOverwrite) {
        try (FileWriter fileWriter = new FileWriter(path, isOverwrite)) {
            StringBuilder builder = new StringBuilder(titles).append("\n");
            dataList.forEach(data -> builder.append(data).append("\n"));
            fileWriter.write(builder.toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Cleans the given CSV data by removing any double quotes and merging
     * values that are split across multiple columns. The cleaned data is then
     * written to a new CSV file.
     *
     * @param dataList The list of CSV data to be cleaned
     * @param titles   The titles of the CSV columns
     */
    public static void cleanCSV(List<String[]> dataList, String titles) {
        List<String> cleanedData = new ArrayList<>();
        dataList.forEach(values -> {
            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < values.length; i++) {
                String value = values[i].replace("\"", "");
                if (i < 3) {
                    builder.append(value).append(",");
                } else {
                    if (!value.contains("|") && (i + 1) != values.length) {
                        builder.append(value);
                    } else {
                        builder.append(",").append(value);
                    }
                }
            }
            cleanedData.add(builder.toString());
        });
        writeCSV(cleanedData, "src/resource/CLEANED_DATA.csv", titles, false);
    }
}
