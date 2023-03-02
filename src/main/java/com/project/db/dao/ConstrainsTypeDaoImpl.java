package com.project.db.dao;

import javax.persistence.EntityManager;

import com.project.db.entities.*;
import java.util.List;


public class ConstrainsTypeDaoImpl implements ConstrainsTypeDao {

    private final EntityManager entityManager;


    public ConstrainsTypeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //to make sure  the constraint level is between 1-3
    final int MAX_CONSTRAINT_LEVEL = 3;
    final int MIN_CONSTRAINT_LEVEL = 1;
    //make sure that the constraint description is under 100 characters
    final int MAX_CONSTRAINT_DESCRIPTION_LENGTH = 100;
    //make sure you do not use too much constraint
    final int MAX_CONSTRAINT_TYPE_ID = 9;
    final int MIN_CONSTRAINT_TYPE_ID = 1;


    // get constraint level by type id
    public int getConstraintLevelByTypeId(int typeId) {
        return entityManager
                .createQuery("SELECT c FROM ConstraintType c WHERE ConstraintType.typeId=: constraint_type_id ORDER BY c.typeId",ConstraintType.class)
                .setParameter("constraint_type_id",typeId)
                .getSingleResult().getConstraintLevel();
    }
    @Override
    public ConstraintType getById(long id) {
        return entityManager
                .createQuery("SELECT c FROM ConstraintType c WHERE ConstraintType.typeId=: constraint_type_id ORDER BY c.typeId",ConstraintType.class)
                .setParameter("constraint_type_id",id)
                .getSingleResult();
    }

    @Override
    public ConstraintType getByTypeId(int typeId) {
        return entityManager
                .createQuery("SELECT c FROM ConstraintType c WHERE ConstraintType.typeId=: constraint_type_id ORDER BY c.typeId",ConstraintType.class)
                .setParameter("constraint_type_id",typeId)
                .getSingleResult();
    }


    public ConstraintType update(ConstraintType constraintType) {
        if (validate(constraintType))
            return entityManager.merge(constraintType);
        return null;
    }
    public boolean validateConstraintLevel(int constraintLevel) {
        if (constraintLevel < MIN_CONSTRAINT_LEVEL || constraintLevel > MAX_CONSTRAINT_LEVEL)
            return false;
        return true;
    }
    //public boolean validte constraint type id
    public boolean validateConstraintTypeId(int constraintTypeId) {
        if (constraintTypeId < MIN_CONSTRAINT_TYPE_ID || constraintTypeId > MAX_CONSTRAINT_TYPE_ID)
            return false;
        return true;
    }

    //public boolean validte constraint description
    public boolean validateConstraintDescription(String constraintDescription) {

        if (constraintDescription == null || constraintDescription.isEmpty()
                || constraintDescription.length() > MAX_CONSTRAINT_DESCRIPTION_LENGTH)
            return false;
        return true;
    }
    public boolean validate(ConstraintType constraintType) {
        if (constraintType == null) {
            return false;
        }
        if (validateConstraintDescription(constraintType.getConstraintDescription())
                && validateConstraintLevel(constraintType.getConstraintLevel(   ))&& validateConstraintTypeId(constraintType.getTypeId()))
            return false;



        return true;
    }
    @Override
    public List<ConstraintType> getAll() {
        String query = "SELECT c FROM ConstraintType c";
        return entityManager.createQuery(query, ConstraintType.class).getResultList();
    }
    @Override
    public void insert(ConstraintType u) {
        if(validate(u))  entityManager.persist(u);
    }
    //DONE

    //DONE
    @Override
    public void delete(ConstraintType u) {entityManager.remove(entityManager.merge(u));
    }



/*
    public enum constrainttype {
        SICKNESS(1),
        HOLIDAY(2),
        VACATION(3),
        PARENTAL_LEAVE(4),
        BOSS_APPROVED(5), OTHER(6), UNDEFINED(7);

        private final int value;

        constrainttype(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    } */
}