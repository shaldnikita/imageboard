package ru.shaldnikita.imageboard.port.adapter.model.mapper;

import ru.shaldnikita.imageboard.domain.model.Board;
import ru.shaldnikita.imageboard.port.adapter.model.BoardView;

import java.util.stream.Collectors;

public class BoardViewMapper {

    public static BoardView mapToView(Board board) {
        return new BoardView(
                board.getId(),
                board.getThreads()
                        .stream()
                        .peek(t -> t.setMessages(
                                t.getMessages()
                                        .stream()
                                        .limit(4)
                                        .collect(Collectors.toList())
                                )
                        )
                        .map(ThreadViewMapper::domainToViewThread)
                        .collect(Collectors.toList())
        );
    }

}
