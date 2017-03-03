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
@Table(name = "IRA_PKI_CERTIFICATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraPkiCertificates.findAll", query = "SELECT i FROM IraPkiCertificates i"),
    @NamedQuery(name = "IraPkiCertificates.findByCertNo", query = "SELECT i FROM IraPkiCertificates i WHERE i.certNo = :certNo"),
    @NamedQuery(name = "IraPkiCertificates.findByName", query = "SELECT i FROM IraPkiCertificates i WHERE i.name = :name"),
    @NamedQuery(name = "IraPkiCertificates.findByValidFrom", query = "SELECT i FROM IraPkiCertificates i WHERE i.validFrom = :validFrom"),
    @NamedQuery(name = "IraPkiCertificates.findByValidUpto", query = "SELECT i FROM IraPkiCertificates i WHERE i.validUpto = :validUpto"),
    @NamedQuery(name = "IraPkiCertificates.findByCertificate", query = "SELECT i FROM IraPkiCertificates i WHERE i.certificate = :certificate"),
    @NamedQuery(name = "IraPkiCertificates.findByCurrentStatus", query = "SELECT i FROM IraPkiCertificates i WHERE i.currentStatus = :currentStatus"),
    @NamedQuery(name = "IraPkiCertificates.findByDateOfCurrStatus", query = "SELECT i FROM IraPkiCertificates i WHERE i.dateOfCurrStatus = :dateOfCurrStatus"),
    @NamedQuery(name = "IraPkiCertificates.findByIssuer", query = "SELECT i FROM IraPkiCertificates i WHERE i.issuer = :issuer"),
    @NamedQuery(name = "IraPkiCertificates.findByEmpNo", query = "SELECT i FROM IraPkiCertificates i WHERE i.empNo = :empNo")})
public class IraPkiCertificates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CERT_NO")
    private String certNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_UPTO")
    @Temporal(TemporalType.DATE)
    private Date validUpto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "CERTIFICATE")
    private String certificate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_OF_CURR_STATUS")
    @Temporal(TemporalType.DATE)
    private Date dateOfCurrStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ISSUER")
    private String issuer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EMP_NO")
    private String empNo;

    public IraPkiCertificates() {
    }

    public IraPkiCertificates(String certNo) {
        this.certNo = certNo;
    }

    public IraPkiCertificates(String certNo, String name, Date validFrom, Date validUpto, String certificate, String currentStatus, Date dateOfCurrStatus, String issuer, String empNo) {
        this.certNo = certNo;
        this.name = name;
        this.validFrom = validFrom;
        this.validUpto = validUpto;
        this.certificate = certificate;
        this.currentStatus = currentStatus;
        this.dateOfCurrStatus = dateOfCurrStatus;
        this.issuer = issuer;
        this.empNo = empNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(Date validUpto) {
        this.validUpto = validUpto;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getDateOfCurrStatus() {
        return dateOfCurrStatus;
    }

    public void setDateOfCurrStatus(Date dateOfCurrStatus) {
        this.dateOfCurrStatus = dateOfCurrStatus;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certNo != null ? certNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraPkiCertificates)) {
            return false;
        }
        IraPkiCertificates other = (IraPkiCertificates) object;
        if ((this.certNo == null && other.certNo != null) || (this.certNo != null && !this.certNo.equals(other.certNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraPkiCertificates[ certNo=" + certNo + " ]";
    }
    
}
