package ru.shaldnikita.imageboard.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.shaldnikita.imageboard.domain.model.Thread;

import java.util.Optional;

public interface ThreadRepository {

    public Optional<Thread> findById(long id);
    public Page<Thread> findByBoardOrderByCreateDateDesc(String board, Pageable pageable);
    public Optional<Thread> findByIdAndBoard(long id, String board);
    //public Page<Thread> findOrderByCreateDateDesc(Pageable pageable);
    public Thread insert(Thread thread);
}
