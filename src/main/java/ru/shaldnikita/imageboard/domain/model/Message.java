package ru.shaldnikita.imageboard.domain.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Document("message")
@lombok.Data
public class Message {

    @Transient
    public static final String SEQUENCE_NAME = "messages_sequence";

    @Id
    private long id;

    @Version
    private int version;

    boolean sage;

    private String contentText;

    private List<Data> contentData;

    @NotNull
    private LocalDateTime createDate;

    public Message(String contentText, List<Data> contentData, LocalDateTime createDate) {
        this.contentText = contentText;
        this.contentData = contentData;
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
