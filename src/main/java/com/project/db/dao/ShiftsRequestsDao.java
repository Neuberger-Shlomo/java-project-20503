package com.project.db.dao;

import com.project.db.entities.AvailableShifts;
import com.project.db.entities.Schedule;
import com.project.db.entities.ShiftsRequests;
import com.project.db.entities.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ShiftsRequestsDao extends DAO<ShiftsRequests>{
    /**
     * Gets ShiftsRequests by shift id
     * @param id
     * @return List
     */
    List<ShiftsRequests> GetByShiftId(int id) throws SQLException;
    /**
     * Gets ShiftsRequests by user id
     * @param id
     * @return List
     */
    List<ShiftsRequests> getByUId(int id) throws SQLException;
    /**
     * Gets ShiftsRequests by timestamp
     * @param time
     * @return ShiftsRequests
     */
    ShiftsRequests getByTimestamp(Date time) throws SQLException;
    /**
     * Gets User by user id
     * @param shiftsRequests
     * @return User
     */
    User getUser(ShiftsRequests shiftsRequests) throws SQLException;
    /**
     * Gets AvailableShifts by Shift id
     * @param shiftsRequests
     * @return AvailableShifts
     */
    AvailableShifts getShift(ShiftsRequests shiftsRequests) throws SQLException;
}
