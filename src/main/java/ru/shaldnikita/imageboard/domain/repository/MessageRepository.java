package ru.shaldnikita.imageboard.domain.repository;

import ru.shaldnikita.imageboard.domain.model.Message;

import java.util.Optional;

public interface MessageRepository {
    public Optional<Message> findById(long id);
    public Message insert(Message message);
}
