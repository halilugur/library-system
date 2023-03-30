/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.utils;

import java.util.ArrayList;
import java.util.List;
import system.models.Author;

/**
 *
 * @author Tolga Baris Pinar
 */
public class AuthorUtil {

    private final static String AUTHOR_CSV_PATH = "src/resource/author.csv";

    public static List<Author> readFromCsv() {
        List<Author> authors = new ArrayList<>();
        List<String[]> dataList = LoadData.dataFromCsv(AUTHOR_CSV_PATH, "Author data loading...", "Author data is loaded!");
        dataList.parallelStream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String author_first_name = data[1];
            String author_last_name = data[2];
            Author author = new Author(id, author_first_name, author_last_name);
            authors.add(author);
        });
        return authors;
    }

}
