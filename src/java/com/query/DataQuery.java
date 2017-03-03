/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.query;

import com.controller.LoginController;
import com.domain.IPE;
import com.entity.IncidentReportQuestionnaire;
import com.entity.IraBuildingList;
import com.entity.IraDocumentsAttached;
import com.entity.IraIncidentMaster;
import com.entity.IraIncidentQuestionRelation;
import com.entity.IraIncidentReportDetails;
import com.entity.IraIncidentReportWorkFlow;
import com.entity.IraPersonsInvolved;
import com.entity.IraPkiCertificates;
import com.entity.IraPkiEmpInfoAll;
import com.entity.IraQuestionMaster;
import com.entity.IraReportAuthoritySigns;
import com.entity.IraRoleMaster;
import com.entity.IraUserAuth;

import com.entity.IraUserRoles;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DIVYA
 */
@ManagedBean(name = "dataQuery")
@ApplicationScoped
public class DataQuery implements Serializable{
     private static final long serialVersionUID = 1L;
    EntityManagerFactory emf;
    EntityManager em;
    LoginController obj;
    
 public DataQuery()
 {
     emf=Persistence.createEntityManagerFactory("jsfloginPU");
     em=emf.createEntityManager();
     em.getTransaction().begin();
     
    }
    public LoginController loginControl(Integer empNo,String password ){
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        try {
            IraUserRoles i=em.createNamedQuery("IraUserRoles.findByEmpNo",IraUserRoles.class).setParameter("empNo", empNo).getSingleResult();
                   
            if(i!=null)
            {
                
                
                IraUserAuth j=em.createNamedQuery("IraUserAuthCheck", IraUserAuth.class).setParameter("empNo",empNo).setParameter("password",password).getSingleResult();
                if(j!=null)
                {
                     System.out.println("jeswin\n jeswin\n"+i.getRoleId().getRoleId()+"\njeswin\njeswin +"+password);
               
                    IraRoleMaster k=em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId",i.getRoleId().getRoleId()).getSingleResult();
                       if(k!=null)
                       {
                      obj=new LoginController();
                      obj.setRoleId(k.getRoleId());
                       obj.setPrivilege(k.getRoleName());
                           IraPkiEmpInfoAll l=em.createNamedQuery("IraPkiEmpInfoAll.findByEmpNo",IraPkiEmpInfoAll.class).setParameter("empNo", empNo).getSingleResult();
                      obj.setUserName(l.getName());
                      return obj;
                                       
                    }
 
                 }
              
            }
             
                return null;
        } catch (Exception e) {
            return null;
        }
    }
         public List <IraIncidentMaster> createIm()
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        try {
                        List <IraIncidentMaster> list=em.createNamedQuery("IraIncidentMaster.findAll",IraIncidentMaster.class).getResultList();
                        return list;
             
        } catch (Exception e) {
            return null;
        }
 }
          public List <IraIncidentMaster> createIm(char y)
         { 
        try {
                        List <IraIncidentMaster> list=em.createNamedQuery("IraIncidentMaster.findByIsActive",IraIncidentMaster.class).setParameter("isActive", y).getResultList();
                        return list;
             
        } catch (Exception e) {
            return null;
        }
    
    

    }
         public void writeIm(List <IraIncidentMaster> IM)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             for(IraIncidentMaster im :IM)
             {
                 em.persist(im);
                em.flush();
                em.clear();
             }
             em.getTransaction().commit();

         }
         public void writeIRc(List <IraIncidentQuestionRelation> IRc)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             for(IraIncidentQuestionRelation irc :IRc)
             {
                
                 Integer srNo =(Integer)em.createQuery("select max(i.srNo) from IraIncidentQuestionRelation i").getSingleResult();
                 if(srNo==null)
                     srNo=0;
                 System.out.println(srNo);
                 irc.setSrNo(srNo+1);
                
                  em.persist(irc);
                
                em.flush();
                em.clear();
             }
             em.getTransaction().commit();           
         }
         public void updateIm(List <IraIncidentMaster> IM)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          
             for(IraIncidentMaster im :IM)
             {
                
                  IraIncidentMaster Im = (IraIncidentMaster)em.find(IraIncidentMaster.class, im.getIncidentId());
                Im.setIncidentName(im.getIncidentName());
                Im.setIsActive(im.getIsActive());
                em.flush();
                em.clear();
             }
             em.getTransaction().commit();             
         }
         public void removeIRc(List <IraIncidentQuestionRelation> IRc)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          
             for(IraIncidentQuestionRelation irc :IRc)
             {
                
                IraIncidentQuestionRelation IRC = (IraIncidentQuestionRelation)em.find(IraIncidentQuestionRelation.class, irc.getSrNo());
                    em.remove(IRC);
             }
             em.getTransaction().commit();
         }
         
          public List <IraQuestionMaster> createQm()
                        { 
                             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                       try {
                                       List <IraQuestionMaster> list=em.createNamedQuery("IraQuestionMaster.findAll",IraQuestionMaster.class).getResultList();
                                       return list;

                       } catch (Exception e) {
                           return null;
                       }
         }
          
          public List <IraQuestionMaster> createQm(char y)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                        try {
                                        List <IraQuestionMaster> list=em.createNamedQuery("IraQuestionMaster.findByIsActive",IraQuestionMaster.class).setParameter("isActive",y ).getResultList();
                                        return list;

                        } catch (Exception e) {
                            return null;
                        }
         }
          
          public IraQuestionMaster createQm(IraIncidentQuestionRelation iqr)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                        try {
                                        IraQuestionMaster obj=em.createNamedQuery("IraQuestionMaster.findByQuestionId",IraQuestionMaster.class).setParameter("questionId", iqr.getQuestionId().getQuestionId()).getSingleResult();
                                        return obj;

                        } catch (Exception e) {
                            return null;
                        }
         }
          
           public IraIncidentMaster createIm(IraIncidentQuestionRelation iqr)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                        try {
                            
                                        IraIncidentMaster obj=em.createNamedQuery("IraIncidentMaster.findByIncidentId",IraIncidentMaster.class).setParameter("incidentId", iqr).getSingleResult();
                                        return obj;

                        } catch (Exception e) {
                            return null;
                        }
         }
          
          
          public List <IraIncidentQuestionRelation> createIQr(int i)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                        try {     IraIncidentMaster im=getIm(i);
                                        List <IraIncidentQuestionRelation> list=em.createNamedQuery("IraIncidentQuestionRelation.findByImId",IraIncidentQuestionRelation.class).setParameter("incidentId", im).getResultList();
                                      
                                      
                                        
                                        return list;

                        } catch (Exception e) {
                            return null;
                        }
         }
          
           public List <IraQuestionMaster> createIQrS(int i)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
              List <IraQuestionMaster>  qm=createQm('Y');
             
                        try {
                                 IraIncidentMaster im=getIm(i);
                            System.out.println(qm.size());
                                        
                                    List <IraIncidentQuestionRelation> IQR=new ArrayList<IraIncidentQuestionRelation>();
                                    System.out.println(im.getIncidentName());
                                    IQR=em.createNamedQuery("IraIncidentQuestionRelation.findByImId", IraIncidentQuestionRelation.class).setParameter("incidentId", im).getResultList();
                                       if(IQR!=null)
                                       {
                                      
                                       for(IraIncidentQuestionRelation iqr:IQR)
                                       {
                                           containsQ(qm, iqr.getQuestionId().getQuestionId());
                                       }
                                       }
                                    
                             return qm;
                        } catch (Exception e) {
                            return qm;
                        }
         }
           public List <IraIncidentMaster> createImforQ(int i)
         { 
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
              List <IraIncidentMaster>  im=new ArrayList<IraIncidentMaster>();
             
                        try {
                                 IraQuestionMaster qm=getQm(i);
                            
                                        
                                    List <IraIncidentQuestionRelation> IQR=new ArrayList<IraIncidentQuestionRelation>();
                                   
                                    IQR=em.createNamedQuery("IraIncidentQuestionRelation.findByQmId", IraIncidentQuestionRelation.class).setParameter("questionId", qm).getResultList();
                                         System.out.println("hello");
                                        System.out.println(IQR.size());
                                        System.out.println("hello");
                                    for(IraIncidentQuestionRelation iqr:IQR)
                                        im.add(iqr.getIncidentId());
                                   
                             return im;
                        } catch (Exception e) {
                            return im;
                        }
         }
           
         public static void containsQ(Collection<IraQuestionMaster> c, int a) {
             
                        for(IraQuestionMaster o : c) {
                            if(o != null && o.getQuestionId()==a) {
                                c.remove(o);
                            }
    }
}
 
    
    

   
        public void writeQm(List <IraQuestionMaster> QM)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                            for(IraQuestionMaster qm :QM)
                            {
                                em.persist(qm);
                               em.flush();
                               em.clear();
                            }
                            em.getTransaction().commit();           
        }
        public void updateQm(List <IraQuestionMaster> QM)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();

                   for(IraQuestionMaster qm :QM)
                   {

                        IraQuestionMaster Qm = (IraQuestionMaster)em.find(IraQuestionMaster.class, qm.getQuestionId());
                      Qm.setQuestionDescription(qm.getQuestionDescription());
                      Qm.setIsActive(qm.getIsActive());
                      em.flush();
                      em.clear();
                   }
                   em.getTransaction().commit();
          
        }
        public List <IraRoleMaster> createRm()
        { 
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                    try {
                                  List <IraRoleMaster> list=em.createNamedQuery("IraRoleMaster.findAll",IraRoleMaster.class).getResultList();
                                  return list;

                    } catch (Exception e) {
                      return null;
                    }



        }
          public List <IraRoleMaster> createRm(char y)
        { 
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                    try {
                                  List <IraRoleMaster> list=em.createNamedQuery("IraRoleMaster.findByIsActive",IraRoleMaster.class).setParameter("isActive", y).getResultList();
                                  return list;

                    } catch (Exception e) {
                      return null;
                    }



        }
        public void writeRm(List <IraRoleMaster> RM)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                for(IraRoleMaster rm :RM)
                {
                    em.persist(rm);
                   em.flush();
                   em.clear();
                }
                em.getTransaction().commit();            
        }
        public void updateRm(List <IraRoleMaster> RM)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();

                    for(IraRoleMaster rm :RM)
                    {

                         IraRoleMaster Qm = (IraRoleMaster)em.find(IraRoleMaster.class, rm.getRoleId());
                       Qm.setRoleName(rm.getRoleName());
                       Qm.setIsActive(rm.getIsActive());

                       Qm.setRoleWeight(rm.getRoleWeight());
                       Qm.setIsReportCreationAllowed(rm.getIsReportCreationAllowed());
                       em.flush();
                       em.clear();
                    }
                    em.getTransaction().commit();
                    
         }

        public IraQuestionMaster getQm(int id)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                return em.createNamedQuery("IraQuestionMaster.findByQuestionId", IraQuestionMaster.class).setParameter("questionId", id).getSingleResult();

        }
        public IraIncidentMaster getIm(int id)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                return em.createNamedQuery("IraIncidentMaster.findByIncidentId", IraIncidentMaster.class).setParameter("incidentId", id).getSingleResult();

        }
        public List<IPE> createIPET(String name)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();                                                                   
             
            List<IPE> IP=new ArrayList<IPE>();
         
            List <IraPkiEmpInfoAll> IpE=new ArrayList<IraPkiEmpInfoAll>();
            IraPkiEmpInfoAll ip=new IraPkiEmpInfoAll();
            IraRoleMaster irm=new IraRoleMaster();
            System.out.println("hello");
            String rolename=new String();
            int roleid=0;
                    rolename="none";
                    
            IpE=em.createNamedQuery("IraPkiEmpInfoAll.findByName",IraPkiEmpInfoAll.class).setParameter("name", "%"+name+"%").getResultList();
            System.out.println(IpE.size());
            for(IraPkiEmpInfoAll i:IpE)
            {
                IraUserRoles iur=new IraUserRoles();
                int emp=i.getEmpNo();
             
             try
             {
                iur=em.createNamedQuery("IraUserRoles.findByEmpNo",IraUserRoles.class).setParameter("empNo",emp).getSingleResult();
                 rolename=iur.getRoleId().getRoleName();
                    roleid=iur.getRoleId().getRoleId();
             }
             catch(Exception e) 
             {
                 roleid=0;
                    rolename="none";
                 
             }
                
                  
                    IP.add(new IPE(emp, i.getDesignation(), i.getDivShortName(), i.getGroupShortName(), i.getName(),rolename, roleid));
                System.out.println(IP.size());
            }
            return IP;
        }
         public List<IPE> createIPET(int no)
        {  
            if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            
            List<IPE> IP=new ArrayList<IPE>();
         
            List <IraPkiEmpInfoAll> IpE=new ArrayList<IraPkiEmpInfoAll>();
            IraPkiEmpInfoAll ip=new IraPkiEmpInfoAll();
            IraRoleMaster irm=new IraRoleMaster();
        
            String rolename=new String();
            int roleid=0;
                    rolename="none";
                    System.out.println(no);
            IpE=em.createNamedQuery("IraPkiEmpInfoAll.findByEmpNo",IraPkiEmpInfoAll.class).setParameter("empNo",no).getResultList();
            System.out.println(IpE.size());
            for(IraPkiEmpInfoAll i:IpE)
            {
                IraUserRoles iur=new IraUserRoles();
                int emp=i.getEmpNo();
                System.out.println("hellisd");
             try
             {
                iur=em.createNamedQuery("IraUserRoles.findByEmpNo",IraUserRoles.class).setParameter("empNo",emp).getSingleResult();
                 rolename=iur.getRoleId().getRoleName();
                    roleid=iur.getRoleId().getRoleId();
             }
             catch(Exception e) 
             {
                 roleid=0;
                    rolename="none";
                 
             }
                
                  
                    IP.add(new IPE(emp, i.getDesignation(), i.getDivShortName(), i.getGroupShortName(), i.getName(),rolename, roleid));
                System.out.println(IP.size());
            }
            return IP;
        }
         
         public void commitIUR(int empNo,int id)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             IraPkiEmpInfoAll emp=new IraPkiEmpInfoAll();
             IraRoleMaster rm=new IraRoleMaster();
             emp=em.createNamedQuery("IraPkiEmpInfoAll.findByEmpNo",IraPkiEmpInfoAll.class).setParameter("empNo",empNo).getSingleResult();
             rm=em.createNamedQuery("IraRoleMaster.findByRoleId", IraRoleMaster.class).setParameter("roleId", id).getSingleResult();
               IraUserRoles iur=new IraUserRoles();
           
               
              Object obj=em.find(IraUserRoles.class,emp.getEmpNo());
             
              if(obj!=null)
              {
           IraUserRoles IUR = (IraUserRoles)obj;
              IraPkiEmpInfoAll temp=IUR.getIraPkiEmpInfoAll();
              temp.removeChild();
                em.remove(IUR);
             
                em.getTransaction().commit();             
          
              }
              else
              {
                  
               IraUserRoles IUR=new IraUserRoles();

               IUR.setIraPkiEmpInfoAll(emp);

               IUR.setRoleId(rm);
               IraUserAuth temp=new IraUserAuth(emp.getEmpNo(),"password",'Y');
               IUR.setIraUserAuth(temp);

                IUR.setEmpNo(empNo);

               em.persist(IUR);

               em.flush();
               em.clear();
               em.getTransaction().commit();
              
              }
                 
            
            
                 
             
         }
         public List<IPE> getUsers(int id)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
              
             List <IPE> users=new ArrayList<IPE>();
             IraRoleMaster rm=em.createNamedQuery("IraRoleMaster.findByRoleId", IraRoleMaster.class).setParameter("roleId", id).getSingleResult();
              List <IraUserRoles> IUR=em.createNamedQuery("IraUserRoles.findByRoleId",IraUserRoles.class).setParameter("roleId", rm).getResultList();
              for(IraUserRoles i:IUR)
              {
                  IraPkiEmpInfoAll temp=i.getIraPkiEmpInfoAll();
              users.add(new IPE(temp.getEmpNo(),temp.getDesignation(), temp.getDivShortName(),temp.getGroupShortName(), temp.getName(), rm.getRoleName(), id));
         
                      }
              return users;
         }
         public List<IraBuildingList> createBl(char y)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             return em.createNamedQuery("IraBuildingList.findByIsActive",IraBuildingList.class).setParameter("isActive", y).getResultList();
         }
        public int getMaxReportId()
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             
            Integer rId =(Integer)em.createQuery("select max(i.reportId) from IraIncidentReportDetails i").getSingleResult();
                 if(rId==null)
                     rId=0;
                 return rId+1;
        }
          public short getMaxSrNo()
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            try 
            {short srNo =(short)em.createQuery("select max(i.srNo) from IraPersonsInvolved i").getSingleResult();
               
                 return (short)(srNo+1);
            }
            catch(Exception e)
            {
                return (short)1;
            }
        }
            public int getMaxQues()
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            Integer ques =(Integer)em.createQuery("select max(i.srNo) from IncidentReportQuestionnaire i").getSingleResult();
                 if(ques==null)
                     ques=0;
                 return ques+1;
        }
          
          
           public int getMaxSrNoDoc()
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            Integer srNo =(Integer)em.createQuery("select max(i.srNo) from IraDocumentsAttached i").getSingleResult();
                 if(srNo==null)
                     srNo=0;
                 return srNo+1;
        }
          public IraPkiEmpInfoAll getIraPki(int empNo)
          {
               if(!em.getTransaction().isActive())
            em.getTransaction().begin();
              return em.createNamedQuery("IraPkiEmpInfoAll.findByEmpNo",IraPkiEmpInfoAll.class).setParameter("empNo", empNo).getSingleResult();
          }
          
          public List <IraIncidentQuestionRelation> getQuestions(int id)
          {
               if(!em.getTransaction().isActive())
            em.getTransaction().begin();
               
              return em.createNamedQuery("IraIncidentQuestionRelation.findByImId",IraIncidentQuestionRelation.class).setParameter("incidentId", getIm(id)).getResultList();
          }
          public int commitIrd(IraIncidentReportDetails ird)
          {
               if(!em.getTransaction().isActive())
            em.getTransaction().begin();
              
              if(ird.getReportId()!=getMaxReportId())
            {
                ird.setReportId(getMaxReportId());
                
               
              } 
               em.persist(ird);
                   em.flush();
                   em.clear();
                   em.getTransaction().commit();
                  
              return ird.getReportId();
          }

             

    public void commitIRD(int reportId,IraIncidentReportDetails ird,List <IncidentReportQuestionnaire> irq,List <IraPersonsInvolved> ipi,List <IraDocumentsAttached> ida,String mode)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
     
            boolean change=false;
            if(!mode.equals("Edit"))
                    {
                        ird=getIRD(reportId);
             for(IncidentReportQuestionnaire i :irq)
                {
                    System.out.println(i.getSrNo());
              
                        i.setReportId(ird);
                    i.setSrNo(getMaxQues());
                   em.persist(i);
                   em.flush();
                   em.clear();
                }
              for(IraDocumentsAttached i :ida)
                {
                   
                        i.setReportId(ird);
                     i.setSrNo(getMaxSrNoDoc());
                    em.persist(i);
                   em.flush();
                   em.clear();
                }
                for(IraPersonsInvolved i :ipi)
                {
                 
                        i.setReportId(ird);
                     i.setSrNo(getMaxSrNo());
                    em.persist(i);
                   em.flush();
                   em.clear();
                }
                em.getTransaction().commit();            
                System.out.println("reoreoeroreoeroreoreo");
             
            
                    }
                   else
                   {
           
                       IraIncidentReportDetails irD=(IraIncidentReportDetails)em.find(IraIncidentReportDetails.class,ird.getReportId());
             
                       List<IncidentReportQuestionnaire> irQ=em.createNamedQuery("IncidentReportQuestionnaire.findByReportId", IncidentReportQuestionnaire.class).setParameter("reportId", irD).getResultList();
                 
                       List<IraDocumentsAttached> idA=em.createNamedQuery("IraDocumentsAttached.findByReportId", IraDocumentsAttached.class).setParameter("reportId", irD).getResultList();
                       List<IraPersonsInvolved> ipI=em.createNamedQuery("IraPersonsInvolved.findByReportId", IraPersonsInvolved.class).setParameter("reportId", irD).getResultList();
                   irD=ird;
                       em.flush();
                       em.clear();
                    List<Integer> irQc=new ArrayList<>();
                    List<Short> ipIc=new ArrayList<>();
                    List<Integer> idAc=new ArrayList<>();
                    boolean match=false;
                    for(IncidentReportQuestionnaire i :irq)
                    {
                        match=false;
                        i.setReportId(ird);
                        for(IncidentReportQuestionnaire j:irQ)
                        {
                            if(j.getSrNo()==i.getSrNo())
                            {
                                IncidentReportQuestionnaire k=em.find(IncidentReportQuestionnaire.class, j.getSrNo());
                                k=i;
                                em.flush();
                                em.clear();
                                irQc.add(i.getSrNo());
                                match=true;
                            }
                        }
                        if(!match)
                        {
                            i.setSrNo(getMaxQues());
                            em.persist(i);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                    if(irQc.size()!=irQ.size())
                    {
                        
                           for(IncidentReportQuestionnaire i :irQ)
                    {
                        match=false;
                        for(int j:irQc)
                        {
                            if(j==i.getSrNo())
                            {   
                                match=true;
                            }
                        }
                        if(!match)
                        {
                            
                            IncidentReportQuestionnaire k=em.find(IncidentReportQuestionnaire.class, i.getSrNo());
                            em.remove(k);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                        
                    }
                    for(IraPersonsInvolved i :ipi)
                    {
                        match=false;
                        i.setReportId(ird);
                        for(IraPersonsInvolved j:ipI)
                        {
                            if(j.getSrNo()==i.getSrNo())
                            {
                                IraPersonsInvolved k=em.find(IraPersonsInvolved.class, j.getSrNo());
                                k=i;
                                em.flush();
                                em.clear();
                                ipIc.add(i.getSrNo());
                                match=true;
                            }
                        }
                        if(!match)
                        {
                             i.setSrNo(getMaxSrNo());
                            em.persist(i);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                    if(ipIc.size()!=ipI.size())
                    {
                        
                           for(IraPersonsInvolved i :ipI)
                    {
                        match=false;
                        for(int j:ipIc)
                        {
                            if(j==i.getSrNo())
                            {   
                                match=true;
                            }
                        }
                        if(!match)
                        {
                            IraPersonsInvolved k=em.find(IraPersonsInvolved.class, i.getSrNo());
                            em.remove(k);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                        
                    }
                    
                    for(IraDocumentsAttached i :ida)
                    {
                        match=false;
                        i.setReportId(ird);
                        for(IraDocumentsAttached j:idA)
                        {
                            if(j.getSrNo()==i.getSrNo())
                            {
                                IraDocumentsAttached k=em.find(IraDocumentsAttached.class, j.getSrNo());
                                k=i;
                                em.flush();
                                em.clear();
                                idAc.add(i.getSrNo());
                                match=true;
                            }
                        }
                        if(!match)
                        {
                            i.setSrNo(getMaxSrNoDoc());
                            em.persist(i);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                    if(idAc.size()!=idA.size())
                    {
                        
                           for(IraDocumentsAttached i :idA)
                    {
                        match=false;
                        for(int j:idAc)
                        {
                            if(j==i.getSrNo())
                            {   
                                match=true;
                            }
                        }
                        if(!match)
                        {
                            IraDocumentsAttached k=em.find(IraDocumentsAttached.class, i.getSrNo());
                            em.remove(k);
                            em.flush();
                            em.clear();
                        }
                                
                    }
                        
                    }
                    
                       
                       System.out.println("Victory");
                    
                       em.getTransaction().commit();             
                    }
           
        
        }
   
    
         
      public IraQuestionMaster getQmDesc(String disc)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          return em.createNamedQuery("IraQuestionMaster.findByQuestionDescription",IraQuestionMaster.class).setParameter("questionDescription", disc).getSingleResult();
                 
      }
      public IraRoleMaster getRm(int id)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          return em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", id).getSingleResult();
      }
      public List<IraIncidentReportDetails> getIrdList(int roleId,int empNo)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          List<IraIncidentReportDetails> ird=new ArrayList<IraIncidentReportDetails>();
          
            try
             {
                 List<IraIncidentReportDetails> irdi=new ArrayList<IraIncidentReportDetails>();
                 IraPkiEmpInfoAll reportMarkedTo=getIraPki(empNo);
                
                 IraRoleMaster reportTo=getRm(roleId);
        
                 irdi=em.createNamedQuery("IraIncidentReportDetails.findByMarkedTo", IraIncidentReportDetails.class).setParameter("reportTo", reportTo).getResultList();
                 for(IraIncidentReportDetails i:irdi)
                 {
                     if(i.getReportMarkedTo()==null)
                        ird.add(i);
                     else if(i.getReportMarkedTo().equals(reportMarkedTo))
                         ird.add(i);
                 }
                 
                
                 
                     return ird;
             }
             catch(Exception e) 
             {
            
                    return ird; 
             }
          
          
      
      }
      public boolean check(int reportId,int empNo,int roleId)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          
             try
             {
                 IraIncidentReportDetails i;
                 IraPkiEmpInfoAll reportMarkedTo=getIraPki(empNo);
                
                 IraRoleMaster reportTo=getRm(roleId);
        
                 i=em.createNamedQuery("IraIncidentReportDetails.findByReportId", IraIncidentReportDetails.class).setParameter("reportId", reportId).getSingleResult();
                
                     if(i.getReportTo().equals(reportTo))
                     {
                      if(i.getReportMarkedTo()==null)
                             
                       return true;
                      else
                          if(i.getReportMarkedTo().equals(reportMarkedTo))
                              return true;
                 
                     }
                    
                         
                 
                
                 
                     return false;
             }
             catch(Exception e) 
             {
            
                    return false;
             }
      }
      public IraIncidentReportDetails getIRD(int reportId)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
         return em.createNamedQuery("IraIncidentReportDetails.findByReportId", IraIncidentReportDetails.class).setParameter("reportId", reportId).getSingleResult();
      }
      
        public IraPkiCertificates getCert(int empNo)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
         return em.createNamedQuery("IraPkiCertificates.findByEmpNo", IraPkiCertificates.class).setParameter("empNo", ""+empNo).getSingleResult();
      }
      
      public List<IncidentReportQuestionnaire> getIRQ(IraIncidentReportDetails ird)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          List<IncidentReportQuestionnaire> irq=new ArrayList<IncidentReportQuestionnaire>();
          
            try
             {
                
        
                 irq=em.createNamedQuery("IncidentReportQuestionnaire.findByReportId", IncidentReportQuestionnaire.class).setParameter("reportId", ird).getResultList();
              
                 
          
                 
                     return irq;
             }
             catch(Exception e) 
             {
            
                    return irq; 
             }
          
          
      
      }
       public List<IraPersonsInvolved> getIPI(IraIncidentReportDetails ird)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          List<IraPersonsInvolved> ipi=new ArrayList<IraPersonsInvolved>();
          
            try
             {
                
        
                 ipi=em.createNamedQuery("IraPersonsInvolved.findByReportId", IraPersonsInvolved.class).setParameter("reportId", ird).getResultList();
              
                 
          
                 
                     return ipi;
             }
             catch(Exception e) 
             {
            
                    return ipi; 
             }
          
          
      
      }
        public List<IraDocumentsAttached> getIDA(IraIncidentReportDetails ird)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          List<IraDocumentsAttached> ida=new ArrayList<IraDocumentsAttached>();
          
         
                
        
                 ida=em.createNamedQuery("IraDocumentsAttached.findByReportId", IraDocumentsAttached.class).setParameter("reportId", ird).getResultList();
              
                 
          
                 
                     return ida;
            
          
          
      
      }
         public List<IraReportAuthoritySigns> getAuthSign(IraIncidentReportDetails ird)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
          List<IraReportAuthoritySigns> authSign=new ArrayList<IraReportAuthoritySigns>();
          
            try
             {
                
        
                 authSign=em.createNamedQuery("IraReportAuthoritySigns.findByReportId", IraReportAuthoritySigns.class).setParameter("reportId", ird).getResultList();
                 
              
                 
          
                 
                     return authSign;
             }
             catch(Exception e) 
             {
            
                    return authSign; 
             }
          
          
      
      }
         public void setCert(String cert)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             IraPkiCertificates i=em.find(IraPkiCertificates.class,""+21442);
             i.setCertificate(cert);
             em.flush();
             em.clear();
             em.getTransaction().commit();             
             
         }
         public String getRoleName(int id)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             return em.createNamedQuery("IraRoleMaster.findByRoleId", IraRoleMaster.class).setParameter("roleId", id).getSingleResult().getRoleName();
         }
         public List<IraIncidentReportWorkFlow> getWorkFlow(String currentStatus)
         {
              if(!em.getTransaction().isActive())
            em.getTransaction().begin();
             System.out.println(currentStatus);
             return em.createNamedQuery("IraIncidentReportWorkFlow.findByCurrentStatus",IraIncidentReportWorkFlow.class).setParameter("currentStatus", currentStatus).getResultList();
         }
       public int getMaxAuthSrNo()
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            Integer rId =(Integer)em.createQuery("select max(i.authSrNo) from IraReportAuthoritySigns i").getSingleResult();
                 if(rId==null)
                     rId=0;
                 return rId+1;
        }
       public IraPkiEmpInfoAll getReportMarkedTO(int roleId,IraIncidentReportDetails reportId)
       {
            if(!em.getTransaction().isActive())
            em.getTransaction().begin();
           IraRoleMaster i=em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", roleId).getSingleResult();
           IraRoleMaster j=em.createNamedQuery("IraRoleMaster.findByRoleWeight",IraRoleMaster.class).setParameter("roleWeight", i.getRoleWeight()-1).getSingleResult();
           System.out.println(j.getRoleId());
           return em.createNamedQuery("IraReportAuthoritySigns.findByRoleIdAndReportId", IraReportAuthoritySigns.class).setParameter("reportId", reportId.getReportId()).setParameter("roleId", j.getRoleId()).getSingleResult().getAuthEmpNo();
       }
       
        public IraRoleMaster getReportTO(int roleId)
       {
            if(!em.getTransaction().isActive())
            em.getTransaction().begin();
           try
           {
           return em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", roleId).getSingleResult();
                       }
           catch(Exception e)
           {
               return null;
           }
       }
       public IraRoleMaster getRoleByName(String role)
       {
            if(!em.getTransaction().isActive())
            em.getTransaction().begin();
           List<IraRoleMaster> rm=createRm('Y');
           for(IraRoleMaster i:rm)
           {
               if(i.getRoleName().toUpperCase().equals(role))
                   return i;
           }
          
           return null;
       }
       
       public boolean commitSign(IraIncidentReportDetails incidentReport,IraReportAuthoritySigns iras,int roleId)
       {
           
         
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        em.getEntityManagerFactory().getCache().evictAll();
          IraIncidentReportDetails k=em.find(IraIncidentReportDetails.class, incidentReport.getReportId());
          System.out.println("helloasdababe11111");
           System.out.println("helloasdababe22222");
       try{
           if(k.getReportTo().getRoleId()==roleId+1)
           {
           k=incidentReport;
           em.flush();
           em.clear();
           iras.setReportId(incidentReport.getReportId());
           if(iras.getAuthSrNo()!=getMaxAuthSrNo())
               iras.setAuthSrNo(getMaxAuthSrNo());
           em.persist(iras);
           em.flush();
           em.clear();
              System.out.println("helloasdababe");
         
           em.getTransaction().commit();  
           return true;
        }
           return false;
       }
        catch(Exception e)
        {
            return false;
        }
           
       
    
       }
       public boolean getQforP(IraQuestionMaster q,int incidentId)
       {
            if(!em.getTransaction().isActive())
            em.getTransaction().begin();
           if( em.createNamedQuery("IraIncidentQuestionRelation.findByImIdQmId", IraIncidentQuestionRelation.class).setParameter("questionId", q).setParameter("incidentId",getIm(incidentId) ).getSingleResult().getIsMandatory()=='Y')
               return true;
           else
           return false;
       }
        public List<IraIncidentReportDetails> getIrdSignedList(int empNo)
      {
           if(!em.getTransaction().isActive())
            em.getTransaction().begin();
                   em.getEntityManagerFactory().getCache().evictAll();
          List<IraIncidentReportDetails> ird=new ArrayList<IraIncidentReportDetails>();
          
     
      
                 IraPkiEmpInfoAll reportMarkedTo=getIraPki(empNo);
                 List<IraReportAuthoritySigns> sign=em.createNamedQuery("IraReportAuthoritySigns.findByAuthEmpNo", IraReportAuthoritySigns.class).setParameter("authEmpNo", reportMarkedTo).getResultList();
           System.out.println("asddddddddddddddddddddddddddddddddddddddddddddd");
        
                System.out.println(sign.size());
                 for(IraReportAuthoritySigns i:sign)
                 {
                        ird.add(em.createNamedQuery("IraIncidentReportDetails.findByReportId", IraIncidentReportDetails.class).setParameter("reportId", i.getReportId()).getSingleResult());
                 }
                 
                
                 System.out.println(ird.size());
                     return ird;
       
          
          
      
      }
        
        public boolean checkCreation(int roleid)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();em.getEntityManagerFactory().getCache().evictAll();
            IraRoleMaster i =em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", roleid).getSingleResult();
            if(i.getIsReportCreationAllowed()=='Y')
                return true;
            else return false;
            
        }
        
        public List<IraIncidentReportDetails> getIrdForSearch(String q,int roleid)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            List<IraIncidentReportDetails> ird=new ArrayList<IraIncidentReportDetails>();
           
                List<IraIncidentReportDetails> irdi=em.createNamedQuery("IraIncidentReportDetails.findAll", IraIncidentReportDetails.class).getResultList();
                
                
              
                 for(IraIncidentReportDetails i :irdi)
                 {
                     System.out.println("yello");
                     if(compareRole(roleid, returnRoleId(i.getReportedBy().getEmpNo())))
                     {
                         System.out.println("yello");
                     if(q.equals("All"))
                         ird.add(i);
                     
                     else if(i.getCurrentStatus().startsWith(q))
                            ird.add(i);
                     }
                     }
                
                return ird;
            
         
        }
        
        public boolean compareRole(int roleId,int roleId2)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            IraRoleMaster i=em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", roleId).getSingleResult();
             IraRoleMaster j=em.createNamedQuery("IraRoleMaster.findByRoleId",IraRoleMaster.class).setParameter("roleId", roleId2).getSingleResult();
             if(i.getRoleWeight()>=j.getRoleWeight())
                 return true;
             else
                 return false;
                        
        
        }
        public int returnRoleId(int emp)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            IraUserRoles i=em.createNamedQuery("IraUserRoles.findByEmpNo",IraUserRoles.class).setParameter("empNo", emp).getSingleResult();
            return i.getRoleId().getRoleId();
        }
        public List<Integer> getAuthByNo(IraIncidentReportDetails ird)
        {
             if(!em.getTransaction().isActive())
            em.getTransaction().begin();
            List<Integer> authNo=new ArrayList<>();
            try{
                
            
            List<IraReportAuthoritySigns> auth=em.createNamedQuery("IraReportAuthoritySigns.findByReportId",IraReportAuthoritySigns.class).setParameter("reportId", ird).getResultList();
            for(IraReportAuthoritySigns i: auth)
                authNo.add(i.getAuthSrNo());
            return authNo;
            
            }catch(Exception e)
            {
                return authNo;
            }
        
        }
      
}
