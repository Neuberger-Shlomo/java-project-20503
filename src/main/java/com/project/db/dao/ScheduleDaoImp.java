package com.project.db.dao;

import com.project.db.entities.*;
import com.sun.istack.internal.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDaoImp implements ScheduleDao{

    @PersistenceContext(unitName = "UsersPU")
    EntityManager entityManager;

    /**
     * Get all the Schedules in the database
     * @return A List of schedules
     */
    @Nullable
    @Override
    public List<Schedule> getAll() {
        return entityManager
                .createNamedQuery(Queries.ScheduleQueries.GET_ALL, Schedule.class)
                .getResultList();
    }

    /**
     * insert schedule to the database after validating the schedule
     * @param s to insert
     */
    @Override
    public void insert(Schedule s) {
        if(validate(s))
            entityManager.persist(s);
    }

    /**
     * Updates a Schedule in the database
     * @param s to update
     * @return newly change update or null
     */
    @Nullable
    @Override
    public Schedule update(Schedule s) {
        if(validate(s))
            return entityManager.merge(s);
        return null;
    }

    /**
     * delete a Schedule form the database
     * @param s to be deleted
     */
    @Override
    public void delete(Schedule s) {
        entityManager.remove(entityManager.merge(s));
    }

    /**
     * Validate that the request_id exists in Shift_Requests table
     * @return
     */
    private boolean validateSchedule(int reqId){
        if(entityManager.createQuery("SELECT r FROM ShiftsRequests r WHERE r.requestId =: reqId")
                .setParameter("reqId", reqId)
                .getResultList() == null)
            return false;
        return true;
    }

    /**
     * Validate the Schedule object
     * @param s to validate
     * @return if the Schedule is valid
     */
    @Override
    public boolean validate(Schedule s) {
        // TODO: add more validation if needed
        return validateSchedule(s.getRequestId());
    }

    /**
     * Get Schedule by id
     * @param id of the entry
     * @return the found Schedule or null
     */
    @Nullable
    @Override
    public Schedule getById(long id) {
        return entityManager
                .createNamedQuery(Queries.ScheduleQueries.GET_BY_ID,Schedule.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    /**
     * Gets Schedule by week
     * @param week
     * @return the found Schedule or null
     */
    @Nullable
    @Override
    public List<Schedule> GetByWeek(int week) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ScheduleQueries.GET_BY_WEEK,Schedule.class)
                .setParameter("week",week)
                .getResultList();
    }

    /**
     * Gets Schedule by week
     * @param requestId
     * @return the found Schedule or null
     */
    @Nullable
    @Override
    public Schedule getByRequest(int requestId) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ScheduleQueries.GET_BY_REQUEST,Schedule.class)
                .setParameter("reqId", requestId)
                .getSingleResult();
    }
}
