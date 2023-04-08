package system.comparator;

import java.util.Comparator;
import system.models.Book;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookTitleComparator implements Comparator<Book> {

    @Override
    public int compare(Book object1, Book object2) {
        return object1.getTitle().compareTo(object2.getTitle());
    }
}
