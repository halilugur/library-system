package system.utils;

import java.util.List;
import system.models.Author;
import system.models.Book;

/**
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class RelationUtil {

    private final static String BOOK_AUTHOR_CSV_PATH = "src/resource/book_author.csv";

    /**
     * 
     * @param books
     * @param authors 
     */
    public static void loadBookAndAuthorRelation(List<Book> books, List<Author> authors) {
        List<String[]> relations = CSVUtil.readCSV(BOOK_AUTHOR_CSV_PATH, "Relation data loading...", "Relation data is loaded!");
        relations.forEach(relation -> {
            Integer bookId = Integer.valueOf(relation[0]);
            Integer authorId = Integer.valueOf(relation[1]);
            books.stream().filter(book -> book.getId().equals(bookId))
                    .findFirst()
                    .ifPresent(book -> {
                        Author findAuthor = authors.stream()
                                .filter(author -> author.getId().equals(authorId))
                                .findFirst().orElse(null);
                        book.setAuthor(findAuthor);

                    });
        });
    }
}
