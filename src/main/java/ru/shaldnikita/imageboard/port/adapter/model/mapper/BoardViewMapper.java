package ru.shaldnikita.imageboard.port.adapter.model.mapper;

import ru.shaldnikita.imageboard.domain.model.Board;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.port.adapter.model.BoardView;
import ru.shaldnikita.imageboard.port.adapter.model.ThreadView;

import java.util.List;
import java.util.stream.Collectors;

public class BoardViewMapper {

    private final static int BOARD_THREAD_MESSAGES_SIZE = 4;

    public static BoardView mapToView(Board board) {
        var threads = buildThreadsViews(board.getThreads());
        return new BoardView(
                board.getId(),
                threads
        );
    }

    private static List<ThreadView> buildThreadsViews(List<Thread> threads) {
        return threads
                .stream()
                .peek(t -> t.setMessages(
                        t.getMessages()
                                .stream()
                                .limit(BOARD_THREAD_MESSAGES_SIZE)
                                .collect(Collectors.toList())
                        )
                )
                .map(ThreadViewMapper::domainToViewThread)
                .collect(Collectors.toList());
    }

}
