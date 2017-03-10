package com.example;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Alejandro Duarte
 */
@ApplicationScoped
@Repository
public interface PersonRepository extends EntityRepository<Person, Long> {

}