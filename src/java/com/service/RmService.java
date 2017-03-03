package com.service;
import com.query.DataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.domain.Rm;
import com.entity.IraRoleMaster;

 
@ManagedBean(name = "rmService")
@ApplicationScoped
public class RmService {
     
    public List<Rm> createRms() {
        List<Rm> list = new ArrayList<Rm>();
        DataQuery query=new DataQuery();
        List <IraRoleMaster> rM=query.createRm();
        for(IraRoleMaster rm:rM)
        {
            list.add(new Rm(rm.getRoleId(),rm.getRoleName(),rm.getRoleWeight(),rm.getIsReportCreationAllowed(),rm.getIsActive()));
        }
         
        return list;
    }
     public List<Rm> createRms(char y) {
        List<Rm> list = new ArrayList<Rm>();
        DataQuery query=new DataQuery();
        List <IraRoleMaster> rM=query.createRm(y);
        for(IraRoleMaster rm:rM)
        {
            list.add(new Rm(rm.getRoleId(),rm.getRoleName(),rm.getRoleWeight(),rm.getIsReportCreationAllowed(),rm.getIsActive()));
        }
         
        return list;
    }
    public void commitRm(List <Rm> list)
    {
        DataQuery query=new DataQuery();
        List <IraRoleMaster> RM=new ArrayList<IraRoleMaster>();
        for(Rm rm:list)
         {
             if(rm.getStatus().equals("new"))
             {
                 RM.add(new IraRoleMaster(rm.getId(),rm.getName(),rm.getWeight(),rm.isReportCreationAllowed,rm.getIsActive()));
                 
             }
         }
        query.writeRm(RM);
    }
     public void commitRmUpd(List <Rm> list)
    {
        DataQuery query=new DataQuery();
        List <IraRoleMaster> RM=new ArrayList<IraRoleMaster>();
    
        for(Rm rm:list)
         {
     
             if(rm.getStatus().equals("update"))
             {
                 RM.add(new IraRoleMaster(rm.getId(),rm.getName(),rm.getWeight(),rm.getIsReportCreationAllowed(),rm.getIsActive()));
              
             }
         }
        query.updateRm(RM);
    }
 
}
     