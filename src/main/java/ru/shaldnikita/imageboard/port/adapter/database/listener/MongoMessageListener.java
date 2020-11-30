package ru.shaldnikita.imageboard.port.adapter.database.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.port.adapter.database.SequenceGenerator;

@Component
@RequiredArgsConstructor
//Does not work at all because we persist Thread, not Message
public class MongoMessageListener extends AbstractMongoEventListener<Message> {

    private final SequenceGenerator sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Message> event) {
        super.onBeforeConvert(event);
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Message.SEQUENCE_NAME));
        }
    }
}
