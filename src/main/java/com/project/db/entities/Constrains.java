package com.project.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

    @Entity
    public class Constrains {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "constraint_id", nullable = false)
        private Integer constraintId;
        @Basic
        @Column(name = "type_id", nullable = false)
        private Integer typeId;
        @Basic
        @Column(name = "uid", nullable = false)
        private Integer uid;
        @Basic
        @Column(name = "data", nullable = false, length = -1)
        private String data;
        @Basic
        @Column(name = "permanent_flag", nullable = false)
        private Boolean permanentFlag;
        @Basic
        @Column(name = "week_number", nullable = false)
        private Integer weekNumber;
        @Basic
        @Column(name = "start_date", nullable = false)
        private java.sql.Timestamp startDate;
        @Basic
        @Column(name = "end_date", nullable = false)
        private java.sql.Timestamp endDate;

    public Integer getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(Integer constraintId) {
        this.constraintId = constraintId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getPermanentFlag() {
        return permanentFlag;
    }

    public void setPermanentFlag(Boolean permanentFlag) {
        this.permanentFlag = permanentFlag;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constrains that = (Constrains) o;
        return Objects.equals(constraintId, that.constraintId) && Objects.equals(typeId, that.typeId) && Objects.equals(uid, that.uid) && Objects.equals(data, that.data) && Objects.equals(permanentFlag, that.permanentFlag) && Objects.equals(weekNumber, that.weekNumber) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }


    @Override
    public int hashCode() {
        return Objects.hash(constraintId, typeId, uid, data, permanentFlag, weekNumber, startDate, endDate);
    }
    //create is empty methods for fields

}
