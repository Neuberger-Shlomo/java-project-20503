package com.project.db.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.project.db.entities.*;
import jdk.jfr.Timestamp;

import java.util.List;


@Stateless
public class ConstrainsDAOImpl implements ConstrainsDao {

    private EntityManager entityManager;

    public void ConstrainsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * Get all Constrains for the database
     * @return  List of all the Constrains
     */
    @Override
    public List<Constrains> getAll() {
        String query = "SELECT c FROM Constrains c";
        return entityManager.createQuery(query, Constrains.class).getResultList();
    }
    /**
     *  Insert single Constrains after validation
     * @param constrains to insert
     */
    @Override
    public void insert(Constrains constrains) {
        if(validate(constrains))  entityManager.persist(constrains);
    }
    /**
     * Update a given Constrains if valid null if not
     * @param constrains to update
     * @return the Constrains or null if unable to update
     */
    @Override
    public Constrains update(Constrains constrains) {
        if(validate(constrains)) return entityManager.merge(constrains);
        else return null;
    }
    /**
     * Delete a Constrains
     * @param constrains to be deleted
     */
    @Override
    public void delete(Constrains constrains) {
        entityManager.remove(entityManager.merge(constrains));
    }


    /**
     * Check if all the fields of the constrains are not null
     * @param constrains
     * @return  true if all the fields are not null, false otherwise
     */
    public boolean checkNullConstrainsFields(Constrains constrains){
        if(constrains.getConstraintId() == null )
            return false;
        if(constrains.getWeekNumber() == null )
            return false;
        if(constrains.getStartDate() == null )
            return false;
        if(constrains.getEndDate() == null )
            return false;
        if(constrains.getData() == null )
            return false;
        if (constrains.getConstraintId() == null)
            return false;
        if (constrains.getPermanentFlag()== null)
            return false;

        return true;
    }


      /**
         * Validate the constrains
         * @param constrains to validate
         * @return true if valid, false otherwise
         */
    @Override
    public boolean validate(Constrains constrains)  {

        //check Constrains fields not null
if(checkNullConstrainsFields(constrains) == false)
            return false;
        // Validate start date before end date
        if(constrains.getStartDate().compareTo(constrains.getEndDate()) >= 0)
            return false;

        //week number is positive number
        if(constrains.getWeekNumber() <= 0)
            return false;

        return true;
    }

    /**
     * Get Constrains by id
      * @param id
     * @return Constrains with the given id
     */
    @Override
    public Constrains getById(long id) {
        return entityManager
                .createQuery("SELECT c FROM Constrains c WHERE Constrains.constraintId=: constraint_id ORDER BY c.constraintId",Constrains.class)
                .setParameter("constraint_id",id)
                .getSingleResult();
    }
    /**
     * Get Constrains by week number
     * @param weekNumber
     * @return Constrains with the given week number
     */
    @Override
    public List<Constrains> getByWeekNumber(int weekNumber) {
        return entityManager
.createQuery("SELECT c FROM Constrains c WHERE Constrains.weekNumber=: week_number ",Constrains.class)
                .setParameter("week_number",weekNumber)
                .getResultList();
    }
    /**
     * Get Constrains by start date
     * @param startDate
     * @return Constrains with the given start date
     */
    @Override
    public List<Constrains> getByStartDate(Timestamp startDate) {
    return entityManager
            .createQuery("SELECT c FROM Constrains c WHERE Constrains.startDate=: start_date ",Constrains.class)
                .setParameter("start_date",startDate)
                .getResultList();}
    /**
     * Get Constrains by end date
     * @param endDate
     * @return Constrains with the given endDate
     */
    @Override
    public List<Constrains> getByEndDate(Timestamp endDate) {
    return entityManager
            .createQuery("SELECT c FROM Constrains c WHERE Constrains.endDate=: end_date ",Constrains.class)
                .setParameter("end_date",endDate)
                .getResultList();
    }

    /**
     * Get Constrains that are permanent
     * @return list of Constrains that are permanent
     */
    @Override
    public List<Constrains> getPermanent() {
    return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.permanentFlag=: permanent_flag ",Constrains.class)
                .setParameter("permanent_flag",1)
                .getResultList();
    }
    /**
     * Get list of Constrains by user id
     * @param uid
     * @return list of Constrains with given user id
     */
    @Override
    public List<Constrains> getByUid(int uid) {
    return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.uid=: uid ",Constrains.class)
                .setParameter("uid",uid)
                .getResultList();    }
/**
     * Get list of Constrains by type id
     * @param typeId
     * @return list of Constrains with given type id
     */
    @Override
    public List<Constrains> getByTypeId(int typeId) {
      return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.typeId=: type_id ",Constrains.class)
                .setParameter("type_id",typeId)
                .getResultList();
    }
    /**
     * Get user by constrains
     * @param constrains
     * @return user with the given constrains
     */
    @Override
    public User getUserByConstraint(Constrains constrains) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.uid=: uid",User.class)
                .setParameter("uid",constrains.getUid())
                .getSingleResult();
    }

}