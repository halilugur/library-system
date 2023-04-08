package system.utils;

import system.comparator.IdComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import system.models.Book;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookUtil {

    private final static String BOOK_CSV_PATH = "src/resource/book.csv";

    /**
     *
     * @return
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
        Collections.sort(books, new IdComparator<>());
        return books;
    }
}
