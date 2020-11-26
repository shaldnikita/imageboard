package ru.shaldnikita.imageboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shaldnikita.imageboard.application.model.CreateMessageModel;
import ru.shaldnikita.imageboard.domain.exception.ThreadNotFoundException;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final ThreadRepository threadRepository;

    public Message createMessage(CreateMessageModel message) {
        var thread = threadRepository.findById(message.getThreadId()).orElseThrow(
                () -> new ThreadNotFoundException(message.getThreadId())
        );
        var newMessage = new Message(
                message.getContextText(),
                message.getContentData(),
                LocalDateTime.now()
        );
        thread.addMessage(newMessage);
        threadRepository.insert(thread);
        return newMessage;
    }
}
