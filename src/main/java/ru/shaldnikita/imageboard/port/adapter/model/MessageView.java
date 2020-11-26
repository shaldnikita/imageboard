package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;
import ru.shaldnikita.imageboard.domain.model.Data;

import java.util.List;

@lombok.Data
@AllArgsConstructor
public class MessageView {

    private long id;

    private String contentText;

    private List<Data> contentData;

    private String createDate;
}
