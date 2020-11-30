package ru.shaldnikita.imageboard.port.adapter.model;

import lombok.AllArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
public class BoardView {
    private String name;
    private List<ThreadView> threads;
}
