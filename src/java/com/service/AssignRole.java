/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.domain.IPE;
import com.domain.Rm;
import com.entity.IraPkiEmpInfoAll;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DIVYA
 */
@ManagedBean
@ViewScoped

public class AssignRole implements Serializable{
    private List <IPE> ipeE;
    @ManagedProperty("#{ipeService}")
    IpeService IPe;
     @ManagedProperty("#{rmService}")
    RmService rm;
    
    private String name;
    private int empNo;
   private List<Rm> rms;
   private int rid;
   private String role;
   private List<IPE> users;

    public List<Rm> getRms() {
        return rms;
    }

    public void setRms(List<Rm> rms) {
        this.rms = rms;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public RmService getRm() {
        return rm;
    }

    public void setRm(RmService rm) {
        this.rm = rm;
    }

  
    
    private List <IPE>  ipe;

    public IpeService getIPe() {
        return IPe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public void setIPe(IpeService IPe) {
        this.IPe = IPe;
    }

    public List<IPE> getIpe() {
        return ipe;
    }

    public void setIpe(List<IPE> ipe) {
        this.ipe = ipe;
    }

    public List<IPE> getIpeE() {
        return ipeE;
    }

    public void setIpeE(List<IPE> ipeE) {
        this.ipeE = ipeE;
    }
    
    
    @PostConstruct
    public void init()
    {
        ipeE=new ArrayList<IPE>();
        ipe=new ArrayList<IPE>();
        rms=rm.createRms('Y');
        users=new ArrayList<IPE>();
        empNo=0;
       
      
    }
    
        public void onChange()
    {
        if(name!=null||empNo!=0)
        {
            if(name!=null&&!name.trim().equals(""))
            ipeE=IPe.getTarget(name);
            else
                ipeE=IPe.getTarget(empNo);
        }    
        
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
        
     public void onChangeViewU()
    {
        for(Rm r:rms)
        {
            if(r.getId()==rid)
            {
                role=r.getName();
                break;
            }
        }
      users=IPe.getUsers(rid);
        
    }
        public void commit(int empNo,int id,String task)
        {
            System.out.println("hello");
            if(task.equals("assign"))
            {
                if(id!=0)
                {
                  
                    IPe.commit(empNo, id);
                    
                }
                else
                {
                    
                }
            }
            else
            {
            IPe.commit(empNo, id);
            }
            onChange();
         //return "AssignRole";   
         }

    public List<IPE> getUsers() {
        return users;
    }

    public void setUsers(List<IPE> users) {
        this.users = users;
    }
    public boolean userIsEmpty()
    {
        return users.isEmpty();
    }
}
