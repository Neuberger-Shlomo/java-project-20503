package com.project.db.dao;

import com.project.db.entities.Roles;
import com.project.db.entities.Schedule;

import java.sql.SQLException;
import java.util.List;
public interface ScheduleDao extends DAO<Schedule>{
    /**
     * Gets Schedule by week
     * @param week
     * @return List
     */
    List<Schedule> GetByWeek(int week) throws SQLException;
    /**
     * Gets Roles by Request
     * @param request
     * @return List
     */
    Schedule getByRequest(int request) throws SQLException;
}
