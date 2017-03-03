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
@ManagedBean(name="IM")


public class Im implements Serializable {

    public int id;
    public String name;
    public char isActive;
    public String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } public void setName() {
        this.name = "";
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
    

    public Im() {}

    public Im(int id,String name,char isActive) {
        this.id = id;
        this.name = name; 
        this.isActive=isActive;
        this.status="old";
    }
    public Im(int id,String name,char isActive,String status) {
        this.id = id;
        this.name = name; 
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
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.id)) {
            return false;
        }
        return true;
    }
}
