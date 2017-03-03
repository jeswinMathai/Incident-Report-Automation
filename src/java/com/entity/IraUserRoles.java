/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_USER_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraUserRoles.findAll", query = "SELECT i FROM IraUserRoles i"),
     @NamedQuery(name = "IraUserRoles.findByEmpNoId", query = "SELECT i FROM IraUserRoles i WHERE i.empNo = :empNo AND i.roleId = :roleId" ),
     @NamedQuery(name = "IraUserRoles.findByRoleId", query = "SELECT i FROM IraUserRoles i WHERE i.roleId = :roleId"),
     @NamedQuery(name = "IraUserRoles.findByEmpNo", query = "SELECT i FROM IraUserRoles i WHERE i.empNo = :empNo")})
public class IraUserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_NO")
    private Integer empNo;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IraPkiEmpInfoAll iraPkiEmpInfoAll;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private IraRoleMaster roleId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "iraUserRoles")
    private IraUserAuth iraUserAuth;

    public IraUserRoles() {
    }

    public IraUserRoles(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public IraPkiEmpInfoAll getIraPkiEmpInfoAll() {
        return iraPkiEmpInfoAll;
    }

    public void setIraPkiEmpInfoAll(IraPkiEmpInfoAll iraPkiEmpInfoAll) {
        this.iraPkiEmpInfoAll = iraPkiEmpInfoAll;
    }

    public IraRoleMaster getRoleId() {
        return roleId;
    }

    public void setRoleId(IraRoleMaster roleId) {
        this.roleId = roleId;
    }

    public IraUserAuth getIraUserAuth() {
        return iraUserAuth;
    }

    public void setIraUserAuth(IraUserAuth iraUserAuth) {
        this.iraUserAuth = iraUserAuth;
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
        if (!(object instanceof IraUserRoles)) {
            return false;
        }
        IraUserRoles other = (IraUserRoles) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraUserRoles[ empNo=" + empNo + " ]";
    }
    
}
