package com.project.db.services;

import com.project.db.entities.ConstraintType;

import javax.ejb.Local;

@Local
public interface ConstrainsTypeService {
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
