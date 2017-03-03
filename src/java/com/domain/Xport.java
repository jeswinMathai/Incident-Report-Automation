/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

/**
 *
 * @author DIVYA
 */
public class Xport {
    private String one;
    private String two;
    private String three;
    private String four;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }
    public static Xport newObj(Xport i)
    {
        Xport j=new Xport();
        j.setOne(i.getOne());
        j.setTwo(i.getTwo());
        j.setThree(i.getThree());
        j.setFour(i.getFour());
        return j;
    }
    
}
