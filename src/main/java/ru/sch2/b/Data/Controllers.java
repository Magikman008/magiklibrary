package ru.sch2.b.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controllers {
    @Autowired
    private BookAction bookAction;

    @Autowired
    private AuthorAction authorAction;

    @GetMapping(path = "/")
    public String test() {
        return "index";
    }

    @GetMapping(path = "/library")
    public String library() {
        return "library";
    }

    @RequestMapping(value = "/listauthor", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorAction.findAll();
    }

    @RequestMapping(value = "/listbook", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookAction.findAll();
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public @ResponseBody
    String addNewBook(@RequestParam String name, @RequestParam String img) {
        Book s = new Book();
        s.setName(name);
        s.setImg(img);
        bookAction.save(s);
        return "Книга добавлена";
    }

    @RequestMapping(value = "/addauthor", method = RequestMethod.POST)
    public @ResponseBody
    String addNewAuthor(@RequestParam String name, @RequestParam String lastname) {
        Author s = new Author();
        s.setName(name);
        s.setLastname(lastname);
        authorAction.save(s);
        return "Автор  добавлен";
    }

    @RequestMapping(value = "/addba", method = RequestMethod.POST)
    public @ResponseBody
    String addNewBA(@RequestParam String book, @RequestParam String author) {
        SQLSend.send(book, author);
        return "Связь добавлена";
    }
}
