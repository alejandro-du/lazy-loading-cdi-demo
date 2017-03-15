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
        // there's a bug in Delta Spike Data that prevent us from using a repository method without having a filter
        // predicate (such as findBy) and return a QueryResult at the same time. As a workaround we defined a
        // repository with a filter predicate and pass "%" in order to query all the results.
        QueryResult<Person> result = repository.findByEmailLike("%");
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
