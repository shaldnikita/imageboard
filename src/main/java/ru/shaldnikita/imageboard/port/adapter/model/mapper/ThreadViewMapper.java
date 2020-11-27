package ru.shaldnikita.imageboard.port.adapter.model.mapper;

import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.port.adapter.model.MessageView;
import ru.shaldnikita.imageboard.port.adapter.model.ThreadView;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ThreadViewMapper {

    public static ThreadView domainToViewThread(Thread thread) {
        return new ThreadView(
                thread.getId(),
                thread.getTitle(),
                thread.getContentText(),
                thread.getContentData(),
                thread.getBoard(),
                thread.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                thread.getMessages().stream()
                        .map(m -> new MessageView(
                                m.getId(),
                                m.getContentText(),
                                m.getContentData(),
                                m.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                        )).collect(Collectors.toList())
        );
    }
}
