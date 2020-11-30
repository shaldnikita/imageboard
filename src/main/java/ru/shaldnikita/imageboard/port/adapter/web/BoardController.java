package ru.shaldnikita.imageboard.port.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;
import ru.shaldnikita.imageboard.port.adapter.model.BoardView;
import ru.shaldnikita.imageboard.port.adapter.model.mapper.ThreadViewMapper;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final ThreadRepository threadRepository;

    @GetMapping("/{board}")
    public String getBoard(@PathVariable String board, Pageable pageable, Model model) {
        var threads = threadRepository.findByBoardOrderByCreateDateDesc(board, pageable)
                .map(ThreadViewMapper::domainToViewThread).toList();

        model.addAttribute("board", new BoardView(board, threads));
        return "board";
    }

}
