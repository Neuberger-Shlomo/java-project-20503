package com.project.db.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "AvailableShifts", schema = "public", catalog = "project")
public class AvailableShifts {
    @Basic
    @Column(name = "week_number", nullable = false)
    private Integer weekNumber;
    @Basic
    @Column(name = "day_number", nullable = false)
    private Integer dayNumber;
    @Basic
    @Column(name = "start_hour", nullable = false)
    private Integer startHour;

    @Basic
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @Basic
    @Column(name = "empolyee_count", nullable = false)
    private Integer empolyeeCount;
    @Basic
    @Column(name = "manager_count", nullable = false)
    private Integer managerCount;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "shifts_id", nullable = false)
    private Integer shiftsId;
    @OneToMany(mappedBy = "availableShiftsByShiftId")
    private Collection<ShiftsRequests> shiftsRequestsByShiftsId;

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(String endHour) {
        this.duration = duration;
    }

    public Integer getEmpolyeeCount() {
        return empolyeeCount;
    }

    public void setEmpolyeeCount(Integer empolyeeCount) {
        this.empolyeeCount = empolyeeCount;
    }

    public Integer getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(Integer managerCount) {
        this.managerCount = managerCount;
    }

    public Integer getShiftsId() {
        return shiftsId;
    }

    public void setShiftsId(Integer shiftsId) {
        this.shiftsId = shiftsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableShifts that = (AvailableShifts) o;
        return Objects.equals(weekNumber, that.weekNumber) && Objects.equals(dayNumber, that.dayNumber) && Objects.equals(startHour, that.startHour) && Objects.equals(duration, that.duration) && Objects.equals(empolyeeCount, that.empolyeeCount) && Objects.equals(managerCount, that.managerCount) && Objects.equals(shiftsId, that.shiftsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekNumber, dayNumber, startHour, duration, empolyeeCount, managerCount, shiftsId);
    }

    public Collection<ShiftsRequests> getShiftsRequestsByShiftsId() {
        return shiftsRequestsByShiftsId;
    }

    public void setShiftsRequestsByShiftsId(Collection<ShiftsRequests> shiftsRequestsByShiftsId) {
        this.shiftsRequestsByShiftsId = shiftsRequestsByShiftsId;
    }

}


