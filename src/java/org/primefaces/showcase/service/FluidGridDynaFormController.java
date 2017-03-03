/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.showcase.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;

/**
 *
 * @author DIVYA
 */
@ManagedBean  
@ViewScoped  
public class FluidGridDynaFormController implements Serializable {  
  
    private List<FluidGridItem> items;  
  
    @PostConstruct  
    protected void initialize() {  
        items = new ArrayList<FluidGridItem>();  
  
        List<SelectItem> selectItems = new ArrayList<SelectItem>();  
        selectItems.add(new SelectItem("1", "Label 1"));  
        selectItems.add(new SelectItem("2", "Label 2"));  
        selectItems.add(new SelectItem("3", "Label 3"));  
  
        items.add(new FluidGridItem(new DynamicField("First Label", null, true, null), "input"));  
        items.add(new FluidGridItem(new DynamicField("Second Label", "Some default value", false, null), "input"));  
        items.add(new FluidGridItem(new DynamicField("Third Label", null, false, selectItems), "select"));  
        items.add(new FluidGridItem(new DynamicField("Fourth Label", "2", false, selectItems), "select"));  
        items.add(new FluidGridItem(new DynamicField("Fifth Label", null, true, null), "calendar"));  
        items.add(new FluidGridItem(new DynamicField("Sixth Label", new Date(), false, null), "calendar"));  
        items.add(new FluidGridItem(new DynamicField("Seventh Label", null, false, null), "input"));  
        items.add(new FluidGridItem(new DynamicField("Eighth Label", null, false, selectItems), "select"));  
        items.add(new FluidGridItem(new DynamicField("Ninth Label", null, false, null), "calendar"));  
    }  
  
    public List<FluidGridItem> getItems() {  
        return items;  
    }  
}  