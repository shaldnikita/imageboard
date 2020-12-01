package ru.shaldnikita.imageboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.shaldnikita.imageboard.application.model.CreateMessageModel;
import ru.shaldnikita.imageboard.application.model.CreateThreadModel;
import ru.shaldnikita.imageboard.domain.exception.ThreadNotFoundException;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;
import ru.shaldnikita.imageboard.port.adapter.database.SequenceGenerator;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;

    private final SequenceGenerator sequenceGenerator;

    public Thread createThread(String board, CreateThreadModel thread) {
        var newThread = new Thread(
                thread.getTitle(),
                thread.getContextText(),
                thread.getContentData(),
                board,
                LocalDateTime.now()
        );
        return threadRepository.insert(newThread);
    }

    /**
     * Говнокод :)
     * Не пытайся вникать, как он работает (не уверен даже, что он вообще работает как нужно)
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public long addMessageToThread(String board, long threadId, CreateMessageModel message) {
        return threadRepository.findByIdAndBoard(threadId, board)
                .map(t -> {
                    var newMessage = new Message(
                            message.getContextText(),
                            message.getContentData(),
                            LocalDateTime.now()
                    );
                    t.addMessage(newMessage);
                    threadRepository.insert(t);
                    // Достаем последний заинсерченный айдишник (может поменяться в другой транзации и тогда ебанет)
                    return sequenceGenerator.getLastSequenceForMessage();

        }).orElseThrow(() -> new ThreadNotFoundException(threadId, board));

    }

}
