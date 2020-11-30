package ru.shaldnikita.imageboard.port.adapter.model.mapper;

import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.port.adapter.model.DataBatches;
import ru.shaldnikita.imageboard.port.adapter.model.MessageView;

import java.time.format.DateTimeFormatter;

public class MessageViewMapper {

    public static MessageView mapToView(Message m) {
        return new MessageView(
                m.getId(),
                m.getContentText(),
                new DataBatches(m.getContentData()),
                m.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
        );
    }

}
