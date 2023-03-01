package com.project.db.dao;

import javax.persistence.EntityManager;

import com.project.db.entities.*;
import java.util.List;


public class ConstrainsTypeDaoImpl implements ConstrainsTypeDao {

    private final EntityManager entityManager;


    public ConstrainsTypeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    final int MAX_CONSTRAINT_LEVEL = 3;
    final int MIN_CONSTRAINT_LEVEL = 1;
    final int MAX_CONSTRAINT_DESCRIPTION_LENGTH = 100;
    final int MAX_CONSTRAINT_TYPE_ID = 9;
    final int MIN_CONSTRAINT_TYPE_ID = 1;


    // get constraint level by type id
    public int getConstraintLevelByTypeId(int typeId) {
        return entityManager
                .createQuery("SELECT c FROM ConstraintType c WHERE ConstraintType.typeId=: constraint_type_id ORDER BY c.typeId",ConstraintType.class)
                .setParameter("constraint_type_id",typeId)
                .getSingleResult().getConstraintLevel();
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
    private boolean validate(ConstraintType constraintType) {
        if (constraintType == null) {
            return false;
        }
        if (validateConstraintDescription(constraintType.getConstraintDescription())
                && validateConstraintLevel(constraintType.getConstraintLevel(   ))&& validateConstraintTypeId(constraintType.getTypeId()))
            return true;

        // If typeId is null, create a default constraint type with default level
        if (constraintType.getTypeId() == null) {
            constraintType.setTypeId(7);
            constraintType.setConstraintLevel(1);
        }

        return true;
    }




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

    @Override
    public ConstraintType getById(int id) {
        return entityManager
                .createQuery("SELECT c FROM ConstraintType c WHERE ConstraintType.typeId=: constraint_type_id ORDER BY c.typeId",ConstraintType.class)
                .setParameter("constraint_type_id",id)
                .getSingleResult();
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