package system.controller;

import static system.models.Constants.BOOKS;
import static system.models.Constants.SCANNER;
import static system.models.Constants.STUDENTS;
import static system.utils.ScannerUtil.checkNumber;
import static system.utils.StringUtil.makeShort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import system.models.Book;
import system.models.Student;
import system.models.WaitingQueue;

/**
 * The BorrowedController class is responsible for managing the borrowing and
 * returning of books. It contains a list of borrowed books and a waiting queue
 * for books that are currently unavailable. The class provides methods for
 * borrowing a book, returning a book, and listing all currently borrowed books.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BorrowedController {

    private final List<Book> borrowedBook;
    private final WaitingQueue waitingBook;

    public BorrowedController() {
        borrowedBook = new ArrayList<>();
        waitingBook = new WaitingQueue();
    }

    /**
     * Performs an action based on the given option.
     *
     * @param option An integer representing the action to be performed.
     *               1: Borrow an eBook.
     *               2: Return a borrowed book.
     *               3: List all borrowed book.
     *               4: List all waiting book.
     */
    public void borrowed(int option) {
        switch (option) {
            case 1:
                borroweBook();
                break;
            case 2:
                giveBack();
                break;
            case 3:
                listBorrowedBook();
                break;
            case 4:
                listWaitingBook();
                break;
        }
    }

    /**
     * Allows a student to borrow a book from the library. If the book is
     * already borrowed, the student is added to the waiting list. If the
     * student already has the book, they cannot borrow it again.
     */
    private void borroweBook() {
        System.out.println("Which book you want to borrow? CODE: ");
        String bookCode = SCANNER.nextLine();
        Integer studentId = checkNumber("Student ID: ");
        Optional<Student> student = STUDENTS.stream()
                .filter(studentFilter -> studentFilter.getId().equals(studentId))
                .findAny();
        Optional<Book> book = BOOKS.stream()
                .filter(bookFilter -> bookFilter.getCode().equals(bookCode))
                .findAny();
        if (student.isPresent()) {
            if (book.isPresent()) {
                if (!borrowedBook.contains(book.get())) {
                    borrowedBook.add(book.get());
                    student.get().getBorrowedList().add(book.get());
                    listBorrowedBook();
                    waitingBook.poll(book.get());
                } else {
                    if (student.get().getBorrowedList().contains(book.get())) {
                        System.out.println("This student already have this book!");
                    } else {
                        System.out.println("This book already borrowed! We have added you to the waiting list.");
                        waitingBook.add(book.get(), student.get());
                        student.get().getWaitingList().add(book.get());
                    }
                }
            } else {
                System.out.println("Book not found! ID: " + bookCode);
            }
        } else {
            System.out.println("Student not found! ID: " + studentId);
        }
    }

    /**
     * Prompts the user to give back a book by entering its code. If the book is
     * found and has been borrowed, it is removed from the borrowed book list
     * and the student's borrow list. If there is a student waiting for the
     * book, they are printed to the console.
     */
    private void giveBack() {
        System.out.println("Which book you want to give back? CODE: ");
        String bookCode = SCANNER.nextLine();
        Optional<Book> book = BOOKS.stream()
                .filter(bookFilter -> bookFilter.getCode().equals(bookCode))
                .findAny();
        if (book.isPresent()) {
            if (borrowedBook.contains(book.get())) {
                borrowedBook.remove(book.get());
                Student student = STUDENTS.stream()
                        .filter(studentFilter -> studentFilter.getBorrowedList().contains(book.get()))
                        .findAny().orElse(null);
                if (Objects.nonNull(student)) {
                    student.getBorrowedList().remove(book.get());
                    Optional.ofNullable(waitingBook.peek(book.get()))
                            .ifPresent(newBorrower -> {
                                System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                                System.out.println("Below student in waiting list for this book!");
                                System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                                System.out.printf("%-10s%-20s%-20s%n", "ID", "Name", "Surname");
                                System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                                System.out.println(newBorrower);
                            });
                }

            } else {
                System.out.println("This book not borrowed.");
            }
        } else {
            System.out.println("Book not found! CODE: " + bookCode);
        }
    }

    /**
     * Prints out the list of borrowed books.
     */
    private void listBorrowedBook() {
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.printf("%-10s%-40s%-20s%-20s%-15s%-15s%-15s%n",
                "Book ID", "Book Code", "Book Title", "Genres",
                "Author ID", "Author Name", "Author Surname");
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        borrowedBook.forEach(System.out::println);
    }

    /**
     * Prints out the list of waiting books.
     */
    private void listWaitingBook() {
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.printf("%-40s%-20s%-20s%-20s%n", "Book Code", "Book Title", "Student ID", "Student Name");
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        waitingBook.toMap().forEach((book, student) -> System.out.printf("%-40s%-20s%-20s%-20s",
                book.getCode(),
                makeShort(book.getTitle()),
                student.getId(),
                student.getName()));
    }
}
