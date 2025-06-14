package com.srikarprabhaskandagatla.bookstack.repository;

import com.srikarprabhaskandagatla.bookstack.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    Optional<BorrowedBook> findByBookId(int bookId);
}