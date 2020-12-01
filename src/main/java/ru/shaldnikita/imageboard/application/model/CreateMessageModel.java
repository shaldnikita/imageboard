package ru.shaldnikita.imageboard.application.model;

import ru.shaldnikita.imageboard.domain.model.Data;

import java.util.List;

@lombok.Data
public class CreateMessageModel {

    private final String contextText;

    private final List<Data> contentData;

}
