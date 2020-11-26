package ru.shaldnikita.imageboard.port.adapter.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;

public interface MongoThreadRepository extends ThreadRepository, MongoRepository<Thread, Long> {

    @Override
    default Thread insert(Thread thread) {
        return save(thread);
    }
}
