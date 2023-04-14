package system.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import system.comparator.IdComparator;
import system.models.Book;

/**
 * A utility class for reading book data from a CSV file.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookUtil {

    private static final String BOOK_CSV_PATH = "src/resource/book.csv";

    private BookUtil() {
    }

    /**
     * Reads book data from a CSV file and returns a list of Book objects.
     *
     * @return A list of Book objects
     */
    public static List<Book> readFromCsv() {
        List<Book> books = new ArrayList<>();
        List<String[]> dataList = CSVUtil.readCSV(BOOK_CSV_PATH, "Book data loading...", "Book data is loaded!");
        dataList.forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String code = data[1];
            String title = data[2];
            String[] genres;
            if (data.length > 3) {
                genres = data[3].split("\\|");
            } else {
                genres = new String[0];
            }
            Book book = new Book(id, code, title, Set.of(genres));
            books.add(book);
        });
        SortUtil.sort(books, new IdComparator<>());
        return books;
    }
}
