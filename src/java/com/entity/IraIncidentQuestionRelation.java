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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_INCIDENT_QUESTION_RELATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraIncidentQuestionRelation.findAll", query = "SELECT i FROM IraIncidentQuestionRelation i"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findBySrNo", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.srNo = :srNo"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findByIsMandatory", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.isMandatory = :isMandatory"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findByCanBeRepeated", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.canBeRepeated = :canBeRepeated"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findByImId", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.incidentId = :incidentId"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findByImIdQmId", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.incidentId = :incidentId AND i.questionId = :questionId"),
    @NamedQuery(name = "IraIncidentQuestionRelation.findByQmId", query = "SELECT i FROM IraIncidentQuestionRelation i WHERE i.questionId = :questionId")})

public class IraIncidentQuestionRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SR_NO")
    private Integer srNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_MANDATORY")
    private Character isMandatory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAN_BE_REPEATED")
    private Character canBeRepeated;
    @JoinColumn(name = "INCIDENT_ID", referencedColumnName = "INCIDENT_ID")
    @ManyToOne(optional = false)
    private IraIncidentMaster incidentId;
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID")
    @ManyToOne(optional = false)
    private IraQuestionMaster questionId;

    public IraIncidentQuestionRelation() {
    }

    public IraIncidentQuestionRelation(Integer srNo) {
        this.srNo = srNo;
    }

    public IraIncidentQuestionRelation(Integer srNo, Character isMandatory, Character canBeRepeated) {
        this.srNo = srNo;
        this.isMandatory = isMandatory;
        this.canBeRepeated = canBeRepeated;
    }
   
    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Character getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Character isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Character getCanBeRepeated() {
        return canBeRepeated;
    }

    public void setCanBeRepeated(Character canBeRepeated) {
        this.canBeRepeated = canBeRepeated;
    }

    public IraIncidentMaster getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(IraIncidentMaster incidentId) {
        this.incidentId = incidentId;
    }

    public IraQuestionMaster getQuestionId() {
        return questionId;
    }

    public void setQuestionId(IraQuestionMaster questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof IraIncidentQuestionRelation)) {
            return false;
        }
        IraIncidentQuestionRelation other = (IraIncidentQuestionRelation) object;
        if ((this.srNo == null && other.srNo != null) || (this.srNo != null && !this.srNo.equals(other.srNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraIncidentQuestionRelation[ srNo=" + srNo + " ]";
    }
    
}
