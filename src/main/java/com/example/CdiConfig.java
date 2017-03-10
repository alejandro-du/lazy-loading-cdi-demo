package com.example;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

    @Produces
    @PersistenceContext(unitName = "DemoPU")
    @Dependent
    public EntityManager entityManager;
}
