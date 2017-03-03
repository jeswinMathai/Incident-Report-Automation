/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_INCIDENT_REPORT_DETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraIncidentReportDetails.findAll", query = "SELECT i FROM IraIncidentReportDetails i"),
    @NamedQuery(name = "IraIncidentReportDetails.findByReportId", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.reportId = :reportId"),
    @NamedQuery(name = "IraIncidentReportDetails.findByIncidentName", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.incidentName = :incidentName"),
    @NamedQuery(name = "IraIncidentReportDetails.findByDateTimeOfOccurrence", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.dateTimeOfOccurrence = :dateTimeOfOccurrence"),
    @NamedQuery(name = "IraIncidentReportDetails.findByBuildingShortName", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.buildingShortName = :buildingShortName"),
    @NamedQuery(name = "IraIncidentReportDetails.findByLocationDescription", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.locationDescription = :locationDescription"),
    @NamedQuery(name = "IraIncidentReportDetails.findByReportedOn", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.reportedOn = :reportedOn"),
    @NamedQuery(name = "IraIncidentReportDetails.findByCurrentStatus", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.currentStatus = :currentStatus"),
    @NamedQuery(name = "IraIncidentReportDetails.findByIsNewVersionRequired", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.isNewVersionRequired = :isNewVersionRequired"),
    @NamedQuery(name = "IraIncidentReportDetails.findByOldReportId", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.oldReportId = :oldReportId"),
    @NamedQuery(name = "IraIncidentReportDetails.findByIncidentDetails", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.incidentDetails = :incidentDetails"),
    @NamedQuery(name = "IraIncidentReportDetails.findByMarkedTo", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.reportTo = :reportTo"),
    @NamedQuery(name = "IraIncidentReportDetails.findByFutureProtection", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.futureProtection = :futureProtection"),
    @NamedQuery(name = "IraIncidentReportDetails.findByEditReport", query = "SELECT i FROM IraIncidentReportDetails i WHERE i.editReport = :editReport")})
public class IraIncidentReportDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INCIDENT_NAME")
    private String incidentName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_TIME_OF_OCCURRENCE")
    @Temporal(TemporalType.DATE)
    private Date dateTimeOfOccurrence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "BUILDING_SHORT_NAME")
    private String buildingShortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOCATION_DESCRIPTION")
    private String locationDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORTED_ON")
    @Temporal(TemporalType.DATE)
    private Date reportedOn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_NEW_VERSION_REQUIRED")
    private Character isNewVersionRequired;
    @Basic(optional = false)
    @Column(name = "OLD_REPORT_ID")
    private int oldReportId;
    @Size(max = 100)
    @Column(name = "INCIDENT_DETAILS")
    private String incidentDetails;
    @Size(max = 100)
    @Column(name = "FUTURE_PROTECTION")
    private String futureProtection;
    @Column(name = "EDIT_REPORT")
    private char editReport;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportId",orphanRemoval = true)
    private Collection<IraPersonsInvolved> iraPersonsInvolvedCollection;
    @OneToMany(cascade = { CascadeType.PERSIST,
                       CascadeType.MERGE }, mappedBy = "reportId",orphanRemoval = true)
    private Collection<IncidentReportQuestionnaire> incidentReportQuestionnaireCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportId",orphanRemoval = true)
    private Collection<IraDocumentsAttached> iraDocumentsAttachedCollection;
    @JoinColumn(name = "BUILDING_CODE", referencedColumnName = "BUILDING_CODE")
    @ManyToOne(optional = false)
    private IraBuildingList buildingCode;
    @JoinColumn(name = "INCIDENT_ID", referencedColumnName = "INCIDENT_ID")
    @ManyToOne(optional = false)
    private IraIncidentMaster incidentId;
    @JoinColumn(name = "REPORT_MARKED_TO", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private IraPkiEmpInfoAll reportMarkedTo;
    @JoinColumn(name = "REPORTED_BY", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private IraPkiEmpInfoAll reportedBy;
      @JoinColumn(name = "REPORT_TO", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private IraRoleMaster reportTo;

    public IraIncidentReportDetails() {
    }

    public IraIncidentReportDetails(Integer reportId) {
        this.reportId = reportId;
    }

    public IraIncidentReportDetails(Integer reportId, String incidentName, Date dateTimeOfOccurrence, String buildingShortName, String locationDescription, Date reportedOn, String currentStatus, Character isNewVersionRequired, int oldReportId) {
        this.reportId = reportId;
        this.incidentName = incidentName;
        this.dateTimeOfOccurrence = dateTimeOfOccurrence;
        this.buildingShortName = buildingShortName;
        this.locationDescription = locationDescription;
        this.reportedOn = reportedOn;
        this.currentStatus = currentStatus;
        this.isNewVersionRequired = isNewVersionRequired;
        this.oldReportId = oldReportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public Date getDateTimeOfOccurrence() {
        return dateTimeOfOccurrence;
    }

    public void setDateTimeOfOccurrence(Date dateTimeOfOccurrence) {
        this.dateTimeOfOccurrence = dateTimeOfOccurrence;
    }

    public String getBuildingShortName() {
        return buildingShortName;
    }

    public void setBuildingShortName(String buildingShortName) {
        this.buildingShortName = buildingShortName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Date getReportedOn() {
        return reportedOn;
    }

    public IraRoleMaster getReportTo() {
        return reportTo;
    }

    public void setReportTo(IraRoleMaster reportTo) {
        this.reportTo = reportTo;
    }

    public void setReportedOn(Date reportedOn) {
        this.reportedOn = reportedOn;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Character getIsNewVersionRequired() {
        return isNewVersionRequired;
    }

    public void setIsNewVersionRequired(Character isNewVersionRequired) {
        this.isNewVersionRequired = isNewVersionRequired;
    }

    public int getOldReportId() {
        return oldReportId;
    }

    public void setOldReportId(int oldReportId) {
        this.oldReportId = oldReportId;
    }

    public String getIncidentDetails() {
        return incidentDetails;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public String getFutureProtection() {
        return futureProtection;
    }

    public void setFutureProtection(String futureProtection) {
        this.futureProtection = futureProtection;
    }

    public char getEditReport() {
        return editReport;
    }

    public void setEditReport(char editReport) {
        this.editReport = editReport;
    }

    @XmlTransient
    public Collection<IraPersonsInvolved> getIraPersonsInvolvedCollection() {
        return iraPersonsInvolvedCollection;
    }

    public void setIraPersonsInvolvedCollection(Collection<IraPersonsInvolved> iraPersonsInvolvedCollection) {
        this.iraPersonsInvolvedCollection = iraPersonsInvolvedCollection;
    }

    @XmlTransient
    public Collection<IncidentReportQuestionnaire> getIncidentReportQuestionnaireCollection() {
        return incidentReportQuestionnaireCollection;
    }

    public void setIncidentReportQuestionnaireCollection(Collection<IncidentReportQuestionnaire> incidentReportQuestionnaireCollection) {
        this.incidentReportQuestionnaireCollection = incidentReportQuestionnaireCollection;
    }

    @XmlTransient
    public Collection<IraDocumentsAttached> getIraDocumentsAttachedCollection() {
        return iraDocumentsAttachedCollection;
    }

    public void setIraDocumentsAttachedCollection(Collection<IraDocumentsAttached> iraDocumentsAttachedCollection) {
        this.iraDocumentsAttachedCollection = iraDocumentsAttachedCollection;
    }

    public IraBuildingList getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(IraBuildingList buildingCode) {
        this.buildingCode = buildingCode;
    }

    public IraIncidentMaster getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(IraIncidentMaster incidentId) {
        this.incidentId = incidentId;
    }

    public IraPkiEmpInfoAll getReportMarkedTo() {
        return reportMarkedTo;
    }

    public void setReportMarkedTo(IraPkiEmpInfoAll reportMarkedTo) {
        this.reportMarkedTo = reportMarkedTo;
    }

    public IraPkiEmpInfoAll getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(IraPkiEmpInfoAll reportedBy) {
        this.reportedBy = reportedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraIncidentReportDetails)) {
            return false;
        }
        IraIncidentReportDetails other = (IraIncidentReportDetails) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraIncidentReportDetails[ reportId=" + reportId + " ]";
    }
    
}
