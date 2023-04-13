package system.models;

import java.util.Objects;

/**
 * Represents an author with a unique ID, name, and surname.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class Author extends BaseModel {

    private String name;
    private String surname;

    public Author(Integer id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
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

    /**
     * It generates a hash code so that an object will be unique when it is fetched.
     * 
     * @return integer
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.getId());
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.surname);
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        return Objects.equals(this.getId(), other.getId());
    }

    /**
     * Prints information of author's unique ID, name, and surname.
     * 
     * @return a author's unique ID, name, and surname.
     */
    @Override
    public String toString() {
        return String.format("%-10d%-20s%-20s", this.getId(), name, surname);
    }
}
