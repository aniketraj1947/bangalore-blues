package com.sleek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "ownerDescription")
    private String ownerDescription;

    @Column(name = "ownerPhoneNumber")
    private String ownerPhoneNumber;

    public String getOwnerId() {
        return ownerId;
    }

    public Owner setOwnerId(String ownerId) {
        this.ownerId = ownerId; return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerDescription() {
        return ownerDescription;
    }

    public void setOwnerDescription(String ownerDescription) {
        this.ownerDescription = ownerDescription;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerDescription='" + ownerDescription + '\'' +
                ", ownerPhoneNumber='" + ownerPhoneNumber + '\'' +
                '}';
    }
}
