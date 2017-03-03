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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DIVYA
 */
@ManagedBean
@ViewScoped
public class ViewSignedReport implements Serializable{
    
    private IraIncidentReportDetails incidentReport;
    private List<IraPersonsInvolved> ipi;
    private List<IraDocumentsAttached> docs;
    private List<IraReportAuthoritySigns> authSign; 
    private List<IncidentReportQuestionnaire> ques;
    private String XML;
    private String sign;
        private List<Xport> export;

    public List<Xport> getExport() {
        return export;
    }

    public void setExport(List<Xport> export) {
        this.export = export;
    }






    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


   


    public void setXML(String XML) {
        this.XML = XML;
    }


   
    
    @ManagedProperty("#{login}")
    LoginController login;

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
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
        export=new ArrayList<>();
        render=false;
        
        
        
    }
    
       public void init2() throws IOException
    {
       
            render=true;
            xml=false;
            incidentReport=query.getIRD(reportId);
            ques=query.getIRQ(incidentReport);
            ipi=query.getIPI(incidentReport);
            System.out.println("hello");
            docs=query.getIDA(incidentReport);
            authSign=query.getAuthSign(incidentReport);
            Xport i=new Xport();
            i.setOne("Incident Name:");
            i.setTwo(incidentReport.getIncidentName());
            i.setThree("Incident Id:");
            i.setFour(""+incidentReport.getIncidentId().getIncidentId());
            export.add(Xport.newObj(i));
            i.setOne("Building:");
            i.setTwo(incidentReport.getBuildingShortName());
            i.setThree("Building Code:");
            i.setFour(""+incidentReport.getBuildingCode().getBuildingCode());
            export.add(Xport.newObj(i));
             i.setOne("Location Description:");
            i.setTwo(incidentReport.getBuildingShortName());
            i.setThree("Date Of Occurence:");
            i.setFour(""+incidentReport.getDateTimeOfOccurrence().toLocaleString());
            export.add(Xport.newObj(i));
             i.setOne("Current Status:");
            i.setTwo(incidentReport.getCurrentStatus());
            i.setThree("Is new Version Required?:");
            i.setFour(""+incidentReport.getIsNewVersionRequired());
            export.add(Xport.newObj(i));
             i.setOne("Old Report Id:");
            i.setTwo(""+incidentReport.getOldReportId());
            i.setThree("Reported On:");
            i.setFour(""+incidentReport.getReportedOn().toLocaleString());
            export.add(Xport.newObj(i));
             i.setOne("Incident Description: ");
            i.setTwo(incidentReport.getIncidentDetails());
            i.setThree("");
            i.setFour("");
            export.add(Xport.newObj(i));
            
       
            
        
           
            
     
        
        
        
        
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
  
    
}
