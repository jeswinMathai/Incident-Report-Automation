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
import javax.persistence.OneToOne;
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
@Table(name = "IRA_PKI_EMP_INFO_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraPkiEmpInfoAll.findAll", query = "SELECT i FROM IraPkiEmpInfoAll i"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByEmpNo", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.empNo = :empNo"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.name like :name"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByDesignation", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.designation = :designation"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByDesigFullName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.desigFullName = :desigFullName"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByDivShortName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.divShortName = :divShortName"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByDivName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.divName = :divName"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByGroupShortName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.groupShortName = :groupShortName"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByGroupName", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.groupName = :groupName"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByEmail", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.email = :email"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByPhoneNo", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.phoneNo = :phoneNo"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByAuthType", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.authType = :authType"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByCertSno", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.certSno = :certSno"),
    @NamedQuery(name = "IraPkiEmpInfoAll.findByStatFlag", query = "SELECT i FROM IraPkiEmpInfoAll i WHERE i.statFlag = :statFlag")})
public class IraPkiEmpInfoAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_NO")
    private Integer empNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "DESIGNATION")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESIG_FULL_NAME")
    private String desigFullName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DIV_SHORT_NAME")
    private String divShortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIV_NAME")
    private String divName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GROUP_SHORT_NAME")
    private String groupShortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GROUP_NAME")
    private String groupName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONE_NO")
    private int phoneNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AUTH_TYPE")
    private String authType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CERT_SNO")
    private String certSno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STAT_FLAG")
    private Character statFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportMarkedTo")
    private Collection<IraReportAuthoritySigns> iraReportAuthoritySignsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authEmpNo")
    private Collection<IraReportAuthoritySigns> iraReportAuthoritySignsCollection1;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true    , mappedBy = "iraPkiEmpInfoAll")
    private IraUserRoles iraUserRoles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedBy")
    private Collection<IraDocumentsAttached> iraDocumentsAttachedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportMarkedTo")
    private Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedBy")
    private Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection1;

    public IraPkiEmpInfoAll() {
    }

    public IraPkiEmpInfoAll(Integer empNo) {
        this.empNo = empNo;
    }

    public IraPkiEmpInfoAll(Integer empNo, String name, String designation, String desigFullName, String divShortName, String divName, String groupShortName, String groupName, String email, int phoneNo, String authType, String certSno, Character statFlag) {
        this.empNo = empNo;
        this.name = name;
        this.designation = designation;
        this.desigFullName = desigFullName;
        this.divShortName = divShortName;
        this.divName = divName;
        this.groupShortName = groupShortName;
        this.groupName = groupName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.authType = authType;
        this.certSno = certSno;
        this.statFlag = statFlag;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
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

    public String getDesigFullName() {
        return desigFullName;
    }

    public void setDesigFullName(String desigFullName) {
        this.desigFullName = desigFullName;
    }

    public String getDivShortName() {
        return divShortName;
    }

    public void setDivShortName(String divShortName) {
        this.divShortName = divShortName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getGroupShortName() {
        return groupShortName;
    }

    public void setGroupShortName(String groupShortName) {
        this.groupShortName = groupShortName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getCertSno() {
        return certSno;
    }

    public void setCertSno(String certSno) {
        this.certSno = certSno;
    }

    public Character getStatFlag() {
        return statFlag;
    }

    public void setStatFlag(Character statFlag) {
        this.statFlag = statFlag;
    }

    public void removeChild() {
        
        iraUserRoles=null;
    }
    @XmlTransient
    public Collection<IraReportAuthoritySigns> getIraReportAuthoritySignsCollection() {
        return iraReportAuthoritySignsCollection;
    }

    public void setIraReportAuthoritySignsCollection(Collection<IraReportAuthoritySigns> iraReportAuthoritySignsCollection) {
        this.iraReportAuthoritySignsCollection = iraReportAuthoritySignsCollection;
    }

    @XmlTransient
    public Collection<IraReportAuthoritySigns> getIraReportAuthoritySignsCollection1() {
        return iraReportAuthoritySignsCollection1;
    }

    public void setIraReportAuthoritySignsCollection1(Collection<IraReportAuthoritySigns> iraReportAuthoritySignsCollection1) {
        this.iraReportAuthoritySignsCollection1 = iraReportAuthoritySignsCollection1;
    }

    public IraUserRoles getIraUserRoles() {
        return iraUserRoles;
    }

    public void setIraUserRoles(IraUserRoles iraUserRoles) {
        this.iraUserRoles = iraUserRoles;
    }

    @XmlTransient
    public Collection<IraDocumentsAttached> getIraDocumentsAttachedCollection() {
        return iraDocumentsAttachedCollection;
    }

    public void setIraDocumentsAttachedCollection(Collection<IraDocumentsAttached> iraDocumentsAttachedCollection) {
        this.iraDocumentsAttachedCollection = iraDocumentsAttachedCollection;
    }

    @XmlTransient
    public Collection<IraIncidentReportDetails> getIraIncidentReportDetailsCollection() {
        return iraIncidentReportDetailsCollection;
    }

    public void setIraIncidentReportDetailsCollection(Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection) {
        this.iraIncidentReportDetailsCollection = iraIncidentReportDetailsCollection;
    }

    @XmlTransient
    public Collection<IraIncidentReportDetails> getIraIncidentReportDetailsCollection1() {
        return iraIncidentReportDetailsCollection1;
    }

    public void setIraIncidentReportDetailsCollection1(Collection<IraIncidentReportDetails> iraIncidentReportDetailsCollection1) {
        this.iraIncidentReportDetailsCollection1 = iraIncidentReportDetailsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraPkiEmpInfoAll)) {
            return false;
        }
        IraPkiEmpInfoAll other = (IraPkiEmpInfoAll) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraPkiEmpInfoAll[ empNo=" + empNo + " ]";
    }
    
}
