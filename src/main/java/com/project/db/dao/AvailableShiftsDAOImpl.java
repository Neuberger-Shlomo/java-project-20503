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
    //return a row of AvailableShift matching parameter id
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


    /**
     * Validate AvailableShifts
     * @param availableShifts AvailableShifts
     */
    @Override
    public boolean validate(AvailableShifts availableShifts)  {

       if(checkNullAvailableShiftsFields(availableShifts) == false)
            return false;


        // Validate legal start hour
        if(availableShifts.getStartHour() <= 0 || availableShifts.getStartHour() >= 24)
            return false;
            //validate duration positive
         if (availableShifts.getDuration() <= 0)
             return false;
        // validate employee count
        if (availableShifts.getEmpolyeeCount() <= 0 || availableShifts.getManagerCount() < 0)
            return false;
        if(availableShifts.getWeekNumber() < 0)
            return false;

         return true;
    }

    /**
     * Retrive all AvailableShifts
     * @return list of all AvailableShifts
     */    @Override
    public List<AvailableShifts> getAll() {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p ORDER BY p.shiftsId",AvailableShifts.class)
                .getResultList();
    }

    /**
     * Insert new AvailableShifts
     * @param u AvailableShifts
     */    public void insert(AvailableShifts u) {
        if(validate(u))  entityManager.persist(u);
    }
    /**
     *
     * @param u AvailableShifts
     * @return updated AvailableShifts
     */    @Override
    public AvailableShifts update(AvailableShifts u) {

        if(validate(u)) return entityManager.merge(u);
        else return null;
    }
    //delete shift
    @Override
    public void delete(AvailableShifts u) {entityManager.remove(entityManager.merge(u));}
// return all shifts with this week number
    /**
     * Retrieve  all shifts that have week number equal to given week number
     * @param  weekNumber
     * @return list of all shifts that have week number equal to given week number
     */
    @Override
    public List<AvailableShifts> getByWeekNumber(int weekNumber) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.weekNumber=: week_number ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("week_number",weekNumber)
                .getResultList();
    }
    /**
     * Retrieve  all shifts that have day number equal to given day number
     * @param dayNumber
     * @return  list of all shifts that have day number equal to given day number
     */
    @Override
    public List<AvailableShifts> getByDayNumber(int dayNumber) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.dayNumber=: day_number ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("day_number",dayNumber)
                .getResultList();
    }
    //return all shifts that have duration equal to given duration
    /**
     * Retrieve  all shifts that have duration equal to given duration
     * @param duration
     * @return list of all shifts that have duration equal to given duration
     */
    @Override
    public List<AvailableShifts> getShiftsByDuration(int duration)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.duration=: duration ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("duration",duration)
                .getResultList();
    }
    /**
     * Retrieve all shifts that have duration less than given duration
     * @param duration
     * @return list of all shifts that have duration less then given duration
     */
    @Override
    public List<AvailableShifts> getLessThenDuration(int duration)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.duration<=: duration ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("duration",duration)
                .getResultList();
    }
    /**
     * Retrieve all shifts that begin after this hour
     * @param startHour
     * @return list of all shifts that begin after this hour
     */
    @Override
    public List<AvailableShifts> getShiftsBeginAfterHour(int startHour) {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.startHour>=: start_hour ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("start_hour",startHour)
                .getResultList();
    }
    /**
     * Retrieve all shifts that have this manager count
     * @param managerCount
     * @return list of all shifts that have this manager count
     */    @Override
    public List<AvailableShifts> getShiftsByManagerCount(int managerCount)  {
        return entityManager
                .createQuery("SELECT p FROM AvailableShifts p WHERE AvailableShifts.managerCount=: manager_count ORDER BY p.shiftsId",AvailableShifts.class)
                .setParameter("manager_count",managerCount)
                .getResultList();
    }
//return list of all shifts that have this employee count
    /**
     * Retrieve all shifts that have this employee count
     * @param    employeeCount
     * @return  list of all shifts that have this employee count
     */
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

