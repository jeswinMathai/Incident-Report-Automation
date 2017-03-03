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
import javax.persistence.FetchType;
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
@Table(name = "IRA_REPORT_AUTHORITY_SIGNS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraReportAuthoritySigns.findAll", query = "SELECT i FROM IraReportAuthoritySigns i"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAuthSrNo", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authSrNo = :authSrNo"),
     @NamedQuery(name = "IraReportAuthoritySigns.findByReportId", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.reportId = :reportId"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAuthName", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authName = :authName"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAuthDesig", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authDesig = :authDesig"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAuthCertSrNo", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authCertSrNo = :authCertSrNo"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByRoleId", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.roleId = :roleId"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByRoleIdAndReportId", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.roleId = :roleId AND i.reportId= :reportId"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByRoleName", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.roleName = :roleName"),
     @NamedQuery(name = "IraReportAuthoritySigns.findByAuthEmpNo", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authEmpNo = :authEmpNo"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAction", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.action = :action"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByRemarks", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.remarks = :remarks"),
    @NamedQuery(name = "IraReportAuthoritySigns.findByAuthSign", query = "SELECT i FROM IraReportAuthoritySigns i WHERE i.authSign = :authSign")})
public class IraReportAuthoritySigns implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTH_SR_NO")
    private Integer authSrNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "AUTH_NAME")
    private String authName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AUTH_DESIG")
    private String authDesig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AUTH_CERT_SR_NO")
    private String authCertSrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_ID")
    private int roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ACTION")
    private String action;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "REMARKS")
    private String remarks;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "AUTH_SIGN")
    private String authSign;
    @Column(name = "REPORT_ID")
    @NotNull
    private Integer reportId;
    @JoinColumn(name = "REPORT_MARKED_TO", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private IraPkiEmpInfoAll reportMarkedTo;
    @JoinColumn(name = "AUTH_EMP_NO", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private IraPkiEmpInfoAll authEmpNo;

    public IraReportAuthoritySigns() {
    }

    public IraReportAuthoritySigns(Integer authSrNo) {
        this.authSrNo = authSrNo;
    }

    public IraReportAuthoritySigns(Integer authSrNo, String authName, String authDesig, String authCertSrNo, int roleId, String roleName, String action, String remarks, String authSign) {
        this.authSrNo = authSrNo;
        this.authName = authName;
        this.authDesig = authDesig;
        this.authCertSrNo = authCertSrNo;
        this.roleId = roleId;
        this.roleName = roleName;
        this.action = action;
        this.remarks = remarks;
        this.authSign = authSign;
    }

    public Integer getAuthSrNo() {
        return authSrNo;
    }

    public void setAuthSrNo(Integer authSrNo) {
        this.authSrNo = authSrNo;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDesig() {
        return authDesig;
    }

    public void setAuthDesig(String authDesig) {
        this.authDesig = authDesig;
    }

    public String getAuthCertSrNo() {
        return authCertSrNo;
    }

    public void setAuthCertSrNo(String authCertSrNo) {
        this.authCertSrNo = authCertSrNo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAuthSign() {
        return authSign;
    }

    public void setAuthSign(String authSign) {
        this.authSign = authSign;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public IraPkiEmpInfoAll getReportMarkedTo() {
        return reportMarkedTo;
    }

    public void setReportMarkedTo(IraPkiEmpInfoAll reportMarkedTo) {
        this.reportMarkedTo = reportMarkedTo;
    }

    public IraPkiEmpInfoAll getAuthEmpNo() {
        return authEmpNo;
    }

    public void setAuthEmpNo(IraPkiEmpInfoAll authEmpNo) {
        this.authEmpNo = authEmpNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authSrNo != null ? authSrNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraReportAuthoritySigns)) {
            return false;
        }
        IraReportAuthoritySigns other = (IraReportAuthoritySigns) object;
        if ((this.authSrNo == null && other.authSrNo != null) || (this.authSrNo != null && !this.authSrNo.equals(other.authSrNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraReportAuthoritySigns[ authSrNo=" + authSrNo + " ]";
    }
    
}
