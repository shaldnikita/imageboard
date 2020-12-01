package ru.shaldnikita.imageboard.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document("board")
public class Board {

    @Transient
    public static final String SEQUENCE_NAME = "boards_sequence";

    @Id
    private String id;

    private List<Thread> threads = new ArrayList<>();

    public Board(List<Thread> threads) {
        this.threads = threads;
    }

    public void addThread(Thread thread) {
        this.threads.add(thread);
    }


}
