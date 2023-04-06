package system.utils;

import system.comparator.IdComparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        dataList.stream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String code = data[1];
            String title = data[2];
            String[] genre = data[3].split("\\|");
            Book book = new Book(id, code, title, Arrays.asList(genre));
            books.add(book);
        });
        Collections.sort(books, new IdComparator<>());
        return books;
    }

}
