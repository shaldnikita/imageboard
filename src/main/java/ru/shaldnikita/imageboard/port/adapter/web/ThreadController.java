package ru.shaldnikita.imageboard.port.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.shaldnikita.imageboard.application.ThreadService;
import ru.shaldnikita.imageboard.application.model.CreateThreadModel;
import ru.shaldnikita.imageboard.domain.repository.ThreadRepository;

import static ru.shaldnikita.imageboard.port.adapter.model.mapper.ThreadViewMapper.domainToViewThread;

@Controller
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadService threadService;

    private final ThreadRepository threadRepository;

    @PostMapping("/{board}")
    public RedirectView createThread(@PathVariable String board, @ModelAttribute CreateThreadModel createThreadModel) {
        var thread = threadService.createThread(createThreadModel);
        return new RedirectView("/" + thread.getId());
    }

    //TODO dynamic path conflicts with /styles/styles.css endpoint
    @GetMapping("/b/{threadId}")
    public String getThread(@PathVariable String board,
                            @PathVariable long threadId,
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
}
