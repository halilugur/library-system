package system.utils;

import java.util.ArrayList;
import java.util.List;
import system.comparator.IdComparator;
import system.models.Author;

/**
 * A utility class for reading author data from a CSV file.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class AuthorUtil {

    private final static String AUTHOR_CSV_PATH = "src/resource/author.csv";

    /**
     * Reads author data from a CSV file and returns a sorted list of Author
     * objects.
     *
     * @return A sorted list of Author objects
     */
    public static List<Author> readFromCsv() {
        List<Author> authors = new ArrayList<>();
        List<String[]> dataList = CSVUtil.readCSV(AUTHOR_CSV_PATH, "Author data loading...", "Author data is loaded!");
        dataList.stream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String name = data[1];
            String surname = data[2];
            Author author = new Author(id, name, surname);
            authors.add(author);
        });
        SortUtil.sort(authors, new IdComparator<>());
        return authors;
    }
}
