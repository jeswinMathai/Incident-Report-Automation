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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DIVYA
 */
@Entity
@Table(name = "IRA_USER_AUTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraUserAuth.findAll", query = "SELECT i FROM IraUserAuth i"),
    @NamedQuery(name = "IraUserAuth.findByEmpNo", query = "SELECT i FROM IraUserAuth i WHERE i.empNo = :empNo"),
    @NamedQuery(name="IraUserAuthCheck",query="SELECT i FROM IraUserAuth i WHERE i.empNo = :empNo AND i.isAllowed='Y' AND i.password=:password"),
    @NamedQuery(name = "IraUserAuth.findByPassword", query = "SELECT i FROM IraUserAuth i WHERE i.password = :password"),
    @NamedQuery(name = "IraUserAuth.findByIsAllowed", query = "SELECT i FROM IraUserAuth i WHERE i.isAllowed = :isAllowed")})
public class IraUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_NO")
    private Integer empNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ALLOWED")
    private Character isAllowed;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IraUserRoles iraUserRoles;

    public IraUserAuth() {
    }

    public IraUserAuth(Integer empNo) {
        this.empNo = empNo;
    }

    public IraUserAuth(Integer empNo, String password, Character isAllowed) {
        this.empNo = empNo;
        this.password = password;
        this.isAllowed = isAllowed;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(Character isAllowed) {
        this.isAllowed = isAllowed;
    }

    public IraUserRoles getIraUserRoles() {
        return iraUserRoles;
    }

    public void setIraUserRoles(IraUserRoles iraUserRoles) {
        this.iraUserRoles = iraUserRoles;
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
        if (!(object instanceof IraUserAuth)) {
            return false;
        }
        IraUserAuth other = (IraUserAuth) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraUserAuth[ empNo=" + empNo + " ]";
    }
    
}
