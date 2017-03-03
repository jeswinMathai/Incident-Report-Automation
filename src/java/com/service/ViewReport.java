/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;


import com.controller.LoginController;
import com.domain.Operations;
import com.domain.Xport;
import com.entity.IncidentReportQuestionnaire;
import com.entity.IraDocumentsAttached;

import com.entity.IraIncidentReportDetails;
import com.entity.IraIncidentReportWorkFlow;
import com.entity.IraPersonsInvolved;
import com.entity.IraPkiCertificates;
import com.entity.IraPkiEmpInfoAll;
import com.entity.IraReportAuthoritySigns;
import com.entity.IraRoleMaster;
import com.query.DataQuery;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DIVYA
 */
@ManagedBean
@ViewScoped
public class ViewReport implements Serializable{
    
    private IraIncidentReportDetails incidentReport;
    private List<IraPersonsInvolved> ipi;
    private List<IraDocumentsAttached> docs;
    private List<IraReportAuthoritySigns> authSign; 
    private List<IncidentReportQuestionnaire> ques;
    private IraPkiCertificates cert;
    private String op;
    private String remark;
    private IraPkiEmpInfoAll pki;
    private String XML;
    private String Role;
    private String sign;
    private List<Operations> operations;
  

    
    List<IraIncidentReportWorkFlow> workflow ;


    private int noofop;

    public int getNoofop() {
        return noofop;
    }

    public void setNoofop(int noofop) {
        this.noofop = noofop;
    }
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

   

    public void setRole(String Role) {
        this.Role = Role;
    }
    

    public void setXML(String XML) {
        this.XML = XML;
    }

    public IraPkiEmpInfoAll getPki() {
        return pki;
    }

