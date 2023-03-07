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

    public static class RolesQueries{
        /**
         * Get all the roles without any parameters
         */
        public final static String GET_ALL = "Roles.GetAll";
        /**
         * Retrieve user by Id
         */
        public static final String GET_BY_ID = "Roles.GetById";
        /**
         * Retrieve Role level by RoleId.
         * notice this query need id of the role
         */
        public static final String GET_BY_LEVEL = "Roles.GetByLevel";
        /**
         * Retrieve user by Name
         */
        public static final String GET_BY_NAME = "Roles.GetByName";
    }

    public static class ScheduleQueries{
        /**
         * Get all the roles without any parameters
         */
        public final static String GET_ALL = "Schedule.GetAll";
        /**
         * Retrieve user by Id
         */
        public static final String GET_BY_ID = "Schedule.GetById";
        /**
         * Retrieve Role level by RoleId.
         * notice this query need id of the role
         */
        public static final String GET_BY_WEEK = "Schedule.GetByWeek";
        /**
         * Retrieve user by Name
         */
        public static final String GET_BY_REQUEST = "Schedule.GetByRequest";
    }

    public static class ShiftsRequestsQueries{
        public final static String GET_ALL = "ShiftRequests.GetAll";
        public final static String GET_BY_ID = "ShiftRequests.GetByReqId";
        public final static String GET_BY_SHIFT_ID = "ShiftRequests.GetByShiftId";
        public final static String GET_SHIFT = "ShiftRequests.GetShift";
        public final static String GET_BY_UID = "ShiftRequests.GetByUId";
        public final static String GET_USER = "ShiftRequests.GetUser";
        public final static String GET_BY_TIMESTAMP = "ShiftRequests.GetByTimestamp";
    }
    public class AvailableShiftsQueries {
        public final static String GET_ALL = "AvailableShifts.GetAll";

    }

    public static class ConstrainsQueries{

    }
    public static class ConstrainsTypeQueries{

    }
}



