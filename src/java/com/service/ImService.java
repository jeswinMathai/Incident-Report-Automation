package com.service;
import com.entity.IraIncidentMaster;
import com.query.DataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.domain.Im;

 
@ManagedBean(name = "imService")
@ApplicationScoped
public class ImService {
     
    public List<Im> createIms() {
        List<Im> list = new ArrayList<Im>();
        DataQuery query=new DataQuery();
        List <IraIncidentMaster> iM=query.createIm();
        for(IraIncidentMaster im:iM)
        {
            list.add(new Im(im.getIncidentId(),im.getIncidentName(),im.getIsActive()));
        }
         
        return list;
    }
    public List<Im> createIms(char y) {
        List<Im> list = new ArrayList<Im>();
        DataQuery query=new DataQuery();
        List <IraIncidentMaster> iM=query.createIm(y);
        for(IraIncidentMaster im:iM)
        {
            list.add(new Im(im.getIncidentId(),im.getIncidentName(),im.getIsActive()));
        }
         
        return list;
    }
    
    
    public void commitIm(List<Im> list)
    {
        DataQuery query=new DataQuery();
        List <IraIncidentMaster> IM=new ArrayList<IraIncidentMaster>();
        for(Im im:list)
         {
             if(im.getStatus().equals("new"))
             {
                 IM.add(new IraIncidentMaster(im.getId(),im.getName(),im.getIsActive()));
                 
             }
         }
        query.writeIm(IM);
    }
     public void commitImUpd(List <Im> list)
    {
        DataQuery query=new DataQuery();
        List <IraIncidentMaster> IM=new ArrayList<IraIncidentMaster>();
    
        for(Im im:list)
         {
     
             if(im.getStatus().equals("update"))
             {
                 IM.add(new IraIncidentMaster(im.getId(),im.getName(),im.getIsActive()));
              
             }
         }
        query.updateIm(IM);
    }
 
}
     