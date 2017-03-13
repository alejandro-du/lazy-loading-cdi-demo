package com.example;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alejandro Duarte
 */
@ApplicationScoped
@Repository
public abstract class PersonRepository implements FullEntityRepository<Person, Long> {

    public List<Person> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root r = query.from(Person.class);

        List<Order> orders = sortOrders.entrySet().stream()
                .map(e -> e.getValue() ? builder.asc(r.get(e.getKey())) : builder.desc(r.get(e.getKey())))
                .collect(Collectors.toList());

        query.orderBy(orders);

        return entityManager.createQuery(query).getResultList();
    }

}