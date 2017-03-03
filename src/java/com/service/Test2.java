/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DIVYA
 */
 @ManagedBean
    @SessionScoped
public class Test2 {
     private boolean stat;

    public boolean isStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }
     @PostConstruct
     public void init()
     {
         stat=false;
     }
     public String send()
     {
         stat=true;
         return "test.jsp";
         
     }
    
   
    
}
