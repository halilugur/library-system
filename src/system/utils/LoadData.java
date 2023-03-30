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
 *
 * @author halilugur
 */
public class LoadData {

    private final static String DELIMITER = ",";

    public static List<String[]> dataFromCsv(String path, String messageStart, String messageEnd) {
        System.out.println(messageStart);
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String[]> dataList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                dataList.add(values);
            }
            System.out.println(messageEnd);
            dataList.remove(0);
            return dataList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
}
