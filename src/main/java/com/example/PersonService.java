package com.example;

import org.apache.deltaspike.data.api.QueryResult;

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
        QueryResult<Person> result = repository.findBy();
        result.firstResult(offset).maxResults(limit);
        sortOrders.entrySet().stream().forEach(order -> {
            if (order.getValue()) {
                result.orderAsc(order.getKey());
            } else {
                result.orderDesc(order.getKey());
            }
        });

        return result.getResultList();
    }

    public int count() {
        return Math.toIntExact(repository.count());
    }

}
