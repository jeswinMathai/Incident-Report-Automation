/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.domain.IRc;
import com.domain.Im;
import com.domain.Qm;
import com.entity.IraQuestionMaster;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author DIVYA
 */
@ManagedBean
@ViewScoped
public class ViewIQR{

    

     private List<IRc> irci;

          private List<IRc> ircI;
          private List<IRc> ircQ;
          private List<Im> ims;
          private List<Qm> qms;
          private int id;
          private boolean next;
          private int qid;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public List<Im> getIms() {
        return ims;
    }

    public void setIms(List<Im> ims) {
        this.ims = ims;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManagedProperty("#{iqrService}")
   IQrService iqr;
  @ManagedProperty("#{imService}")
   ImService im;
    @ManagedProperty("#{qmService}")
   QmService qm;
   
    public List<IRc> getIrci() {
        return irci;
    }
    public String returnIm()
    {
        for(Im i:ims)
        {
            if(i.getId()==this.id)
                return i.getName();
    
        }
        return "No Incident Selected";
    }
    
      public String returnQm()
    {
        for(Qm i:qms)
        {
            if(i.getId()==this.qid)
                return i.getDescription();
    
        }
        return "No Question Selected";
    }
    public void setIrci(List<IRc> irci) {
        this.irci = irci;
    }

    public List<IRc> getIrcI() {
        return ircI;
    }

    public void setIrcI(List<IRc> ircI) {
        this.ircI = ircI;
    }

    public List<IRc> getIrcQ() {
        return ircQ;
    }

    public void setIrcQ(List<IRc> ircQ) {
        this.ircQ = ircQ;
    }

    public ImService getIm() {
        return im;
    }

    public void setIm(ImService im) {
        this.im = im;
    }

    public QmService getQm() {
        return qm;
    }

    public void setQm(QmService qm) {
        this.qm = qm;
    }

    public IQrService getIqr() {
        return iqr;
    }

    public void setIqr(IQrService iqr) {
        this.iqr = iqr;
    }

    public List<Qm> getQms() {
        return qms;
    }

    public void setQms(List<Qm> qms) {
        this.qms = qms;
    }
     
  
     
    @PostConstruct
    public void init() {
        qms=qm.createQms('Y');
        irci=new ArrayList<IRc>();
        ircI=iqr.getTarget(0);
        ircQ=iqr.getSource(0);
        ims =im.createIms('Y');
        next=false;
        System.out.println("hello");
                
         
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
    public void onChange()
    {
        next=true;
    }
    public void onChangeViewQ()
    {
        ircI=iqr.getTarget(id);
        
    }
    public void onChangeViewI()
    {
         System.out.println(ims.size());
        ircI=iqr.getIm(this.qid);
        System.out.println(ims.size());
        System.out.println("helooasdadsadsads12321");
        
    }
    public boolean isEmpty()
    {
       return ircI.isEmpty();
    }
    public void onIncidentChange()
    {
        ircI=new ArrayList<IRc>();
        ircQ=new ArrayList<IRc>();
        ircI=iqr.getTarget(id);
        ircQ=iqr.getSource(id);
        irci=new ArrayList<IRc>();
        for(IRc iri:ircI)
            irci.add(iri);
  
              
        System.out.println("incident change");
        ims =im.createIms();
        next=true;
        System.out.println(next);
    }
    public void clearIms()
    {
        ims=new ArrayList<Im>();
    }
    
     public  String returnQ(Collection<Qm> c, int a) {
        
    for(Qm o : c) {
        if(o != null && o.getId()==a) {
           return o.description;
        }
    }
    return null;
    }
     public  int returnQs(Collection<IRc> c, int a) {
        
    for(IRc o : c) {
        if(o != null && o.getId()==a) {
           return o.getSno();
        }
    }
    return 0;
    }
     public void test()
     {
         System.out.println("hellllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllo");
     }
    public void add(int id,char is,char can)
    {
        
     
    
            System.out.println(ircQ.size());
                        System.out.println(ircI.size());
    ircI.add(new IRc(id, returnQ(qms, id),is,can, this.id,0));
    ircQ=ircQ.stream().filter(IRc -> IRc.getId()!=id).collect(Collectors.toList());
    }
      public void remove(int id)
    {
        
     
    
            System.out.println(ircQ.size());
                        System.out.println(ircI.size());
    ircQ.add(new IRc(id, returnQ(qms, id),'Y','Y', this.id,returnQs(ircI, id)));
    ircI=ircI.stream().filter(IRc -> IRc.getId()!=id).collect(Collectors.toList());
    }
    public boolean checkChange()
    {
        System.out.println("irci size");
        System.out.println(irci.size());
        System.out.println("ircI size");
        System.out.println(ircI.size());
        if(irci.size()!=ircI.size())
            return true;
        else
        {
            for(int i=0;i<irci.size();i++)
            {
                if(irci.get(i)!=ircI.get(i))
                    return true;
            }
            return false;
        }
    }
    public void commitIRc()
    {
        boolean match;
     
       List <Integer> present=new ArrayList<Integer>();
       List <IRc> add =new ArrayList<IRc>();
         List <IRc> remove =new ArrayList<IRc>();
       
            for(int i=0;i<ircI.size();i++)
            {  match=false;
                for(int j=0;j<irci.size();j++)
                {
                  
                    
                    if(ircI.get(i).getId()==irci.get(j).getId())
                    {
                        present.add(ircI.get(i).getId());
                        match=true;
                        break;
                    }
                }

                
                if(!match)
                    add.add(ircI.get(i));
               }
               match=false;
                for(IRc ir:irci)
                {
                    match=false;
                       for(int k:present)
                    {
                        if(ir.getId()==k)
                        {
                            match=true;
                            break;
                        }
                    }
                       if(!match)
                           remove.add(ir);
                }
                
           
           
            if(!add.isEmpty())
                iqr.addIRc(add);
             if(!remove.isEmpty())
                iqr.removeIRc(remove);
             
             onIncidentChange();
            
            
        }
    

}
    
     
