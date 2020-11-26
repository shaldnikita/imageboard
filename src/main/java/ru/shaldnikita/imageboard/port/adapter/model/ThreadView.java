package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import ru.shaldnikita.imageboard.domain.model.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@lombok.Data
@AllArgsConstructor
public class ThreadView {

    private long id;

    @NotNull
    private String title;

    @Nullable
    private String contentText;

    @Nullable
    private List<Data> contentData;

    @NotNull
    private String board;

    @NotNull
    private String createDate;

    @NotNull
    private List<MessageView> messages;

}
