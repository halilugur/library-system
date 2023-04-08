package system.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import system.screens.Menu;

/**
 * This interface provide constant data.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public interface Constants {

    // Create static instances
    Scanner SCANNER = new Scanner(System.in);
    Menu MENU = Menu.getInstance();

    // Menu Options
    Integer EXIT = 0;
    Integer STUDENT = 1;
    Integer BARROWED = 2;
    Integer BOOK = 3;
    Integer AUTHOR = 4;
    Integer BACK = 9;
    List<Integer> MAIN_MENU = Arrays.asList(STUDENT, BARROWED, BOOK, AUTHOR);
    List<Integer> STUDENT_MENU_OPTIONS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, BACK);
    List<Integer> BARROWED_MENU_OPTIONS = Arrays.asList(1, 2, BACK);
    List<Integer> BOOK_MENU_OPTIONS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, BACK);
    List<Integer> AUTHOR_MENU_OPTIONS = Arrays.asList(1, 2, 3, 4, 5, BACK);

    // Loaded Data
    List<Student> STUDENTS = new ArrayList<>();
    List<Book> BOOKS = new ArrayList<>();
    List<Author> AUTHORS = new ArrayList<>();

}
