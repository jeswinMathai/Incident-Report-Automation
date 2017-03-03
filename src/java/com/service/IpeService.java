/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;
import com.domain.IPE;
import com.domain.IRc;
import com.entity.IraPkiEmpInfoAll;

import com.query.DataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


import java.util.Collection;

 
@ManagedBean(name = "ipeService")
@ApplicationScoped

public class IpeService {
    
    public List <IPE> getTarget(int empNo)
     {
         try
         {
         List <IPE> IP=new ArrayList<IPE>();
      
         DataQuery query=new DataQuery();
           
            IP=query.createIPET(empNo);
          
               return IP;
             } catch (Exception e) {
            return new ArrayList<IPE>();
        }
     }
     
  
     public List <IPE> getTarget(String name)
     {
         try
         {
         List <IPE> IP=new ArrayList<IPE>();
      
         DataQuery query=new DataQuery();
           
            IP=query.createIPET(name);
          
               return IP;
             } catch (Exception e) {
            return new ArrayList<IPE>();
        }
     }
     public void commit(int empNo,int id)
     {
           try
         {
         
      
         DataQuery query=new DataQuery();
           
         query.commitIUR( empNo, id);
          
             
             } catch (Exception e) {
           
        }
         
     }
     public List<IPE> getUsers(int id)
     {
       try
         {
         
      
         DataQuery query=new DataQuery();
           
         return query.getUsers( id);
          
             
             } catch (Exception e) {
                 return null;
           
        }
     }
     /*
     public List <IRc> getSource(int i)
     {
         try
         {
         List <IRc> QT=new ArrayList<IRc>();
         
         DataQuery query=new DataQuery();
            List <IraQuestionMaster> IQR=new ArrayList<IraQuestionMaster>();
            IQR=query.createIQrS(i);
            for(IraQuestionMaster q:IQR)
            {
                QT.add(new IRc(q.getQuestionId(), q.getQuestionDescription(),'Y','Y',i,0));   
            }
            return QT;
             } catch (Exception e) {
              return new ArrayList<IRc>();
        }
     }
     public List <IRc> getIm(int id)
     {
        List <IRc> QT=new ArrayList<IRc>();
          DataQuery query=new DataQuery();
         List <IraIncidentMaster> imforQ=query.createImforQ(id);
         for (IraIncidentMaster i:imforQ)
         {
             QT.add(new IRc(i.getIncidentId(), i.getIncidentName(),i.getIsActive(),'Y',id,0));
         }
         return QT;
     }
     public void addIRc(List <IRc> irc)
     {
         try
         {
        
 
         DataQuery query=new DataQuery();
            IraQuestionMaster qm=new IraQuestionMaster();
            IraIncidentMaster im=new IraIncidentMaster();
            List <IraIncidentQuestionRelation> IRC=new ArrayList<IraIncidentQuestionRelation>();
          
             System.out.println(irc.size());
             
            for(IRc i:irc)
            {
                qm=query.getQm(i.getId());
                im=query.getIm(i.getId2());
                IraIncidentQuestionRelation ir=new IraIncidentQuestionRelation(0,'Y','Y');
                ir.setIncidentId(im);
                ir.setQuestionId(qm);
                IRC.add(ir);   
              
            }
        query.writeIRc(IRC);
             } catch (Exception e) {
                 
        }
     }
     public void removeIRc(List <IRc> irc)
     {
         try
         {
        
 
         DataQuery query=new DataQuery();
            IraQuestionMaster qm=new IraQuestionMaster();
            IraIncidentMaster im=new IraIncidentMaster();
            List <IraIncidentQuestionRelation> IRC=new ArrayList<IraIncidentQuestionRelation>();
          
             System.out.println(irc.size());
             
            for(IRc i:irc)
            {
                qm=query.getQm(i.getId());
                im=query.getIm(i.getId2());
                IraIncidentQuestionRelation ir=new IraIncidentQuestionRelation(i.getSno(),'Y','Y');
                ir.setIncidentId(im);
                ir.setQuestionId(qm);
                IRC.add(ir);   
              
            }
        query.removeIRc(IRC);
             } catch (Exception e) {
                 
        }
     }
*/
     
}
     