package ru.shaldnikita.imageboard.port.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shaldnikita.imageboard.domain.model.Board;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;
import ru.shaldnikita.imageboard.port.adapter.model.mapper.BoardViewMapper;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final ThreadRepository threadRepository;

    @GetMapping("/{boardName}")
    public String getBoard(@PathVariable String boardName, Pageable pageable, Model model) {
        var threads = threadRepository.findByBoardOrderByCreateDateDesc(boardName, pageable);

        var board = new Board(
                threads.toList()
        );

        var boardView = BoardViewMapper.mapToView(board);

        model.addAttribute("board", boardView);
        return "board";
    }

}
