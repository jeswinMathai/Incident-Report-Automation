/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_PERSONS_INVOLVED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraPersonsInvolved.findAll", query = "SELECT i FROM IraPersonsInvolved i"),
    @NamedQuery(name = "IraPersonsInvolved.findBySrNo", query = "SELECT i FROM IraPersonsInvolved i WHERE i.srNo = :srNo"),
    @NamedQuery(name = "IraPersonsInvolved.findByHavingBarcCard", query = "SELECT i FROM IraPersonsInvolved i WHERE i.havingBarcCard = :havingBarcCard"),
    @NamedQuery(name = "IraPersonsInvolved.findByCardType", query = "SELECT i FROM IraPersonsInvolved i WHERE i.cardType = :cardType"),
    @NamedQuery(name = "IraPersonsInvolved.findById", query = "SELECT i FROM IraPersonsInvolved i WHERE i.id = :id"),
       @NamedQuery(name = "IraPersonsInvolved.findByReportId", query = "SELECT i FROM IraPersonsInvolved i WHERE i.reportId = :reportId"),
    @NamedQuery(name = "IraPersonsInvolved.findByName", query = "SELECT i FROM IraPersonsInvolved i WHERE i.name = :name"),
    @NamedQuery(name = "IraPersonsInvolved.findByDesignation", query = "SELECT i FROM IraPersonsInvolved i WHERE i.designation = :designation"),
    @NamedQuery(name = "IraPersonsInvolved.findByDivisionCompany", query = "SELECT i FROM IraPersonsInvolved i WHERE i.divisionCompany = :divisionCompany"),
    @NamedQuery(name = "IraPersonsInvolved.findByAddress", query = "SELECT i FROM IraPersonsInvolved i WHERE i.address = :address"),
    @NamedQuery(name = "IraPersonsInvolved.findByStreet", query = "SELECT i FROM IraPersonsInvolved i WHERE i.street = :street"),
    @NamedQuery(name = "IraPersonsInvolved.findByCity", query = "SELECT i FROM IraPersonsInvolved i WHERE i.city = :city"),
    @NamedQuery(name = "IraPersonsInvolved.findByState", query = "SELECT i FROM IraPersonsInvolved i WHERE i.state = :state"),
    @NamedQuery(name = "IraPersonsInvolved.findByCountry", query = "SELECT i FROM IraPersonsInvolved i WHERE i.country = :country"),
    @NamedQuery(name = "IraPersonsInvolved.findByContactNo", query = "SELECT i FROM IraPersonsInvolved i WHERE i.contactNo = :contactNo"),
    @NamedQuery(name = "IraPersonsInvolved.findByIsAccused", query = "SELECT i FROM IraPersonsInvolved i WHERE i.isAccused = :isAccused"),
    @NamedQuery(name = "IraPersonsInvolved.findByIsVictim", query = "SELECT i FROM IraPersonsInvolved i WHERE i.isVictim = :isVictim"),
    @NamedQuery(name = "IraPersonsInvolved.findByIsWitness", query = "SELECT i FROM IraPersonsInvolved i WHERE i.isWitness = :isWitness"),
    @NamedQuery(name = "IraPersonsInvolved.findByHealthStatus", query = "SELECT i FROM IraPersonsInvolved i WHERE i.healthStatus = :healthStatus")})
public class IraPersonsInvolved implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SR_NO")
    private Short srNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAVING_BARC_CARD")
    private Character havingBarcCard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CARD_TYPE")
    private String cardType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESIGNATION")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIVISION_COMPANY")
    private String divisionCompany;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 100)
    @Column(name = "STREET")
    private String street;
    @Size(max = 50)
    @Column(name = "CITY")
    private String city;
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    @Size(max = 75)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 50)
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "IS_ACCUSED")
    private Character isAccused;
    @Column(name = "IS_VICTIM")
    private Character isVictim;
    @Column(name = "IS_WITNESS")
    private Character isWitness;
    @Size(max = 20)
    @Column(name = "HEALTH_STATUS")
    private String healthStatus;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne(optional = false)
    private IraIncidentReportDetails reportId;

    public IraPersonsInvolved() {
    }

    public IraPersonsInvolved(Short srNo) {
        this.srNo = srNo;
    }

    public IraPersonsInvolved(Short srNo, Character havingBarcCard, String cardType, String id, String name, String designation, String divisionCompany) {
        this.srNo = srNo;
        this.havingBarcCard = havingBarcCard;
        this.cardType = cardType;
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.divisionCompany = divisionCompany;
    }

    public Short getSrNo() {
        return srNo;
    }

    public void setSrNo(Short srNo) {
        this.srNo = srNo;
    }

    public Character getHavingBarcCard() {
        return havingBarcCard;
    }

    public void setHavingBarcCard(Character havingBarcCard) {
        this.havingBarcCard = havingBarcCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDivisionCompany() {
        return divisionCompany;
    }

    public void setDivisionCompany(String divisionCompany) {
        this.divisionCompany = divisionCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Character getIsAccused() {
        return isAccused;
    }

    public void setIsAccused(Character isAccused) {
        this.isAccused = isAccused;
    }

    public Character getIsVictim() {
        return isVictim;
    }

    public void setIsVictim(Character isVictim) {
        this.isVictim = isVictim;
    }

    public Character getIsWitness() {
        return isWitness;
    }

    public void setIsWitness(Character isWitness) {
        this.isWitness = isWitness;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public IraIncidentReportDetails getReportId() {
        return reportId;
    }

    public void setReportId(IraIncidentReportDetails reportId) {
        this.reportId = reportId;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (srNo != null ? srNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraPersonsInvolved)) {
            return false;
        }
        IraPersonsInvolved other = (IraPersonsInvolved) object;
        if ((this.srNo == null && other.srNo != null) || (this.srNo != null && !this.srNo.equals(other.srNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraPersonsInvolved[ srNo=" + srNo + " ]";
    }
    
}
