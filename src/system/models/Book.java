package system.models;

import java.util.Objects;
import java.util.Set;
import static system.utils.StringUtil.makeShort;

/**
 * Represents a book with a unique ID, code, title, author, and genres.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Book extends BaseModel {

    private String code;
    private String title;
    private Author author;
    private Set<String> genres;

    public Book(Integer id, String code, String title, Set<String> genres) {
        super(id);
        this.code = code;
        this.title = title;
        this.genres = genres;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.getId());
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }

    @Override
    public String toString() {
        return String.format("%-10d%-40s%-20s%-20s%-20s", this.getId(), code, makeShort(title), makeShort(genres), author);
    }
}
