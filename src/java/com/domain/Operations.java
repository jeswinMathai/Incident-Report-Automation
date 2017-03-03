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
public class Operations {
    
    private String operation;
    private boolean render;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }
    public Operations(String operation,boolean render)
            
    {
        this.operation=operation;
        this.render=render;
    }
    
}
