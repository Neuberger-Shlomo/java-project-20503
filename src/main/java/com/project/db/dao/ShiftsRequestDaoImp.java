package com.project.db.dao;

import com.project.db.entities.*;
import com.sun.istack.internal.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ShiftsRequestDaoImp implements ShiftsRequestsDao{
    @PersistenceContext(unitName = "UsersPU")
    EntityManager entityManager;

    /**
     * Get all the Shifts Requests in the database
     * @return A List of Shifts Requests
     */
    @Nullable
    @Override
    public List<ShiftsRequests> getAll() {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_ALL, ShiftsRequests.class)
                .getResultList();
    }

    /**
     * insert ShiftRequest to the database after validating
     * @param sr to insert
     */
    @Override
    public void insert(ShiftsRequests sr) {
        if(validate(sr))
            entityManager.persist(sr);
    }

    /**
     * Updates a Shift Request in the database
     * @param sr to update
     * @return newly change update or null
     */
    @Nullable
    @Override
    public ShiftsRequests update(ShiftsRequests sr) {
        if(validate(sr))
            return entityManager.merge(sr);
        return null;
    }

    /**
     * delete a Shift Request form the database
     * @param sr to be deleted
     */
    @Override
    public void delete(ShiftsRequests sr) {
        entityManager.remove(entityManager.merge(sr));
    }

    /**
     * Validate that the shift_id and uid exists in Available_shifts and User tables
     * @return
     */
    private boolean validateShiftsRequests(int userId, int shiftId){
        if(entityManager.createQuery("SELECT u FROM User u WHERE u.uid =: uid")
                .setParameter("uid", userId)
                .getResultList() == null)
            return false;
        if(entityManager.createQuery("SELECT a FROM ShiftsRequests a WHERE a.requestId =: shiftId")
                .setParameter("shiftId", shiftId)
                .getResultList() == null)
            return false;
        return true;
    }

    /**
     * Validate the Shift Requests object
     * @param sr to validate
     * @return if the Shift Requests is valid
     */
    @Override
    public boolean validate(ShiftsRequests sr) {
        // TODO: add more validation if needed
        return validateShiftsRequests(sr.getUid(), sr.getShiftId());
    }

    /**
     * Get Shift Requests by id
     * @param id of the entry
     * @return the found Shift Requests or null
     */
    @Nullable
    @Override
    public ShiftsRequests getById(long id) {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_BY_ID,ShiftsRequests.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    /**
     * Get Shift Requests by Shift id
     * @param id of the entry
     * @return the found Shift Requests or null
     */
    @Nullable
    @Override
    public List<ShiftsRequests> GetByShiftId(int id) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_BY_SHIFT_ID,ShiftsRequests.class)
                .setParameter("id",id)
                .getResultList();
    }

    /**
     * Get Shift Requests by uid
     * @param id of the entry
     * @return the found Shift Requests or null
     */
    @Nullable
    @Override
    public List<ShiftsRequests> getByUId(int id) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_BY_UID,ShiftsRequests.class)
                .setParameter("id",id)
                .getResultList();
    }

    /**
     * Get Shift Requests by timestamp
     * @param time of the entry
     * @return the found Shift Requests or null
     */
    @Nullable
    @Override
    public ShiftsRequests getByTimestamp(Date time) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_BY_TIMESTAMP,ShiftsRequests.class)
                .setParameter("time",time)
                .getSingleResult();
    }

    /**
     * Gets the Shift Request User
     * @param shiftsRequests whom User will be retrieved
     * @return the retrieved User
     */
    @Nullable
    @Override
    public User getUser(ShiftsRequests shiftsRequests) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_USER, User.class)
                .setParameter("id",shiftsRequests.getUid())
                .getSingleResult();
    }

    /**
     * Gets the Shift Request AvailableShift
     * @param shiftsRequests whom AvailableShift will be retrieved
     * @return the retrieved AvailableShift
     */
    @Nullable
    @Override
    public AvailableShifts getShift(ShiftsRequests shiftsRequests) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.ShiftsRequestsQueries.GET_SHIFT, AvailableShifts.class)
                .setParameter("id",shiftsRequests.getShiftId())
                .getSingleResult();
    }
}
