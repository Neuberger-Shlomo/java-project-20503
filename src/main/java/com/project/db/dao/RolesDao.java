package com.project.db.dao;
import com.project.db.entities.*;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public interface RolesDao extends DAO<Roles>{

    /**
     * Gets Roles by level
     * @param level
     * @return List
     */
    List<Roles> getByLevel(int level) throws SQLException;
    /**
     * Gets Roles by name
     * @param name
     * @return List
     */
    List<Roles> getByName(String name) throws SQLException;

}
