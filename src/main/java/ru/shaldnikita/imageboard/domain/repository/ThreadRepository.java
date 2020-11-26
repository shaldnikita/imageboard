package ru.shaldnikita.imageboard.domain.repository;

import ru.shaldnikita.imageboard.domain.model.Thread;

import java.util.Optional;

public interface ThreadRepository {

    public Optional<Thread> findById(long id);
    //public Page<Thread> findByBoardOrderByCreateDateDesc(String board, Pageable pageable);
    //public Page<Thread> findOrderByCreateDateDesc(Pageable pageable);
    public Thread insert(Thread thread);
}
