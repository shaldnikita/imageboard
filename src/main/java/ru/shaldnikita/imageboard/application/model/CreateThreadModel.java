package ru.shaldnikita.imageboard.application.model;

import org.springframework.lang.Nullable;
import ru.shaldnikita.imageboard.domain.model.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@lombok.Data
public class CreateThreadModel {

    @NotNull
    private final String title;

    @Nullable
    private final String contextText;

    @Nullable
    private final List<Data> contentData;

    @Nullable
    private String board;
}
