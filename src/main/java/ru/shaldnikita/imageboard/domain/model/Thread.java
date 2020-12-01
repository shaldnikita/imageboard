package ru.shaldnikita.imageboard.domain.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@lombok.Data
@Document("thread")
public class Thread {

    @Transient
    public static final String SEQUENCE_NAME = "threads_sequence";

    @Id
    private long id;

    @Version
    private int version;

    @NotNull
    private String title;

    @Nullable
    private String contentText;

    @Nullable
    private List<Data> contentData;

    @NotNull
    private String board;

    @NotNull
    private LocalDateTime createDate;

    private List<Message> messages = new ArrayList<>();

    public Thread(String title, String contextText, List<Data> contentData, String board, LocalDateTime createDate, List<Message> messages) {
        this.title = title;
        this.contentText = contextText;
        this.contentData = contentData;
        this.board = board;
        this.createDate = createDate;
        this.messages = messages;
    }

    public Thread(@NotNull String title, @Nullable String contentText, @Nullable List<Data> contentData, @NotNull String board, @NotNull LocalDateTime createDate) {
        this.title = title;
        this.contentText = contentText;
        this.contentData = contentData;
        this.board = board;
        this.createDate = createDate;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return id == thread.id;
    }

    @Override
    public int hashCode() {
        return (int)id;
    }
}
