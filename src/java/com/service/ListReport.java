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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author DIVYA
 */
@ManagedBean
@RequestScoped
public class ListReport implements Serializable{
    
    private List<IraIncidentReportDetails> ird;
      @ManagedProperty("#{login}")
    LoginController login;

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }
    @ManagedProperty("#{dataQuery}")
    DataQuery query;

    public List<IraIncidentReportDetails> getIrd() {
        return ird;
    }

    public void setIrd(List<IraIncidentReportDetails> ird) {
        this.ird = ird;
    }

    public DataQuery getQuery() {
        return query;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }
    public String viewReport(int id)
    {
        
        System.out.println("this is report id");
        System.out.println(id);
        return "ViewReport.xhtml?id="+id;
    }
    @PostConstruct
    public void init()
    {
        ird=new ArrayList<>();
        ird=query.getIrdList(login.getRoleId(),login.getEmpNo());
    }
    
}
