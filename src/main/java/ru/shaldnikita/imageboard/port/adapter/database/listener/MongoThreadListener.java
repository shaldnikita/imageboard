package ru.shaldnikita.imageboard.port.adapter.database.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.port.adapter.database.SequenceGenerator;

@Component
@RequiredArgsConstructor
public class MongoThreadListener extends AbstractMongoEventListener<Thread> {

    private final SequenceGenerator sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Thread> event) {
        super.onBeforeConvert(event);
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Thread.SEQUENCE_NAME));
        }
        event.getSource().getMessages().stream()
                .filter(m -> m.getId() < 1)
                .forEach(m -> m.setId(sequenceGenerator.generateSequence(Message.SEQUENCE_NAME)));
    }
}

