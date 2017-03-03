/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.query.DataQuery;
import java.io.Serializable;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 *
 * @author DIVYA
 */

@SessionScoped
@ManagedBean(name = "login", eager = true)
public class LoginController implements Serializable{
    
    
    private int empNo;
    private String password;
    private String privilege;
    private char isAllowed;
    private String userName;
    private  int roleId;

    public  int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

   
    private final DataQuery query=new DataQuery();

    public String loginControl(Integer emp,String pass){
        LoginController obj;
        obj=query.loginControl(emp, "jeswin");
        
        if(obj!=null){
            this.userName=obj.userName;
            this.privilege=obj.privilege;
            this.roleId=obj.roleId;
            
        return "home";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Employee No Invalid!!"));
        return "";
    }
    
        public boolean check()
        {
           if( query.checkCreation(empNo))
           {
               return true;
           }
           return false;
        }

    
     public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public char getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(char isAllowed) {
        this.isAllowed = isAllowed;
    }


    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public  int getEmpNO()
    {
        return empNo;
    }
     
}
