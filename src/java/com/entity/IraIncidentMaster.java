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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DIVYA
 */

@Entity
@Table(name = "IRA_INCIDENT_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraIncidentMaster.findAll", query = "SELECT i FROM IraIncidentMaster i"),
    @NamedQuery(name = "IraIncidentMaster.findByIncidentId", query = "SELECT i FROM IraIncidentMaster i WHERE i.incidentId = :incidentId"),
    @NamedQuery(name = "IraIncidentMaster.findByIncidentName", query = "SELECT i FROM IraIncidentMaster i WHERE i.incidentName = :incidentName"),
    @NamedQuery(name = "IraIncidentMaster.findByIsActive", query = "SELECT i FROM IraIncidentMaster i WHERE i.isActive = :isActive")})
public class IraIncidentMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCIDENT_ID")
    private Integer incidentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INCIDENT_NAME")
    private String incidentName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ACTIVE")
    private Character isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidentId")
    private Collection<IraIncidentQuestionRelation> iraIncidentQuestionRelationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidentId")
    private Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection;
    @Transient
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IraIncidentMaster() {
    }

    public IraIncidentMaster(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public IraIncidentMaster(Integer incidentId, String incidentName, Character isActive) {
        this.incidentId = incidentId;
        this.incidentName = incidentName;
        this.isActive = isActive;
        this.status="old";
    }

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<IraIncidentQuestionRelation> getIraIncidentQuestionRelationCollection() {
        return iraIncidentQuestionRelationCollection;
    }

    public void setIraIncidentQuestionRelationCollection(Collection<IraIncidentQuestionRelation> iraIncidentQuestionRelationCollection) {
        this.iraIncidentQuestionRelationCollection = iraIncidentQuestionRelationCollection;
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
        hash += (incidentId != null ? incidentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraIncidentMaster)) {
            return false;
        }
        IraIncidentMaster other = (IraIncidentMaster) object;
        if ((this.incidentId == null && other.incidentId != null) || (this.incidentId != null && !this.incidentId.equals(other.incidentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraIncidentMaster[ incidentId=" + incidentId + " ]";
    }
    
}
