package ru.shaldnikita.imageboard.port.adapter.database;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.shaldnikita.imageboard.domain.model.Message;
import ru.shaldnikita.imageboard.port.adapter.database.model.DatabaseSequence;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@RequiredArgsConstructor
public class SequenceGenerator {

    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long getLastSequenceForMessage() {
        return Objects.requireNonNull(mongoOperations.findOne(query(where("_id").is(Message.SEQUENCE_NAME)),
                DatabaseSequence.class)).getSeq();
    }

}
