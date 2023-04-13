package system.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import static system.utils.StringUtil.makeShort;

/**
 * Represents a student with a name, surname, address and two sets of books: a
 * waiting list and a borrowed list. Inherits from BaseModel, which provides an
 * ID.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Student extends BaseModel {

    private String name;
    private String surname;
    private String address;
    private Set<Book> waitingList;
    private Set<Book> borrowedList;

    public Student(Integer id, String name, String surname, String address) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    /**
     * It generates a hash code so that an object will be unique when it is fetched.
     * 
     * @return integer
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.getId());
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        return hash;
    }

    /**
     * It checks whether this Object equals to the current object.
     * 
     * @param obj that will be compared.
     * @return the result of the comparison as a boolean value.
     */
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

    /**
     * Prints information of student's ID, Name, Surname and Address.
     * 
     * @return a student's ID, Name, Surname and Address.
     */
    @Override
    public String toString() {
        return String.format("%-10d%-20s%-20s%-20s", this.getId(), name, surname, makeShort(address));
    }
}
