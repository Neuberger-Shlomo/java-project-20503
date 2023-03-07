package com.project.db.entities;

import com.project.db.dao.Queries;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_ALL ,query = "SELECT sr FROM ShiftsRequests sr"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_BY_ID, query = "SELECT sr FROM ShiftsRequests sr WHERE sr.requestId = :id"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_BY_SHIFT_ID, query = "SELECT sr FROM ShiftsRequests sr WHERE sr.shiftId = :id"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_BY_UID, query = "SELECT sr FROM ShiftsRequests sr WHERE sr.uid = :id"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_BY_TIMESTAMP, query = "SELECT s FROM ShiftsRequests s WHERE s.timeStamp = :time ORDER BY s.requestId"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_USER, query = "SELECT u FROM User u WHERE u.uid = :id"),
        @NamedQuery(name = Queries.ShiftsRequestsQueries.GET_SHIFT, query = "SELECT a FROM AvailableShifts a WHERE a.shiftsId = :id"),
})
@Table(name = "Shifts_requests", schema = "public", catalog = "project")
public class ShiftsRequests {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Request_id", nullable = false)
    private Integer requestId;
    @Basic
    @Column(name = "shift_id", nullable = false)
    private Integer shiftId;
    @Basic
    @Column(name = "uid", nullable = false)
    private Integer uid;
    @Basic
    @Column(name = "time_stamp", nullable = false)
    private Timestamp timeStamp;
    @OneToMany(mappedBy = "shiftsRequestsByRequestId")
    private Collection<Schedule> schedulesByRequestId;
    @ManyToOne
    @JoinColumn(name = "shift_id",
            referencedColumnName = "shifts_id",
            nullable = false,
            insertable = false,
            updatable = false)
    private AvailableShifts availableShiftsByShiftId;
    @ManyToOne
    @JoinColumn(name = "uid",
            referencedColumnName = "uid",
            nullable = false,
            insertable = false,
            updatable = false)
    private User userByUid;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiftsRequests that = (ShiftsRequests) o;
        return Objects.equals(requestId, that.requestId) && Objects.equals(shiftId, that.shiftId) && Objects.equals(uid, that.uid) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, shiftId, uid, timeStamp);
    }

    public Collection<Schedule> getSchedulesByRequestId() {
        return schedulesByRequestId;
    }

    public void setSchedulesByRequestId(Collection<Schedule> schedulesByRequestId) {
        this.schedulesByRequestId = schedulesByRequestId;
    }

    public AvailableShifts getAvailableShiftsByShiftId() {
        return availableShiftsByShiftId;
    }

    public void setAvailableShiftsByShiftId(AvailableShifts availableShiftsByShiftId) {
        this.availableShiftsByShiftId = availableShiftsByShiftId;
    }

    public User getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(User userByUid) {
        this.userByUid = userByUid;
    }
}
