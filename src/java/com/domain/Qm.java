/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@ManagedBean(name="QM")


public class Qm implements Serializable {

    public int id;
    public String description;
    public char isActive;
    public String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    } public void setDescription() {
        this.description = "";
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public Qm() {}

    public Qm(int id,String description,char isActive) {
        this.id = id;
        this.description = description; 
        this.isActive=isActive;
        this.status="old";
    }
    public Qm(int id,String description,char isActive,String status) {
        this.id = id;
        this.description = description; 
        this.isActive=isActive;
        this.status=status;
    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

 

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Im other = (Im) obj;
        if ((this.description == null) ? (other.name != null) : !this.description.equals(other.id)) {
            return false;
        }
        return true;
    }
}
