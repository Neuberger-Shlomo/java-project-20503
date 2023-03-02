package com.project.db.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



import com.project.db.entities.AvailableShifts;

@Stateless
public class AvailableShiftsDAOImpl implements AvailableShiftsDao  {





    @PersistenceContext
    private EntityManager entityManager;
    public AvailableShiftsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AvailableShifts getById(long id) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.shiftsId=: shifts_id ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("shifts_id",id)
                .getSingleResult();
    }
    //check all AvailableShifts fields not null
    public boolean checkNullAvailableShiftsFields(AvailableShifts availableShifts){
        if(availableShifts.getShiftsId() == null )
            return false;
        if(availableShifts.getWeekNumber() == null )
            return false;
        if(availableShifts.getDayNumber() == null )
            return false;
        if(availableShifts.getStartHour() == null )
            return false;
        if(availableShifts.getDuration() == null )
            return false;
        if(availableShifts.getEmpolyeeCount() == null )
            return false;
        if(availableShifts.getManagerCount() == null )
            return false;
        return true;
    }



    @Override
    public boolean validate(AvailableShifts availableShifts)  {

       if(checkNullAvailableShiftsFields(availableShifts) == false)
            return false;


        // Validate legal start and end hour
        if(Integer.parseInt(availableShifts.getStartHour()) <= 0 || Integer.parseInt(availableShifts.getDuration()) >= 24)
             return false;
//VALIDATE DURATOIN POSITIVE-----------

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
                .createQuery("SELECT p FROM AvailableShifts p ORDER BY p.shiftsId",AvailableShifts.class)
                .getResultList();
    }

    //insert to shift    @Override
    public void insert(AvailableShifts u) {
        if(validate(u))  entityManager.persist(u);
    }
   //update shift
    @Override
    public AvailableShifts update(AvailableShifts u) {

        if(validate(u)) return entityManager.merge(u);
        else return null;
    }
    //delete shift
    @Override
    public void delete(AvailableShifts u) {entityManager.remove(entityManager.merge(u));}
// return all shifts with this week number
    @Override
    public List<AvailableShifts> getByWeekNumber(int weekNumber) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.weekNumber=: week_number ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("week_number",weekNumber)
                .getResultList();
    }
//return all shifts with this day number
    @Override
    public List<AvailableShifts> getByDayNumber(int dayNumber) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.dayNumber=: day_number ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("day_number",dayNumber)
                .getResultList();
    }
    //return all shifts that have duration equal to given duration
    @Override
    public List<AvailableShifts> getShiftsByDuration(int duration)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.duration=: duration ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("duration",duration)
                .getResultList();
    }
    //return all shifts that have duration less then given duration
    @Override
    public List<AvailableShifts> getLessThenDuration(int duration)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.duration<=: duration ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("duration",duration)
                .getResultList();
    }
// return all shifts that begin after this hour
    @Override
    public List<AvailableShifts> getShiftsBeginAfter(int startHour) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.startHour>=: start_hour ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("start_hour",startHour)
                .getResultList();
    }
//return all shifts that have this manager count
    @Override
    public List<AvailableShifts> getShiftsByManagerCount(int managerCount)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.managerCount=: manager_count ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("manager_count",managerCount)
                .getResultList();
    }
//return list of all shifts that have this employee count
    @Override
    public List<AvailableShifts> getShiftsByEmployeeCount(int employeeCount)    {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.empolyeeCount=: employee_count ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("employee_count",employeeCount)
                .getResultList();
    }
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

