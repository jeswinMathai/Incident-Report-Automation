package com.service;
import com.domain.Qm;
import com.entity.IraQuestionMaster;
import com.query.DataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


 
@ManagedBean(name = "qmService")
@ApplicationScoped
public class QmService {
     
    public List<Qm> createQms() {
        List<Qm> list = new ArrayList<Qm>();
        DataQuery query=new DataQuery();
        List <IraQuestionMaster> QM=query.createQm();
        for(IraQuestionMaster qm:QM)
        {
            list.add(new Qm(qm.getQuestionId(),qm.getQuestionDescription(),qm.getIsActive()));
        }
         
        return list;
    }
      public List<Qm> createQms(char y) {
        List<Qm> list = new ArrayList<Qm>();
        DataQuery query=new DataQuery();
        List <IraQuestionMaster> QM=query.createQm(y);
        for(IraQuestionMaster qm:QM)
        {
            list.add(new Qm(qm.getQuestionId(),qm.getQuestionDescription(),qm.getIsActive()));
        }
         
        return list;
    }
    public void commitQm(List <Qm> list)
    {
        DataQuery query=new DataQuery();
        List <IraQuestionMaster> QM=new ArrayList<IraQuestionMaster>();
        for(Qm qm:list)
         {
             if(qm.getStatus().equals("new"))
             {
                 QM.add(new IraQuestionMaster(qm.getId(),qm.getDescription(),qm.getIsActive()));
                 
             }
         }
        query.writeQm(QM);
    }
     public void commitQmUpd(List <Qm> list)
    {
        DataQuery query=new DataQuery();
        List <IraQuestionMaster> QM=new ArrayList<IraQuestionMaster>();
    
        for(Qm qm:list)
         {
     
             if(qm.getStatus().equals("update"))
             {
                 QM.add(new IraQuestionMaster(qm.getId(),qm.getDescription(),qm.getIsActive()));
              
             }
         }
        query.updateQm(QM);
    }
 
}
     