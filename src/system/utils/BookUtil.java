/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.utils;

import java.util.ArrayList;
import java.util.List;
import system.models.Book;

/**
 *
 * @author Tolga Baris Pinar
 */
public class BookUtil {

    private final static String BOOK_CSV_PATH = "src/resource/book.csv";

    public static List<Book> readFromCsv() {
        List<Book> books = new ArrayList<>();
        List<String[]> dataList = LoadData.dataFromCsv(BOOK_CSV_PATH, "Book data loading...", "Book data loaded!");
        dataList.parallelStream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String bookTitle = data[1];
            String genre = data[3];
            Book book = new Book(id, bookTitle, genre);
            books.add(book);
        });
        return books;
    }

}
