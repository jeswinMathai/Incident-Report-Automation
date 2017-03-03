/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author DIVYA
 */
@ManagedBean(name="IPE")
public class IPE implements Serializable{
    private int empNo;
    private String designation;
    private String division;
    private String group;
    private String name;
    private String role;
    private int id;

    
    public IPE(int empNo,String designation,String div,String group,String name,String role,int id)
    {
        this.empNo=empNo;
        this.designation=designation;
        this.division=div;
        this.group=group;
        this.name=name;
        this.role=role;
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String div) {
        this.division = div;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.empNo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IPE other = (IPE) obj;
        if (this.empNo != other.empNo) {
            return false;
        }
        return true;
    }
   
}
