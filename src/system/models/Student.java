package system.models;

import java.util.Objects;

/**
 *
 * @author halilugur
 */
public class Student {

    private Integer id;
    private String name;
    private String surname;
    private Book[] waitingList;
    private Book[] borrowedList;

    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Book[] getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Book[] waitingList) {
        this.waitingList = waitingList;
    }

    public Book[] getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(Book[] borrowedList) {
        this.borrowedList = borrowedList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(this.id, other.id);
    }
}
