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

    //check Constrains fields not null
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

    @Override
    public Constrains getById(long id) {
        return entityManager
                .createQuery("SELECT c FROM Constrains c WHERE Constrains.constraintId=: constraint_id ORDER BY c.constraintId",Constrains.class)
                .setParameter("constraint_id",id)
                .getSingleResult();
    }

    @Override
    public List<Constrains> getByWeekNumber(int weekNumber) {
        return entityManager
.createQuery("SELECT c FROM Constrains c WHERE Constrains.weekNumber=: week_number ",Constrains.class)
                .setParameter("week_number",weekNumber)
                .getResultList();
    }

    @Override
    public List<Constrains> getByStartDate(String startDate) {
    return entityManager
            .createQuery("SELECT c FROM Constrains c WHERE Constrains.startDate=: start_date ",Constrains.class)
                .setParameter("start_date",startDate)
                .getResultList();}

    @Override
    public List<Constrains> getByEndDate(String endDate) {
    return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.endDate=: end_date ",Constrains.class)
                .setParameter("end_date",endDate)
                .getResultList();    }

    @Override
    public List<Constrains> getPermanent() {
    return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.permanentFlag=: permanent_flag ",Constrains.class)
                .setParameter("permanent_flag",1)
                .getResultList();
    }
    @Override
    public List<Constrains> getByUid(int uid) {
    return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.uid=: uid ",Constrains.class)
                .setParameter("uid",uid)
                .getResultList();    }

    @Override
    public List<Constrains> getByTypeId(int typeId) {
      return entityManager
        .createQuery("SELECT c FROM Constrains c WHERE Constrains.typeId=: type_id ",Constrains.class)
                .setParameter("type_id",typeId)
                .getResultList();
    }
    @Override
    public User getUserByConstraint(Constrains constrains) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.uid=: uid",User.class)
                .setParameter("uid",constrains.getUid())
                .getSingleResult();
    }

}