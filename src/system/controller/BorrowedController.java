package system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import system.models.Book;
import static system.models.Constants.SCANNER;
import static system.models.Constants.BOOKS;
import static system.models.Constants.STUDENTS;
import system.models.Student;
import system.models.WaitingQueue;
import static system.utils.ScannerUtil.checkNumber;

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

    private final List<Book> BORROWED_BOOK;
    private final WaitingQueue WAITING_BOOK;

    public BorrowedController() {
        BORROWED_BOOK = new ArrayList<>();
        WAITING_BOOK = new WaitingQueue();
    }

    /**
     * Performs an action based on the given option.
     *
     * @param option An integer representing the action to be performed.
     *               1: Borrow an eBook.
     *               2: Return a borrowed eBook.
     */
    public void borrowed(int option) {
        switch (option) {
            case 1:
                borroweBook();
                break;
            case 2:
                giveBack();
                break;
        }
    }

    /**
     * Allows a student to borrow a book from the library. If the book is
     * already borrowed, the student is added to the waiting list. If the
     * student already has the book, they cannot borrow it again.
     *
     * @return void
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
                if (!BORROWED_BOOK.contains(book.get())) {
                    BORROWED_BOOK.add(book.get());
                    student.get().getBorrowedList().add(book.get());
                    listBorrowedBook();
                } else {
                    if (student.get().getBorrowedList().contains(book.get())) {
                        System.out.println("This student already have this book!");
                    } else {
                        System.out.println("This book already borrowed! We have added you to the waiting list.");
                        WAITING_BOOK.add(book.get(), student.get());
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
            if (BORROWED_BOOK.contains(book.get())) {
                BORROWED_BOOK.remove(book.get());
                Student student = STUDENTS.stream()
                        .filter(studentFilter -> studentFilter.getBorrowedList().contains(book.get()))
                        .findAny().get();
                student.getBorrowedList().remove(book.get());
                Optional.ofNullable(WAITING_BOOK.peek(book.get()))
                        .ifPresent(newBorrower -> {
                            System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                            System.out.println("Below student in waiting list for this book!");
                            System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                            System.out.println(String.format("%-10s%-20s%-20s", "ID", "Name", "Surname"));
                            System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                            System.out.println(newBorrower);
                        });
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
        BORROWED_BOOK.forEach(book -> {
            System.out.println(book);
        });
    }
}
