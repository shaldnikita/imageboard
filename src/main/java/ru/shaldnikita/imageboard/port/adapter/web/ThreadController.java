package ru.shaldnikita.imageboard.port.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.shaldnikita.imageboard.application.ThreadService;
import ru.shaldnikita.imageboard.application.model.CreateThreadModel;
import ru.shaldnikita.imageboard.domain.model.Thread;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;
import ru.shaldnikita.imageboard.port.adapter.model.MessageView;
import ru.shaldnikita.imageboard.port.adapter.model.ThreadView;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadService threadService;

    private final ThreadRepository threadRepository;

    @PostMapping
    public RedirectView createThread(@ModelAttribute CreateThreadModel createThreadModel) {
        var thread = threadService.createThread(createThreadModel);
        return new RedirectView("/" + thread.getId());
    }

    @GetMapping("/{threadId}")
    public String getThread(@PathVariable long threadId,
                            Model model) {
        var maybeThread = threadRepository.findById(threadId);
        if (maybeThread.isPresent()) {
            var thread = maybeThread.get();
            model.addAttribute("thread", domainToViewThread(thread));
            return "thread";
        } else {
            return "error";
        }
    }

    private static ThreadView domainToViewThread(Thread thread) {
        return new ThreadView(
                thread.getId(),
                thread.getTitle(),
                thread.getContentText(),
                thread.getContentData(),
                thread.getBoard(),
                thread.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                thread.getMessages().stream()
                        .map(m -> new MessageView(
                                m.getId(),
                                m.getContentText(),
                                m.getContentData(),
                                m.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                        )).collect(Collectors.toList())
        );
    }
}
