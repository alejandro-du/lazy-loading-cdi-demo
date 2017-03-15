package com.example;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Alejandro Duarte
 */
@ApplicationScoped
@Repository
public abstract class PersonRepository implements EntityRepository<Person, Long> {

    public abstract QueryResult<Person> findByEmailLike(String email);

}
