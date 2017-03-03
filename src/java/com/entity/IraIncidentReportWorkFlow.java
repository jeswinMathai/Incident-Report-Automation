/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "IRA_INCIDENT_REPORT_WORK_FLOW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraIncidentReportWorkFlow.findAll", query = "SELECT i FROM IraIncidentReportWorkFlow i"),
    @NamedQuery(name = "IraIncidentReportWorkFlow.findByIsNewVersionReqd", query = "SELECT i FROM IraIncidentReportWorkFlow i WHERE i.isNewVersionReqd = :isNewVersionReqd"),
    @NamedQuery(name = "IraIncidentReportWorkFlow.findByCurrentStatus", query = "SELECT i FROM IraIncidentReportWorkFlow i WHERE i.currentStatus = :currentStatus"),
    @NamedQuery(name = "IraIncidentReportWorkFlow.findByAction", query = "SELECT i FROM IraIncidentReportWorkFlow i WHERE i.action = :action"),
    @NamedQuery(name = "IraIncidentReportWorkFlow.findByNewCurrentStatus", query = "SELECT i FROM IraIncidentReportWorkFlow i WHERE i.newCurrentStatus = :newCurrentStatus"),
    @NamedQuery(name = "IraIncidentReportWorkFlow.findBySrNo", query = "SELECT i FROM IraIncidentReportWorkFlow i WHERE i.srNo = :srNo")})
public class IraIncidentReportWorkFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_NEW_VERSION_REQD")
    private Character isNewVersionReqd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACTION")
    private String action;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NEW_CURRENT_STATUS")
    private String newCurrentStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SR_NO")
    private BigDecimal srNo;

    public IraIncidentReportWorkFlow() {
    }

    public IraIncidentReportWorkFlow(BigDecimal srNo) {
        this.srNo = srNo;
    }

    public IraIncidentReportWorkFlow(BigDecimal srNo, Character isNewVersionReqd, String currentStatus, String action, String newCurrentStatus) {
        this.srNo = srNo;
        this.isNewVersionReqd = isNewVersionReqd;
        this.currentStatus = currentStatus;
        this.action = action;
        this.newCurrentStatus = newCurrentStatus;
    }

    public Character getIsNewVersionReqd() {
        return isNewVersionReqd;
    }

    public void setIsNewVersionReqd(Character isNewVersionReqd) {
        this.isNewVersionReqd = isNewVersionReqd;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNewCurrentStatus() {
        return newCurrentStatus;
    }

    public void setNewCurrentStatus(String newCurrentStatus) {
        this.newCurrentStatus = newCurrentStatus;
    }

    public BigDecimal getSrNo() {
        return srNo;
    }

    public void setSrNo(BigDecimal srNo) {
        this.srNo = srNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (srNo != null ? srNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraIncidentReportWorkFlow)) {
            return false;
        }
        IraIncidentReportWorkFlow other = (IraIncidentReportWorkFlow) object;
        if ((this.srNo == null && other.srNo != null) || (this.srNo != null && !this.srNo.equals(other.srNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraIncidentReportWorkFlow[ srNo=" + srNo + " ]";
    }
    
}
