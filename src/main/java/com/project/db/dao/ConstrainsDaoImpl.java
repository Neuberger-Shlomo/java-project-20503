package com.project.db.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.project.db.entities.*;
import java.util.List;


@Stateless
public class ConstrainsDAOImpl implements ConstrainsDao {

    private EntityManager entityManager;

    public void ConstrainsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Constrains> getAll() {
        String query = "SELECT c FROM Constrains c";
        return entityManager.createQuery(query, Constrains.class).getResultList();
    }

    @Override
    public void insert(Constrains constrains) {
        if(validate(constrains))  entityManager.persist(constrains);
    }

    @Override
    public Constrains update(Constrains constrains) {
        if(validate(constrains)) return entityManager.merge(constrains);
        else return null;
    }

    @Override
    public void delete(Constrains constrains) {
        entityManager.remove(entityManager.merge(constrains));
    }

    public static boolean validate(Constrains constrains)  {

        // Validate week number
        if (constrains.getWeekNumber() == null )
            return false;


        // Validate start date before end date
        if(constrains.getStartDate().compareTo(constrains.getEndDate()) >= 0)
            return false;

        //week number is positive number
        if(constrains.getWeekNumber() <= 0)
            return false;

        return true;
    }

    @Override
    public Constrains getById(int id) {
        return entityManager
                .createQuery("SELECT c FROM Constrains c WHERE Constrains.constraintId=: constraint_id ORDER BY c.constraintId",Constrains.class)
                .setParameter("constraint_id",id)
                .getSingleResult();
    }
}