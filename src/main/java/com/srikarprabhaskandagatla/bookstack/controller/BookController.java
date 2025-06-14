package com.srikarprabhaskandagatla.bookstack.controller;

import com.srikarprabhaskandagatla.bookstack.model.Book;
import com.srikarprabhaskandagatla.bookstack.model.BorrowedBook;
import com.srikarprabhaskandagatla.bookstack.repository.BookRepository;
import com.srikarprabhaskandagatla.bookstack.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BorrowedBookRepository borrowRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        return bookRepo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable int id) {
        Optional<Book> optional = bookRepo.findById(id);
        if (optional.isPresent() && optional.get().getQuantity() > 0) {
            Book book = optional.get();
            book.setQuantity(book.getQuantity() - 1);
            bookRepo.save(book);
            BorrowedBook bb = new BorrowedBook(id, 1, LocalDate.now().plusDays(14));
            borrowRepo.save(bb);
            return ResponseEntity.ok("Book borrowed.");
        }
        return ResponseEntity.badRequest().body("Book not available.");
    }

    @PutMapping("/{id}/extend")
    public ResponseEntity<String> extendBorrow(@PathVariable int id) {
        Optional<BorrowedBook> optional = borrowRepo.findByBookId(id);
        if (optional.isPresent()) {
            BorrowedBook bb = optional.get();
            bb.setDueDate(bb.getDueDate().plusDays(7));
            borrowRepo.save(bb);
            return ResponseEntity.ok("Borrowing extended.");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<String> buyBook(@PathVariable int id) {
        Optional<Book> optional = bookRepo.findById(id);
        if (optional.isPresent() && optional.get().getQuantity() > 0) {
            Book book = optional.get();
            book.setQuantity(book.getQuantity() - 1);
            bookRepo.save(book);
            return ResponseEntity.ok("Book bought.");
        }
        return ResponseEntity.badRequest().body("Book not available.");
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<String> sellBook(@PathVariable int id) {
        Optional<Book> optional = bookRepo.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setQuantity(book.getQuantity() + 1);
            bookRepo.save(book);
            return ResponseEntity.ok("Book sold.");
        }
        return ResponseEntity.notFound().build();
    }
}
