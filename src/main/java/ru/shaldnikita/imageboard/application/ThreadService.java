package ru.shaldnikita.imageboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shaldnikita.imageboard.application.model.CreateThreadModel;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;

    public Thread createThread(CreateThreadModel thread) {
        var newThread = new Thread(
                thread.getTitle(),
                thread.getContextText(),
                thread.getContentData(),
                LocalDateTime.now()
        );
        return threadRepository.insert(newThread);
    }

}
