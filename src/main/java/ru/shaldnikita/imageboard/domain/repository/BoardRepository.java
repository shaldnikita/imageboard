package ru.shaldnikita.imageboard.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.shaldnikita.imageboard.domain.model.Board;

public interface BoardRepository extends MongoRepository<Board, Long> {
}
