package com.project.db.dao;
import com.project.db.entities.*;

import java.util.List;

public interface AvailableShiftsDao extends DAO<AvailableShifts>{




//get all shifts of given week
public List<AvailableShifts> getByWeekNumber(int weekNumber);
//get all shifts of given day
public List<AvailableShifts> getByDayNumber(int dayNumber);
//get all shifts with duration equal to given time
public List<AvailableShifts> getShiftsByDuration(int duration);
//get all shifts with duration equal or last then given time
public List<AvailableShifts> getLessThenDuration(int duration);
//get all shifts begin at or after start hour
public List<AvailableShifts> getShiftsBeginAfterHour(int startHour);
//get all shifts with manager count equal to parameter
public List<AvailableShifts> getShiftsByManagerCount(int managerCount);
//get all shifts with employee count equal to parameter
public List<AvailableShifts> getShiftsByEmployeeCount(int employeeCount);

}
