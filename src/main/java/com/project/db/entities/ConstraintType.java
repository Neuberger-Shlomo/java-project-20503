package com.project.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Constraint_type", schema = "public", catalog = "project")
public class ConstraintType {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_id", nullable = false)
    private Integer typeId;
    @Basic
    @Column(name = "constraint_level", nullable = false)
    private Integer constraintLevel;
    @Basic
    @Column(name = "constraint_description", nullable = false, length = -1)
    private String constraintDescription;

    public Integer getTypeId() {
        return typeId;
    }


    public Integer getConstraintLevel() {
        return constraintLevel;
    }

    public void setConstraintLevel(Integer constraintLevel) {
        this.constraintLevel = constraintLevel;
    }

// added code****************************************************
    public static final int DEFAULT_CONSTRAINT_LEVEL = 1;
    public static final String DEFAULT_CONSTRAINT_DESCRIPTION = "Default Constraint Type";

    // Default constructor for JPA
    public ConstraintType() {
        this(DEFAULT_CONSTRAINT_LEVEL, DEFAULT_CONSTRAINT_DESCRIPTION);
    }
    // Constructor with both fields
    public ConstraintType(int constraintLevel, String constraintDescription) {
        this.constraintLevel = constraintLevel;
        this.constraintDescription = constraintDescription;
    }
    // end of added code****************************************************

    public String getConstraintDescription() {
        return constraintDescription;
    }

    public void setConstraintDescription(String constraintDescription) {
        this.constraintDescription = constraintDescription;
    }



    // Implement the equals function for ConstraintType objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConstraintType that = (ConstraintType) obj;
        return Objects.equals(typeId, that.typeId) &&
                Objects.equals(constraintLevel, that.constraintLevel) &&
                Objects.equals(constraintDescription, that.constraintDescription);
    }


    @Override
    public int hashCode() {
        return Objects.hash(typeId, constraintLevel, constraintDescription);
    }

    public void setTypeId(int i) {
    }
}
