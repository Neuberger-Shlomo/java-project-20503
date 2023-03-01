package com.project.db.dao;


import com.project.db.entities.AvailableShifts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class Queries {
    public static class UserQueries{
        /**
         * Get all the users without any parameters
         */
        public final static String GET_ALL = "User.GetAll";
        /**
         * Retrieve Profile of the user.
         * notice this query need id of the user
         */
        public static final String GET_PROFILE_BY_ID = "User.GetProfile";
        /**
         * Retrieve user by id
         */
        public static final String GET_BY_ID = "User.GetById";
    }

    public static class ProfileQueries{
        /**
         * Get all profiles
         */
        public final static String GET_ALL = "Profile.GetAll";

    }

    public class AvailableShiftsQueries {

    }

    public static class ConstrainsQueries{

    }
    public static class ConstrainsTypeQueries{

    }





    }


