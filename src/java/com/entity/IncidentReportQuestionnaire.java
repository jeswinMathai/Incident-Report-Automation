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
@Table(name = "INCIDENT_REPORT_QUESTIONNAIRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncidentReportQuestionnaire.findAll", query = "SELECT i FROM IncidentReportQuestionnaire i"),
    @NamedQuery(name = "IncidentReportQuestionnaire.findBySrNo", query = "SELECT i FROM IncidentReportQuestionnaire i WHERE i.srNo = :srNo"),
    @NamedQuery(name = "IncidentReportQuestionnaire.findByReportId", query = "SELECT i FROM IncidentReportQuestionnaire i WHERE i.reportId= :reportId"),
    @NamedQuery(name = "IncidentReportQuestionnaire.findByQuestionDescription", query = "SELECT i FROM IncidentReportQuestionnaire i WHERE i.questionDescription = :questionDescription"),
    @NamedQuery(name = "IncidentReportQuestionnaire.findByAnswer", query = "SELECT i FROM IncidentReportQuestionnaire i WHERE i.answer = :answer")})
public class IncidentReportQuestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SR_NO")
    private Integer srNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "QUESTION_DESCRIPTION")
    private String questionDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ANSWER")
    private String answer;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private IraIncidentReportDetails reportId;
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID")
    @ManyToOne(optional = false)
    private IraQuestionMaster questionId;

    public IncidentReportQuestionnaire() {
    }

    public IncidentReportQuestionnaire(Integer srNo) {
        this.srNo = srNo;
    }

    public IncidentReportQuestionnaire(Integer srNo, String questionDescription, String answer) {
        this.srNo = srNo;
        this.questionDescription = questionDescription;
        this.answer = answer;
    }

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public IraIncidentReportDetails getReportId() {
        return reportId;
    }

    public void setReportId(IraIncidentReportDetails reportId) {
        this.reportId = reportId;
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
        if (!(object instanceof IncidentReportQuestionnaire)) {
            return false;
        }
        IncidentReportQuestionnaire other = (IncidentReportQuestionnaire) object;
        if ((this.srNo == null && other.srNo != null) || (this.srNo != null && !this.srNo.equals(other.srNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IncidentReportQuestionnaire[ srNo=" + srNo + " ]";
    }
    
}
