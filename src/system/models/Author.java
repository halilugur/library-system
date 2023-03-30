/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.models;

import java.util.Objects;

/**
 *
 * @author Tolga Baris Pinar
 */
public class Author {

    private Integer id;
    private String author_first_name;
    private String author_last_name;

    public Author(Integer id, String author_first_name, String author_last_name) {
        this.id = id;
        this.author_first_name = author_first_name;
        this.author_last_name = author_last_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor_first_name() {
        return author_first_name;
    }

    public void setAuthor_first_name(String author_first_name) {
        this.author_first_name = author_first_name;
    }

    public String getAuthor_last_name() {
        return author_last_name;
    }

    public void setAuthor_last_name(String author_last_name) {
        this.author_last_name = author_last_name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.author_first_name);
        hash = 89 * hash + Objects.hashCode(this.author_last_name);
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.author_first_name, other.author_first_name)) {
            return false;
        }
        if (!Objects.equals(this.author_last_name, other.author_last_name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    
}
