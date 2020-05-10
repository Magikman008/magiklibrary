package ru.sch2.b.Data;

import org.springframework.data.repository.CrudRepository;

public interface BookAction extends CrudRepository<Book, Integer> {
}
