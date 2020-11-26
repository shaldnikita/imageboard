package ru.shaldnikita.imageboard.port.adapter.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.domain.repository.MessageRepository;

public interface MongoMessageRepository extends MongoRepository<Message, Long>, MessageRepository {

    @Override
    default Message insert(Message message) {
        return save(message);
    }
}
