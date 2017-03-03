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
@Table(name = "IRA_QUESTION_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraQuestionMaster.findAll", query = "SELECT i FROM IraQuestionMaster i"),
    @NamedQuery(name = "IraQuestionMaster.findByQuestionId", query = "SELECT i FROM IraQuestionMaster i WHERE i.questionId = :questionId"),
    @NamedQuery(name = "IraQuestionMaster.findByQuestionDescription", query = "SELECT i FROM IraQuestionMaster i WHERE i.questionDescription = :questionDescription"),
    @NamedQuery(name = "IraQuestionMaster.findByIsActive", query = "SELECT i FROM IraQuestionMaster i WHERE i.isActive = :isActive")})
public class IraQuestionMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUESTION_ID")
    private Integer questionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "QUESTION_DESCRIPTION")
    private String questionDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ACTIVE")
    private Character isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<IraIncidentQuestionRelation> iraIncidentQuestionRelationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<IncidentReportQuestionnaire> incidentReportQuestionnaireCollection;

    public IraQuestionMaster() {
    }

    public IraQuestionMaster(Integer questionId) {
        this.questionId = questionId;
    }

    public IraQuestionMaster(Integer questionId, String questionDescription, Character isActive) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.isActive = isActive;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
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
    public Collection<IncidentReportQuestionnaire> getIncidentReportQuestionnaireCollection() {
        return incidentReportQuestionnaireCollection;
    }

    public void setIncidentReportQuestionnaireCollection(Collection<IncidentReportQuestionnaire> incidentReportQuestionnaireCollection) {
        this.incidentReportQuestionnaireCollection = incidentReportQuestionnaireCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraQuestionMaster)) {
            return false;
        }
        IraQuestionMaster other = (IraQuestionMaster) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraQuestionMaster[ questionId=" + questionId + " ]";
    }
    
}
