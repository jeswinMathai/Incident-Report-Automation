/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_BUILDING_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraBuildingList.findAll", query = "SELECT i FROM IraBuildingList i"),
    @NamedQuery(name = "IraBuildingList.findByBuildingCode", query = "SELECT i FROM IraBuildingList i WHERE i.buildingCode = :buildingCode"),
    @NamedQuery(name = "IraBuildingList.findByBuildingName", query = "SELECT i FROM IraBuildingList i WHERE i.buildingName = :buildingName"),
    @NamedQuery(name = "IraBuildingList.findByBuildingShortName", query = "SELECT i FROM IraBuildingList i WHERE i.buildingShortName = :buildingShortName"),
    @NamedQuery(name = "IraBuildingList.findByIsActive", query = "SELECT i FROM IraBuildingList i WHERE i.isActive = :isActive"),
    @NamedQuery(name = "IraBuildingList.findByInsideTrombay", query = "SELECT i FROM IraBuildingList i WHERE i.insideTrombay = :insideTrombay")})
public class IraBuildingList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "BUILDING_CODE")
    private String buildingCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BUILDING_NAME")
    private String buildingName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "BUILDING_SHORT_NAME")
    private String buildingShortName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ACTIVE")
    private Character isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSIDE_TROMBAY")
    private Character insideTrombay;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingCode")
    private Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection;

    public IraBuildingList() {
    }

    public IraBuildingList(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public IraBuildingList(String buildingCode, String buildingName, String buildingShortName, Character isActive, Character insideTrombay) {
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
        this.buildingShortName = buildingShortName;
        this.isActive = isActive;
        this.insideTrombay = insideTrombay;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingShortName() {
        return buildingShortName;
    }

    public void setBuildingShortName(String buildingShortName) {
        this.buildingShortName = buildingShortName;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public Character getInsideTrombay() {
        return insideTrombay;
    }

    public void setInsideTrombay(Character insideTrombay) {
        this.insideTrombay = insideTrombay;
    }

    @XmlTransient
    public Collection<IraIncidentReportDetails> getIraIncidentReportDetailsCollection() {
        return iraIncidentReportDetailsCollection;
    }

    public void setIraIncidentReportDetailsCollection(Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection) {
        this.iraIncidentReportDetailsCollection = iraIncidentReportDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buildingCode != null ? buildingCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraBuildingList)) {
            return false;
        }
        IraBuildingList other = (IraBuildingList) object;
        if ((this.buildingCode == null && other.buildingCode != null) || (this.buildingCode != null && !this.buildingCode.equals(other.buildingCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraBuildingList[ buildingCode=" + buildingCode + " ]";
    }
    
}
