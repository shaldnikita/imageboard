package ru.shaldnikita.imageboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import ru.shaldnikita.imageboard.domain.model.Data;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.domain.repository.MessageRepository;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ImageboardApplication implements CommandLineRunner {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Value("classpath:meme.jpg")
    private Resource res;

    public static void main(String[] args) {
        SpringApplication.run(ImageboardApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        var file = res.getInputStream().readAllBytes();

        var thread = new Thread(
                "Text thread",
                "sup 5051ch",
                List.of(new Data(
                                "meme.jpg",
                                file.length,
                                file
                        ),
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        )),
                "b",
                LocalDateTime.now(),
                new ArrayList<>()
        );
        var message1 = new Message(
                "sage",
                List.of(new Data(
                        "meme.jpg",
                        file.length,
                        file
                )),
                LocalDateTime.now()
        );

        var message2 = new Message(
                "not sage",
                List.of(
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        ),
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        ),
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        ),
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        ),
                        new Data(
                                "meme.jpg",
                                file.length,
                                file
                        )
                ),
                LocalDateTime.now()
        );

        thread.addMessage(message1);
        thread.addMessage(message2);
        threadRepository.insert(thread);
    }
}
