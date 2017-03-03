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
@ManagedBean(name="IRC")
public class IRc implements Serializable {
    public int id;
   public int id2;
    public char isMadatory;
    public char canBeRepeated;
  public int sno;
 
  public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public char getIsMadatory() {
        return isMadatory;
    }

    public void setIsMadatory(char isMadatory) {
        this.isMadatory = isMadatory;
    }

    public char getCanBeRepeated() {
        return canBeRepeated;
    }

    public void setCanBeRepeated(char canBeRepeated) {
        this.canBeRepeated = canBeRepeated;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
 public IRc(int id,String description,int idi) {
        this.id = id;
        this.description = description; 
        this.isMadatory='Y';
        this.canBeRepeated='Y';
        this.id2=idi;
        this.sno=0;
    }
  public IRc(int id,String description,char isman,char canbeR,int idi,int sno) {
        this.id = id;
        this.description = description; 
        this.isMadatory=isman;
        this.canBeRepeated=canbeR;
        this.id2=idi;
         this.sno=sno;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
