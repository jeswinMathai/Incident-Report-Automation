/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DIVYA
 */

    
@ManagedBean(name="test")
@RequestScoped
public class Test {

    @ManagedProperty("#{param.no}")
    private String no;

    @ManagedProperty("#{param.nos}")
    private String nos;
    
    @ManagedProperty("#{param.nos}")
    private String sum;
   

    @PostConstruct
    public void init() {
        // Do here your thing with those parameters.
        System.out.println(no + " " + nos+"="+sum);
    }

    // ...

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getNos() {
            return nos;
        }

        public void setNos(String nos) {
            this.nos = nos;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }
    }
    

