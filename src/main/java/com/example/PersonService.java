package com.example;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Alejandro Duarte
 */
@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository repository;

    public List<Person> findAll(int offset, int limit) {
        return repository.findAll(offset, limit);
    }

    public int count() {
        return Math.toIntExact(repository.count());
    }

}
