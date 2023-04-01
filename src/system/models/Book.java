/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.models;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Tolga Baris Pinar
 */
public class Book extends BaseModel {

    private String code;
    private String title;
    private String[] genre;

    public Book(Integer id, String code, String title, String[] genre) {

        super(id);
        this.code = code;
        this.title = title;
        this.genre = genre;
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

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code);
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Arrays.deepHashCode(this.genre);
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
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Arrays.deepEquals(this.genre, other.genre);
    }

    @Override
    public String toString() {
        return "Book{" + "code=" + code + ", title=" + title + ", genre=" + genre + '}';
    }

}
