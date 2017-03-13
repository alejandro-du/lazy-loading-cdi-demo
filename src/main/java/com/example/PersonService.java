package com.example;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author Alejandro Duarte
 */
@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository repository;

    public List<Person> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        return repository.findAll(offset, limit, sortOrders);
    }

    public int count() {
        return Math.toIntExact(repository.count());
    }

}
