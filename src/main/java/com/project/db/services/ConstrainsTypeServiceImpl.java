package com.project.db.services;

import com.project.db.dao.ConstrainsTypeDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import com.project.db.entities.*;
import com.project.servlet.ConstrainsType;

import java.util.List;
@Stateless
public class ConstrainsTypeServiceImpl {
    @Inject
    ConstrainsTypeDao dao;

    public List<ConstraintType> getAllConstrainsTypes() {
        return dao.getAll();
    }
    public ConstraintType getConstrainsTypeById(ConstraintType u) {
        return dao.getById(u.getTypeId());
    }
    public ConstraintType getConstrainsTypeById(Long id) {
        return dao.getById(id);
    }
    public ConstraintType addConstrainsType(ConstraintType u) {
        dao.insert(u);
        return u;
    }
    public ConstraintType updateConstrainsType(ConstraintType u) {
        return dao.update(u);
    }
    public void deleteConstrainsType(ConstraintType u) {
        dao.delete(u);
    }
    public void deleteConstrainsType(Long id) {
        ConstraintType u  = dao.getById(id);
        if(u != null) dao.delete(u);
    }

}
