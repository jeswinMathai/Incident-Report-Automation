/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.controller.LoginController;
import com.entity.IraIncidentReportDetails;
import com.query.DataQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DIVYA
 */
@ManagedBean
@ViewScoped
public class Search implements Serializable{
    private List<IraIncidentReportDetails> ird;
    private String get;

    public List<IraIncidentReportDetails> getIrd() {
        return ird;
    }

    public void setIrd(List<IraIncidentReportDetails> ird) {
        this.ird = ird;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }
     @ManagedProperty("#{dataQuery}")
    DataQuery query;
     
       @ManagedProperty("#{login}")
    LoginController login;

    public DataQuery getQuery() {
        return query;
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }
     
    
    
    public void getChange()
    {
        System.out.println(get);
        ird=query.getIrdForSearch(get,login.getRoleId());
        System.out.println(ird.size());
        
        

    }
    @PostConstruct
    void init()
    {
      
        ird=new ArrayList<IraIncidentReportDetails>();
    }
    
    
    
}
