/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_DOCUMENTS_ATTACHED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraDocumentsAttached.findAll", query = "SELECT i FROM IraDocumentsAttached i"),
    @NamedQuery(name = "IraDocumentsAttached.findBySrNo", query = "SELECT i FROM IraDocumentsAttached i WHERE i.srNo = :srNo"),
      @NamedQuery(name = "IraDocumentsAttached.findByReportId", query = "SELECT i FROM IraDocumentsAttached i WHERE i.reportId = :reportId"),
    @NamedQuery(name = "IraDocumentsAttached.findByDocFileName", query = "SELECT i FROM IraDocumentsAttached i WHERE i.docFileName = :docFileName"),
    @NamedQuery(name = "IraDocumentsAttached.findByUploadedOn", query = "SELECT i FROM IraDocumentsAttached i WHERE i.uploadedOn = :uploadedOn")})
public class IraDocumentsAttached implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SR_NO")
    private Integer srNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DOC_FILE_NAME")
    private String docFileName;
    @Column(name = "EXT")
    private String ext;
    @Lob
    @Column(name = "DOCUMENT")
    private byte[] document;
    @Column(name = "UPLOADED_ON")
    @Temporal(TemporalType.DATE)
    private Date uploadedOn;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne(optional = false)
    private IraIncidentReportDetails reportId;
    @JoinColumn(name = "UPLOADED_BY", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private IraPkiEmpInfoAll uploadedBy;

    public IraDocumentsAttached() {
    }

    public IraDocumentsAttached(Integer srNo) {
        this.srNo = srNo;
    }

    public IraDocumentsAttached(Integer srNo, String docFileName) {
        this.srNo = srNo;
        this.docFileName = docFileName;
    }

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public IraIncidentReportDetails getReportId() {
        return reportId;
    }

    public void setReportId(IraIncidentReportDetails reportId) {
        this.reportId = reportId;
    }

    public IraPkiEmpInfoAll getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(IraPkiEmpInfoAll uploadedBy) {
        this.uploadedBy = uploadedBy;
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
        if (!(object instanceof IraDocumentsAttached)) {
            return false;
        }
        IraDocumentsAttached other = (IraDocumentsAttached) object;
        if ((this.srNo == null && other.srNo != null) || (this.srNo != null && !this.srNo.equals(other.srNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraDocumentsAttached[ srNo=" + srNo + " ]";
    }
    
}
