package system.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import system.screens.Menu;

/**
 * This interface provide constant data.
 *
 * @author halilugur
 */
public interface Constants {

    // Create static instances
    Scanner SCANNER = new Scanner(System.in);
    Menu MENU = Menu.getInstance();

    // Menu Options
    List<Integer> MAIN_MENU = Arrays.asList(1, 2, 3, 4);
    List<Integer> STUDENT_MENU = Arrays.asList(1, 2, 3, 4, 9);
    List<Integer> BARROWED_MENU = Arrays.asList(1, 2, 9);
    List<Integer> BOOK_MENU = Arrays.asList(1, 2, 3, 4, 9);
    List<Integer> AUTHOR_MENU = Arrays.asList(1, 2, 9);

    // Loaded Data
    List<Student> STUDENTS = new ArrayList<>();
    List<Integer> BOOKS = new ArrayList<>();
    List<Integer> AUTHOR = new ArrayList<>();
}
