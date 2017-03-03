package com.service;
import com.domain.IRc;
import com.entity.IraIncidentMaster;
import com.query.DataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.domain.Im;
import com.domain.Qm;
import com.entity.IraIncidentQuestionRelation;
import com.entity.IraQuestionMaster;
import java.util.Collection;

 
@ManagedBean(name = "iqrService")
@ApplicationScoped
public class IQrService {
     
  
     public List <IRc> getTarget(int i)
     {
         try
         {
         List <IRc> QT=new ArrayList<IRc>();
         IraQuestionMaster q;
         DataQuery query=new DataQuery();
            List <IraIncidentQuestionRelation> IQR=new ArrayList<IraIncidentQuestionRelation>();
            IQR=query.createIQr(i);
            
            for(IraIncidentQuestionRelation iqr:IQR)
            {
           
                q=query.createQm(iqr);
                QT.add(new IRc(q.getQuestionId(), q.getQuestionDescription(),iqr.getIsMandatory(),iqr.getCanBeRepeated(),i,iqr.getSrNo()));
            }
            return QT;
             } catch (Exception e) {
            return new ArrayList<IRc>();
        }
     }
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
     
}
     