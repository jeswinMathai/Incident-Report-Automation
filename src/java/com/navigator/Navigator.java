/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navigator;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author DIVYA
 */
@ManagedBean
public class Navigator {
    private String pageName="home";


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
    
    
    

