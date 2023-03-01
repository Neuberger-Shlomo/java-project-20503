package com.project.db.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



import com.project.db.entities.AvailableShifts;

@Stateless
public class AvailableShiftsDAOImpl implements AvailableShiftsDao  {

// hours in 24 hour format
    // problem if begin at 23:00 and end at 01:00 in the following day (can be a mistake- (start hour after end hour) or valid shift (that
    //begin at 23:00 and end at 01:00 in the following day)
    //consider to add only begining time of shift and for how long it is lasted to calculate end hour
    // add constraint for max hours for shift? did not see that in constrains table




    @PersistenceContext
    private EntityManager entityManager;
    public AvailableShiftsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AvailableShifts getById(int id) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.shiftsId=: shifts_id ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("shifts_id",id)
                .getSingleResult();
    }

    public void saveShift(AvailableShifts shift) {
        entityManager.persist(shift);
    }

   //
    public static boolean validate(AvailableShifts availableShifts)  {

        // Validate week number
        if (availableShifts.getWeekNumber() == null   || availableShifts.getDayNumber()==null)
            return false;

        // Validate employee count
        if (availableShifts.getEmpolyeeCount() == null || availableShifts.getManagerCount() == null)
            return false;

        // Validate legal start and end hour

        if(Integer.parseInt(availableShifts.getStartHour()) <= 0 || Integer.parseInt(availableShifts.getEndHour()) >= 24)
             return false;

        // Validate start and end hour
        if (Integer.parseInt(availableShifts.getStartHour()) >= Integer.parseInt(availableShifts.getEndHour())   )
            return false;
        // validate employee count
        if (availableShifts.getEmpolyeeCount() <= 0 || availableShifts.getManagerCount() < 0)
            return false;
        if(availableShifts.getWeekNumber() <= 0)
            return false;

         return true;
    }
    //DONE
    @Override
    public List<AvailableShifts> getAll() {
        return entityManager
                .createNamedQuery(Queries.ProfileQueries.GET_ALL,AvailableShifts.class)
                .getResultList();
    }

//DONE
    @Override
    public void insert(AvailableShifts u) {
        if(validate(u))  entityManager.persist(u);
    }
    //DONE
    @Override
    public AvailableShifts update(AvailableShifts u) {

        if(validate(u)) return entityManager.merge(u);
        else return null;
    }
    //DONE
    @Override
    public void delete(AvailableShifts u) {entityManager.remove(entityManager.merge(u));}





}


























/*
@Stateless
public class AvailableShiftsDao implements DAO<AvailableShifts> {

    @PersistenceContext
    private final EntityManager entityManager;

    public AvailableShiftsDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AvailableShifts> getAll() {
        return entityManager.createQuery("FROM AvailableShifts", AvailableShifts.class).getResultList();
    }
    @Override
    public List<AvailableShifts> getAll() {
        return entityManager
                .createNamedQuery(Queries.ProfileQueries.GET_ALL,Profile.class)
                .getResultList();
    }
    @Override
    public void insert(AvailableShifts availableShifts) {
        entityManager.persist(availableShifts);
    }

    @Override
    public AvailableShifts update(AvailableShifts availableShifts) {
        return entityManager.merge(availableShifts);
    }

    @Override
    public void delete(AvailableShifts availableShifts) {
        entityManager.remove(availableShifts);
    }

    @Override
    public boolean validate(AvailableShifts availableShifts) {
        // Implement validation logic here, return true if valid, false otherwise
        return true;
    }

    @Nullable
    @Override
    public AvailableShifts getById(@NotNull long id) {
        return entityManager.find(AvailableShifts.class, id);
    }
}



*/

