/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.showcase.service;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;

public class DynamicField implements Serializable {  
  
    private String label;  
    private Object value;  
    private boolean required;  
    private List<SelectItem> selectItems;  
  
    public DynamicField() {  
    }  
  
    public DynamicField(String label, Object value, boolean required, List<SelectItem> selectItems) {  
        this.label = label;  
        this.value = value;  
        this.required = required;  
        this.selectItems = selectItems;  
    }  
  
    public String getLabel() {  
        return label;  
    }  
  
    public void setLabel(String label) {  
        this.label = label;  
    }  
  
    public Object getValue() {  
        return value;  
    }  
  
    public void setValue(Object value) {  
        this.value = value;  
    }  
  
    public boolean isRequired() {  
        return required;  
    }  
  
    public void setRequired(boolean required) {  
        this.required = required;  
    }  
  
    public List<SelectItem> getSelectItems() {  
        return selectItems;  
    }  
  
    public void setSelectItems(List<SelectItem> selectItems) {  
        this.selectItems = selectItems;  
    }  
}  
           