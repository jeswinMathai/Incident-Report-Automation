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
@Table(name = "IRA_ROLE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IraRoleMaster.findAll", query = "SELECT i FROM IraRoleMaster i"),
    @NamedQuery(name = "IraRoleMaster.findByRoleId", query = "SELECT i FROM IraRoleMaster i WHERE i.roleId = :roleId"),
    @NamedQuery(name = "IraRoleMaster.findByRoleName", query = "SELECT i FROM IraRoleMaster i WHERE i.roleName = :roleName"),
    @NamedQuery(name = "IraRoleMaster.findByRoleWeight", query = "SELECT i FROM IraRoleMaster i WHERE i.roleWeight = :roleWeight"),
    @NamedQuery(name = "IraRoleMaster.findByIsReportCreationAllowed", query = "SELECT i FROM IraRoleMaster i WHERE i.isReportCreationAllowed = :isReportCreationAllowed"),
    @NamedQuery(name = "IraRoleMaster.findByIsActive", query = "SELECT i FROM IraRoleMaster i WHERE i.isActive = :isActive")})
public class IraRoleMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_WEIGHT")
    private int roleWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_REPORT_CREATION_ALLOWED")
    private Character isReportCreationAllowed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ACTIVE")
    private Character isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<IraUserRoles> iraUserRolesCollection;

    public IraRoleMaster() {
    }

    public IraRoleMaster(Integer roleId) {
        this.roleId = roleId;
    }

    public IraRoleMaster(Integer roleId, String roleName, int roleWeight, Character isReportCreationAllowed, Character isActive) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleWeight = roleWeight;
        this.isReportCreationAllowed = isReportCreationAllowed;
        this.isActive = isActive;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleWeight() {
        return roleWeight;
    }

    public void setRoleWeight(int roleWeight) {
        this.roleWeight = roleWeight;
    }

    public Character getIsReportCreationAllowed() {
        return isReportCreationAllowed;
    }

    public void setIsReportCreationAllowed(Character isReportCreationAllowed) {
        this.isReportCreationAllowed = isReportCreationAllowed;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<IraUserRoles> getIraUserRolesCollection() {
        return iraUserRolesCollection;
    }

    public void setIraUserRolesCollection(Collection<IraUserRoles> iraUserRolesCollection) {
        this.iraUserRolesCollection = iraUserRolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IraRoleMaster)) {
            return false;
        }
        IraRoleMaster other = (IraRoleMaster) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.IraRoleMaster[ roleId=" + roleId + " ]";
    }
    
}
