package com.project.db.dao;

import javax.ejb.Stateless;
import javax.persistence.*;
import com.project.db.entities.*;
import com.sun.istack.internal.Nullable;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

/**
 * Implementation of the roles DataAccessObject
 */
public class RolesDaoImp implements RolesDao{
    /**
    * ROLE_LEVELS:
     * 1 - standard
     * 2 - manager
    */
    private static final int STANDARD = 1, MANAGER = 2;

    // TODO: add more levels if needed
    @PersistenceContext(unitName = "UsersPU")
    EntityManager entityManager;

    /**
     * Get all the Roles in the database
     * @return A List of roles
     */
    @Override
    public List<Roles> getAll() {
        return entityManager
                .createNamedQuery(Queries.RolesQueries.GET_ALL, Roles.class)
                .getResultList();
    }

    /**
     * insert role to the database after validating the role
     * @param r to insert
     */
    @Override
    public void insert(Roles r) {
        if(validate(r))
            entityManager.persist(r);
    }
    /**
     * Updates a Role in the database
     * @param r to update
     * @return newly change update or null
     */
    @Nullable
    @Override
    public Roles update(Roles r) {
        if(validate(r))
            return entityManager.merge(r);
        return null;
    }

    /**
     * delete a Roel form the database
     * @param r to be deleted
     */
    @Override
    public void delete(Roles r) {
        entityManager.remove(entityManager.merge(r));
    }

    /**
     * Validate that the Role comforts to request
     * @return
     */
    private boolean validateRole(int level, String name){
        if(name.equals("standard"))
            if(level == 1)
                return true;
        if(name.equals("manager"))
            if(level == 2)
                return true;
        return false;
    }

    /**
     * Validate the Roles object
     * @param r to validate
     * @return if the Roles is valid
     */
    @Override
    public boolean validate(Roles r) {
        // TODO: add more validation if needed
        return validateRole(r.getRoleLevel(), r.getRoleName());
    }

    /**
     * Get Role by id
     * @param id of the entry
     * @return the found User or null
     */
    @Nullable
    @Override
    public Roles getById(long id) {
        return entityManager
                .createNamedQuery(Queries.RolesQueries.GET_BY_ID,Roles.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Nullable
    @Override
    public List<Roles> getByLevel(int level) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.RolesQueries.GET_BY_LEVEL,Roles.class)
                .setParameter("level",level)
                .getResultList();
    }

    @Nullable
    @Override
    public List<Roles> getByName(String name) throws SQLException {
        return entityManager
                .createNamedQuery(Queries.RolesQueries.GET_BY_NAME,Roles.class)
                .setParameter("name",name)
                .getResultList();
    }
}
