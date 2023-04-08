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
 * This class read and write to CSV file.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
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

                // Split the line using the delimiter
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
     * This method read data from a CSV.
     *
     * @param path resource of CSV file path.
     * @return List of data as String[]
     */
    public static List<String[]> readCSV(String path) {
        return readCSV(path, "", "");
    }

    /**
     * This method write data to a CSV.
     *
     * @param path resource of CSV file path.
     * @param dataList data for writing to CSV
     * @param titles titles of dataList
     * @param isOverwrite write on file if data exist
     */
    public static void writeCSV(List<String> dataList, String path, String titles, boolean isOverwrite) {
        try ( FileWriter fileWriter = new FileWriter(path, isOverwrite)) {
            StringBuilder builder = new StringBuilder(titles).append("\n");
            dataList.forEach(data -> {
                builder.append(data)
                        .append("\n");
            });
            fileWriter.write(builder.toString());
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     *
     *
     * @param dataList
     * @param titles
     */
    public static void cleanCSV(List<String[]> dataList, String titles) {
        List<String> cleanedData = new ArrayList<>();
        dataList.forEach(values -> {
            String merge = "";
            for (int i = 0; i < values.length; i++) {
                String value = values[i].replace("\"", "");
                if (i < 3) {
                    merge += value + ",";
                } else {
                    if (!value.contains("|") && i > 2 && (i + 1) != values.length) {
                        merge += value;
                    } else {
                        merge += "," + value;
                    }
                }
            }
            cleanedData.add(merge);
        });
        writeCSV(cleanedData, "src/resource/CLEANED_DATA.csv", titles, false);
    }
}
