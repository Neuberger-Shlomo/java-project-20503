package com.project.db.dao;
import com.project.db.entities.*;

import java.util.List;




public interface ConstrainsDao extends DAO<Constrains>{
    //get all constrains of given week
    public List<Constrains> getByWeekNumber(int weekNumber);
    //get all constrains begin at start date
    public List<Constrains> getByStartDate(String startDate);
    //get all constrains that end at end date
    public List<Constrains> getByEndDate(String endDate);
    //get all constrains that are permanent
    public List<Constrains> getPermanent();
    //get all constrains with this uid
    public List<Constrains> getByUid(int uid);
    //get all constrains with this type id
    public List<Constrains> getByTypeId(int typeId);
    //get user with this constraint
    public User getUserByConstraint (Constrains constrains);


}
