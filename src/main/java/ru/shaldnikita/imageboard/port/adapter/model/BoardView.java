package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
public class BoardView {
    private String name;
    /**
     * Threads contains <code>BoardViewMapper.BOARD_THREAD_MESSAGES_SIZE</code> limited messages list size.
     */
    private List<ThreadView> threads;
}