    public void setPki(IraPkiEmpInfoAll pki) {
        this.pki = pki;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @ManagedProperty("#{login}")
    LoginController login;

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public IraPkiCertificates getCert() {
        return cert;
    }

    public void setCert(IraPkiCertificates cert) {
        this.cert = cert;
    }
    private int reportId;
    private boolean render;

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isXml() {
        return xml;
    }

    public void setXml(boolean xml) {
        this.xml = xml;
    }
    private boolean xml;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public DataQuery getQuery() {
        return query;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }
    
    @ManagedProperty("#{dataQuery}")
    DataQuery query;

    public IraIncidentReportDetails getIncidentReport() {
        return incidentReport;
    }

    public void setIncidentReport(IraIncidentReportDetails incidentReport) {
        this.incidentReport = incidentReport;
    }

    public List<IraPersonsInvolved> getIpi() {
        return ipi;
    }

    public void setIpi(List<IraPersonsInvolved> ipi) {
        this.ipi = ipi;
    }

    public List<IraDocumentsAttached> getDocs() {
        return docs;
    }

    public void setDocs(List<IraDocumentsAttached> docs) {
        this.docs = docs;
    }

    public List<IraReportAuthoritySigns> getAuthSign() {
        return authSign;
    }

    public void setAuthSign(List<IraReportAuthoritySigns> authSign) {
        this.authSign = authSign;
    }

    public List<IncidentReportQuestionnaire> getQues() {
        return ques;
    }

    public void setQues(List<IncidentReportQuestionnaire> ques) {
        this.ques = ques;
    }
    
    @PostConstruct 
    public void init()
    {
        incidentReport=new IraIncidentReportDetails();
        ipi=new ArrayList<>();
        docs=new ArrayList();
        authSign=new ArrayList();
        ques=new ArrayList();
        render=false;
        
        
        
    }
    
       public void init2() throws IOException
    {
        if(query.check(reportId,login.getEmpNO(),login.getRoleId()))
        {
        
            render=true;
            xml=false;
            incidentReport=query.getIRD(reportId);
            ques=query.getIRQ(incidentReport);
            ipi=query.getIPI(incidentReport);
            System.out.println("hello");
            docs=query.getIDA(incidentReport);
            authSign=query.getAuthSign(incidentReport);
            cert=query.getCert(login.getEmpNO());
            pki=query.getIraPki(login.getEmpNO());
            System.out.println(cert.getCertificate().contains("\n"));
            System.out.println(ques.size());
            System.out.println(ipi.size());
            System.out.println(docs.size());
            noofop=0;
            Role=login.getPrivilege().toUpperCase();
            workflow=query.getWorkFlow(incidentReport.getCurrentStatus()); 
            operations=new ArrayList<Operations>();
          for(IraIncidentReportWorkFlow i:workflow)
          {
              if(i.getIsNewVersionReqd()==incidentReport.getIsNewVersionRequired())
              {
               if(i.getAction().equals("Revert")&&incidentReport.getReportedBy().equals(pki))
               {
                        operations.add(new Operations(i.getAction(), false));
               }
               else
               {
                   operations.add(new Operations(i.getAction(), true));
                 
               }
               noofop++;
              }
              else if(!i.getAction().equals("Revert"))
              {
                  operations.add(new Operations(i.getAction(), false));
                     noofop++;
              }   
          }
            
     
        }
        
        
        
    }
       
      public String getDate()
      {
          return new SimpleDateFormat("dd/MM/yyyy").format(new Date()); 
      }
       public String getXML()
       {
           
           XML="<XML_VERSION>Incident Report xml version 1</XML_VERSION>\n" +
                        "<GOVERNMENT>Government of India</GOVERNMENT>\n" +
                        "<ORGANIZATION>Bhabha Atomic Research Centre, Mumbai</ORGANIZATION>\n" +
                        "<HEAD>Incident Report Details</HEAD>\n" +
                        "<REPORT_ID>"+reportId+"</REPORT_ID>\n" +
                        "<INCIDENT_NAME>"+incidentReport.getIncidentName()+"</INCIDENT_NAME>\n" +
                         "<INCIDENT_ID>"+incidentReport.getIncidentId().getIncidentId()+"</INCIDENT_ID>\n" +
                        "<LOCATION_Of_OCCURENCE>\n" +
                        "<BUILDING_SHORT_NAME>"+incidentReport.getBuildingShortName()+"</BUILDING_SHORT_NAME>\n" +
                        "<BUILDING_CODE>"+incidentReport.getBuildingCode().getBuildingCode()+"</BUILDING_CODE>\n" +
                        "<LOCATION_DESCRIPTION>"+incidentReport.getLocationDescription()+"</LOCATION_DESCRIPTION>\n" +
                        "</LOCATION_OF_OCCURENCE>\n" +
                   "<CURRENT_STATUS>"+incidentReport.getCurrentStatus()+"</CURRENT_STATUS>\n"+
                   "<DATE_TIME_OF_OCCURENCE>"+incidentReport.getDateTimeOfOccurrence()+"</DATE_TIME_Of_OCCURENCE>\n"+
                   "<EDIT_REPORT>"+incidentReport.getEditReport()+"</EDIT_REPORT>\n"+  
                    "<FUTURE_PROTECTION>"+incidentReport.getFutureProtection()+"</FUTURE_PROTECTION>\n"+
                    "<Is_NEW_VERSION_REQUIRED>"+incidentReport.getIsNewVersionRequired()+"</IS_NEW_VERSION_REQUIRED>\n"+
                    "<INCIDENT_DETAILS>"+incidentReport.getIncidentDetails()+"</INCIDENT_DETAILS>\n" +
                    "<OLD_REPORT_ID>"+incidentReport.getOldReportId()+"</OLD_REPORT_ID>\n" +
                    "<REPORTED_ON>"+incidentReport.getReportedOn()+"</REPORT_ON>\n" +
                    "<REPORT_MARKED_TO>"+incidentReport.getReportMarkedTo()+"</REPORT_MARKED_TO>\n" +
                    "<REPORT_MARKED_BY>"+incidentReport.getReportedBy()+"</REPORT_MARKED_BY>\n" +
                    "<REPORT_TO>"+incidentReport.getReportTo()+"</REPORT__TO>\n"+
                    "<INCIDENT_RELATED_QUESTION>\n";
 
           for(IncidentReportQuestionnaire i:ques)
           {
               XML+=
                       "<SR_NO>"+i.getSrNo()+"</SR_NO"+
                       "<QUESTION_DESCRIPTION>"+i.getQuestionDescription()+"</QUESTION_DESCRIPTION>\n"+
                       "<ANSWER>"+i.getAnswer()+"</ANSWER>\n"+
                       "<QUESTION_ID>"+i.getQuestionId().getQuestionId()+"<QUESTION_ID>\n";
           }
          XML+="<INCIDENT_RELATED_QUESTION>\n";
          if(!ipi.isEmpty())
          {
              XML+="<PERSONS_INVOLVED>\n";
              
              for(IraPersonsInvolved i:ipi)
              {
                  XML+=
                          "<SR_NO>"+i.getSrNo()+"</SR_NO>\n"+
                          "<NAME>"+i.getName()+"</NAME>\n"+
                          "<HAVING_BARC_CARD>"+i.getHavingBarcCard()+"</HAVING_BARC_CARD>\n"+
                          "<CARD_TYPE>"+i.getCardType()+"</CARD_TYPE>\n"+
                          "<ID>"+i.getId()+"</ID>\n"+
                          "<DESIGNATION>"+i.getDesignation()+"</DESIGNATION>\n"+
                          "<DIVISION_COMPANY>"+i.getDivisionCompany()+"</DIVISION_COMPANY>\n"+
                          "<ADDRESS>"+i.getAddress()+"</ADDRESS>\n"+
                          "<STREET>"+i.getStreet()+"</STREET>\n"+
                          "<CITY>"+i.getCity()+"</CITY>\n"+
                          "<CONTACT_NO>"+i.getContactNo()+"</CONTACT_NO>\n"+
                          "<STATE>"+i.getState()+"</STATE>\n"+
                          "<COUNTRY>"+i.getCountry()+"</COUNTRY>\n"+
                          "<HEALTH_STATUS>"+i.getHealthStatus()+"</HEALTH_STATUS>\n"+
                          "<IS_ACCUSED>"+i.getIsAccused()+"</IS_ACCUSED>\n"+
                          "<IS_VICTIM>"+i.getIsVictim()+"</IS_VICTIM>\n"+
                          "<IS_WITNESS>"+i.getIsWitness()+"</IS_WITNESS>\n"; 
              }
              
              XML+="</PERSONS_INVOLVED>\n";              
          }
           if(!docs.isEmpty())
          {
              XML+="<DOCUMENTS_ATTACHED>\n";
              
              for(IraDocumentsAttached i:docs)
              {
                  XML+=
                       "<SR_NO>"+i.getSrNo()+"</SR_NO"+
                       "<DOC_FILE_NAME>"+i.getDocFileName()+"</DOC_FILE_NAME>\n"+
                       "<UPLOADED_ON>"+i.getUploadedOn()+"</UPLOADED_ON>\n"+
                       "<UPLOADED_BY>"+i.getUploadedBy()+"</UPLOADED_BY>\n";   
              }
              
              XML+="</DOCUMENTS_ATTACHED>\n";              
          }
                   
                        
           
             if(!authSign.isEmpty())
          {
              
             
              for(IraReportAuthoritySigns i:authSign)
              {
                  XML+="<"+query.getRoleName(i.getRoleId()).toUpperCase()+">\n"+
                       "<EMP_NO>"+i.getAuthEmpNo().getEmpNo()+"</EMP_NO>\n"+
                       "<ACTION>"+i.getAction()+"</ACTION>\n"+
                       "<REMARK>"+i.getRemarks()+"</REMARK>\n"+
                       "<CERT_SNO>"+i.getAuthCertSrNo()+"</CERT_SNO>\n"+
                          "</"+query.getRoleName(i.getRoleId()).toUpperCase()+">\n";
              }
              
              
                          
          }
               
                       
                 
           return XML;
       }
       public void dummy()
       {
     
       }
       public String getRole()
       {
           return Role;
       }
       public void commit()
       {
             FacesContext context=FacesContext.getCurrentInstance();
              RequestContext.getCurrentInstance().update("growl");
//          if(!sign.startsWith("ERROR"))
//          {
           System.out.println("Visca la ");
           
           IraReportAuthoritySigns iras=new IraReportAuthoritySigns();
           iras.setAuthSrNo(query.getMaxAuthSrNo());
           iras.setAction(op);
           iras.setAuthCertSrNo(cert.getCertNo());
           iras.setAuthDesig(pki.getDesignation());
           iras.setAuthName(pki.getName());
           iras.setAuthSign(sign);
           iras.setRemarks(remark);
           iras.setRoleId(login.getRoleId());
           iras.setRoleName(login.getPrivilege());
           iras.setAuthEmpNo(pki);
        
           
         
           incidentReport.setEditReport('N');
           incidentReport.setReportMarkedTo(null);
               incidentReport.setCurrentStatus(setCurrentStatus(op, incidentReport.getIsNewVersionRequired()));
           incidentReport.setReportTo(setMarkedTo(op, incidentReport.getIsNewVersionRequired())); 
           if(op.equals("Revert"))
           {
               
               iras.setReportMarkedTo(query.getReportMarkedTO(login.getRoleId(), incidentReport));
               
               incidentReport.setReportMarkedTo(query.getReportMarkedTO(login.getRoleId(), incidentReport)); 
                incidentReport.setIsNewVersionRequired('Y');
           }
           System.out.println("hello 4");

    
           if(query.commitSign(incidentReport,iras,login.getRoleId()))
                        context.addMessage("null", new FacesMessage("Successful",  "Signing Succesfull"));
                        else
                        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Unable to process request, The report may have been forwarded by other authority , please try again sometime later"));
    
          //}
    //      else
          context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Signing Error",sign));
            context.addMessage("null", new FacesMessage("Redirecting..",  "You will be Redirected in few Seconds"));
          
      }
       String setCurrentStatus(String operation,char isNewVersionRequired)
       {
           for(IraIncidentReportWorkFlow i:workflow)
           {
               if(i.getAction().equals(operation)&&i.getIsNewVersionReqd()==isNewVersionRequired)
                   return i.getNewCurrentStatus();
           }
           return null;
       }
       IraRoleMaster setMarkedTo(String operation,char isNewVersionRequired)
       {
           if(!(operation.equals("Close")||operation.equals("Close And Rewrite")))
           {
           String currentStatus=setCurrentStatus(operation, isNewVersionRequired);
           String temp[]=currentStatus.split("Pending At ");
           String role=temp[temp.length-1];
           return query.getRoleByName(role);
           }
           return null;
       }
       
    
               
    
}
