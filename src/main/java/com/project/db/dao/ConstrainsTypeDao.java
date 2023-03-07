package com.project.db.dao;
import com.project.db.entities.*;

import java.util.List;

public interface ConstrainsTypeDao extends DAO<ConstraintType> {

    //get constraint level by type id

    public int getConstraintLevelByTypeId(int typeId);

    //get constraint level by id
    public ConstraintType getById(long id);





    //validate constraint level
    public boolean validateConstraintLevel(int constraintLevel);
    //validate constraint type id
    public boolean validateConstraintTypeId(int constraintTypeId);
    //validate constraint description
    public boolean validateConstraintDescription(String constraintDescription);
    //validate constraint type
    public boolean validate(ConstraintType constraintType);
}
