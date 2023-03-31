package system.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class read and write to CSV file.
 *
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

    }
}
