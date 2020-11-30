package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 4)
    private DataBatches contentData;

    @NotNull
    private String board;

    @NotNull
    private String createDate;

    @NotNull
    @Size(max = 4)
    private List<MessageView> messages;

}
