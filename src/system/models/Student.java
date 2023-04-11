package system.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a student with a name, surname, and two sets of books: a waiting
 * list and a borrowed list. Inherits from BaseModel, which provides an ID.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Student extends BaseModel {

    private String name;
    private String surname;
    private Set<Book> waitingList;
    private Set<Book> borrowedList;

    public Student(Integer id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.waitingList = new HashSet<>();
        this.borrowedList = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Book> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Set<Book> waitingList) {
        this.waitingList = waitingList;
    }

    public Set<Book> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(Set<Book> borrowedList) {
        this.borrowedList = borrowedList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.getId());
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return String.format("%-10d%-20s%-20s", this.getId(), name, surname);
    }
}
