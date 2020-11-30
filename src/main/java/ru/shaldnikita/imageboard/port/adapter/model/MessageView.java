package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class MessageView {

    private long id;

    private String contentText;

    private DataBatches contentData;

    private String createDate;
}
