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

/**
 *
 * @author halilugur
 */
public class BorrowedController {

    private final List<Book> BORROWED_BOOK;
    private final WaitingQueue WAITING_BOOK;

    public BorrowedController() {
        BORROWED_BOOK = new ArrayList<>();
        WAITING_BOOK = new WaitingQueue();
    }

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

    private void borroweBook() {
        System.out.println("Which book you want to borrow? ID: ");
        String bookId = SCANNER.next();
        System.out.println("Student ID: ");
        Integer studentId = SCANNER.nextInt();
        Optional<Student> student = STUDENTS.stream()
                .filter(studentFilter -> studentFilter.getId().equals(studentId))
                .findAny();
        Optional<Book> book = BOOKS.stream()
                .filter(bookFilter -> bookFilter.getCode().equals(bookId))
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
                System.out.println("Book not found! ID: " + bookId);
            }
        } else {
            System.out.println("Student not found! ID: " + studentId);
        }
    }

    private void giveBack() {
        System.out.println("Which book you want to give back? ID: ");
        String bookId = SCANNER.next();
        Optional<Book> book = BOOKS.stream()
                .filter(bookFilter -> bookFilter.getCode().equals(bookId))
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
                            System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                            System.out.println("| Student waiting for this book! |");
                            System.out.println(newBorrower);
                            System.out.println("Waiting List: " + newBorrower.getWaitingList());
                            System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
                        });
            } else {
                System.out.println("This book not borrowed.");
            }
        } else {
            System.out.println("Book not found! ID: " + bookId);
        }
    }

    private void listBorrowedBook() {
        BORROWED_BOOK.forEach(book -> {
            System.out.println(book);
        });
    }
}
