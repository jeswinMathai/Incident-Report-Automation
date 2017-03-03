/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.controller.LoginController;
import com.domain.UploadFile;
import com.entity.IncidentReportQuestionnaire;
import com.entity.IraBuildingList;
import com.entity.IraDocumentsAttached;
import com.entity.IraIncidentMaster;
import com.entity.IraIncidentQuestionRelation;
import com.entity.IraIncidentReportDetails;
import com.entity.IraPersonsInvolved;
import com.entity.IraPkiEmpInfoAll;
import com.entity.IraQuestionMaster;
import com.entity.IraRoleMaster;
import com.query.DataQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author DIVYA
 */
@Named(value="fillReport")
@FlowScoped(value="createReport")
public class FillReport implements Serializable{
    
    private List<IraBuildingList> buildingList;
    private List<IraIncidentMaster> ims;
    private int incidentId;
    private int reportMarkedTo;
    private IraIncidentReportDetails incidentReports =new IraIncidentReportDetails();
    private List<IraPersonsInvolved> persons;
    private IraPersonsInvolved person;
    private int empNo;
    private String buildingCode;
    private IraDocumentsAttached doc;
    private List<IraDocumentsAttached> docs;
    private short maxPerson;
    private int maxDocument;
    private List<IraQuestionMaster> repeatedQm;
    private List<IncidentReportQuestionnaire> mandatory;
    private List<IncidentReportQuestionnaire> repeated;
    private int questionId;
    private int maxQno;
    private String roleName;


    private List<IraRoleMaster> rm;
    private int roleId;
    private String mode;
    private int reportId;
    private boolean rendermode;
    private List<IncidentReportQuestionnaire> previousQuestions;

    public List<IncidentReportQuestionnaire> getPreviousQuestions() {
        return previousQuestions;
    }

    public void setPreviousQuestions(List<IncidentReportQuestionnaire> previousQuestions) {
        this.previousQuestions = previousQuestions;
    }

    public boolean isRendermode() {
        return rendermode;
    }

    public void setRendermode(boolean rendermode) {
        this.rendermode = rendermode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
        public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
 @ManagedProperty("#{login}")
   LoginController login;

    public FillReport() {
          mode="Create";
        FacesContext context = FacesContext.getCurrentInstance();
    login = (LoginController) context.getApplication().evaluateExpressionGet(context, "#{login}", LoginController.class);
    String parameter1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");
        String parameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        IraIncidentReportDetails ird=new IraIncidentReportDetails();
    if(parameter1==null)
    {
       mode="Create";
       rendermode=false;
    }
    else if(parameter1.equals("Edit"))
    {
        mode="Edit";
        reportId=Integer.parseInt(parameter);
        rendermode=true;
    }   
    else if(parameter1.equals("Close"))
    {
        mode="Close";
        reportId=Integer.parseInt(parameter);
        rendermode=true;
    }   
       query=new DataQuery();
       file=new UploadFile();
     
            
            this.ims = query.createIm('Y');
             this.buildingList=query.createBl('Y');
  
   
             roleName=query.getReportTO(login.getRoleId()).getRoleName();
             incidentReports.setReportTo(query.getReportTO(login.getRoleId()));
   if(mode.equals("Create"))
   {
       incidentReports.setReportId(getMaxReportId());     
       incidentReports.setCurrentStatus("Pending At "+login.getPrivilege().toUpperCase());
       incidentReports.setIsNewVersionRequired('N');
       empNo=login.getEmpNO();
       IraPkiEmpInfoAll j=query.getIraPki(empNo);
       incidentReports.setReportedBy(j);
       incidentReports.setReportMarkedTo(j);
      
       persons=new ArrayList<>();
       docs=new ArrayList<>();
       
   }
   else
   {
       ird=query.getIRD(reportId);
       persons=new ArrayList((query.getIPI(ird)));
       docs=new ArrayList(query.getIDA(ird));
       incidentId=ird.getIncidentId().getIncidentId();
          previousQuestions=new ArrayList<>();
         previousQuestions=query.getIRQ(ird);
         System.out.println("Question size:"+previousQuestions.size());
        setQuestions();
   }
   if(mode.equals("Close"))
   {
       System.out.println("hellosadassadsad");
       incidentReports.setReportId(query.getMaxReportId());
       incidentReports.setOldReportId(reportId);
       incidentReports.setCurrentStatus("Pending At "+login.getPrivilege().toUpperCase());
       IraPkiEmpInfoAll j=query.getIraPki(login.getEmpNo());
       incidentReports.setReportedOn(ird.getReportedOn());
       incidentReports.setReportedBy(j);
       incidentReports.setReportMarkedTo(j);
       incidentReports.setIsNewVersionRequired('N');
       incidentReports.setBuildingCode(ird.getBuildingCode());
       incidentReports.setBuildingShortName(ird.getBuildingShortName());
       incidentReports.setDateTimeOfOccurrence(ird.getDateTimeOfOccurrence());
       incidentReports.setEditReport('Y');
       incidentReports.setIncidentDetails(ird.getIncidentDetails());
       incidentReports.setLocationDescription(ird.getLocationDescription());
       incidentReports.setIncidentId(ird.getIncidentId());
       incidentReports.setIncidentName(ird.getIncidentName());
       System.out.println("hellosadassadsad");
      
       
       List <IncidentReportQuestionnaire> IRQ=new ArrayList<>();
        System.out.println("hellosadassadsad12312312312");
        if(previousQuestions!=null)
        {
            System.out.println("hello fols");
        }
        else
            System.out.println("dsamn s");
               reportId=query.commitIrd(incidentReports);
               incidentReports.setReportId(reportId);
       for( IncidentReportQuestionnaire i:previousQuestions)
       {
         
     
           i.setReportId(incidentReports);
           IRQ.add(i);
       }
       for(IraPersonsInvolved i:persons)
       {
           i.setReportId(incidentReports);
       }
       for(IraDocumentsAttached i:docs)
       {
           i.setReportId(incidentReports);
       }
              for(IraPersonsInvolved i:persons)
       {
           i.setReportId(incidentReports);
       }
             
             System.out.println(docs.size());
             System.out.println(persons.size());
             System.out.println("hello");
              query.commitIRD(reportId,incidentReports, IRQ, persons, docs,"Create");
              mode="Edit";
   }
   
      maxPerson=query.getMaxSrNo();
       maxDocument=query.getMaxSrNoDoc();
       maxQno=query.getMaxQues();
       rm=query.createRm('Y');
        person=new IraPersonsInvolved();
        doc=new IraDocumentsAttached();
       System.out.println("hello1234321");
  
 

             
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<IraRoleMaster> getRm() {
        return rm;
    }

    public void setRm(List<IraRoleMaster> rm) {
        this.rm = rm;
    }
@ManagedProperty("#{uploadFile}")
UploadFile file;

    public UploadFile getFile() {
        return file;
    }

    public void setFile(UploadFile file) {
        this.file = file;
    }
    


 @ManagedProperty("#{dataQuery}")
 DataQuery query;

 
  public List<IraBuildingList> getBuildingList() {
        return buildingList;
       
    }

    public void setBuildingList(List<IraBuildingList> buildingList) {
        this.buildingList = buildingList;
    }

    public List<IraIncidentMaster> getIms() {
        return ims;
    }

    public void setIms(List<IraIncidentMaster> ims) {
        this.ims = ims;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public DataQuery getQuery() {
        return query;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }
 
    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }
    
    public List<IraPersonsInvolved> getPersons() {
        return persons;
    }

    public void setPersons(List<IraPersonsInvolved> persons) {
        this.persons = persons;
    }
    
    public IraPersonsInvolved getPerson() {
        return person;
    }

    public void setPerson(IraPersonsInvolved person) {
        this.person = person;
    }
    
        public IraIncidentReportDetails getIncidentReports() {
        return incidentReports;
    }

    public void setIncidentReports(IraIncidentReportDetails incidentReports) {
        this.incidentReports = incidentReports;
    }
     public int getReportMarkedTo() {
        return reportMarkedTo;
    }

    public void setReportMarkedTo(int reportMarkedTo) {
        this.reportMarkedTo = reportMarkedTo;
    }

    
        public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void addPerson(){
        
         person.setSrNo(maxPerson);
         person.setReportId(incidentReports);
         persons.add(person);
         person=new IraPersonsInvolved();
         maxPerson++;
        
    }
    
    public IraDocumentsAttached getDoc() {
        return doc;
    }

    public void setDoc(IraDocumentsAttached doc) {
        this.doc = doc;
    }

    public List<IraDocumentsAttached> getDocs() {
        return docs;
    }

    public void setDocs(List<IraDocumentsAttached> docs) {
        this.docs = docs;
    }
       public List<IncidentReportQuestionnaire> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<IncidentReportQuestionnaire> mandatory) {
        this.mandatory = mandatory;
    }

    public List<IncidentReportQuestionnaire> getRepeated() {
        return repeated;
    }

    public void setRepeated(List<IncidentReportQuestionnaire> repeated) {
        this.repeated = repeated;
    }

 

    public List<IraQuestionMaster> getRepeatedQm() {
        return repeatedQm;
    }

    public void setRepeatedQm(List<IraQuestionMaster> repeatedQm) {
        this.repeatedQm = repeatedQm;
    }
    
    public int getMaxReportId()
    { 
        return query.getMaxReportId();
      
    }


 
 
    public String next(int A)
    {
        String a="";
        if(A==1)
        {
                 setQuestions();
        IraBuildingList bl=getBuilding();
        IraIncidentMaster iM=getIncident();
        incidentReports.setIncidentId(iM);
        incidentReports.setIncidentName(iM.getIncidentName());
        incidentReports.setBuildingCode(bl);
        incidentReports.setBuildingShortName(bl.getBuildingShortName());
        a="A";
   
        }
        else if(A==2)
        {
            
           
        a="B";    
        }
        else if(A==3)
        {
            resetDoc();
            a="C";
        }
       
        return "createReport"+a;
    }

    
    public void resetDoc()
    {
        Date date=new Date();
        doc=new IraDocumentsAttached();
           doc.setUploadedBy(incidentReports.getReportedBy());
           doc.setUploadedOn(date);
           doc.setSrNo(maxDocument);
           doc.setReportId(incidentReports);
           file=new UploadFile();
        
    }
    public IraIncidentMaster getIncident()
    {
        for(IraIncidentMaster i:ims)
        {
            if(i.getIncidentId()==incidentId)
                return i;
        }
      
        return null;
    }
     public IraBuildingList getBuilding()
    {
        for(IraBuildingList i:buildingList)
        {
            if(i.getBuildingCode().equals(buildingCode))
                return i;
        }
      
        return null;
    }
     public void uploadfile()
     {
       
         doc.setDocument(file.getFiles());
         doc.setDocFileName(doc.getDocFileName());
         doc.setExt(file.getExt());
         System.out.println(file.getFiles());
         docs.add(doc);
         maxDocument++;
         resetDoc();
       
         
      
     }
     
     public void setQuestions()
     {
         mandatory=new ArrayList<>();
         repeated=new ArrayList<>();
         repeatedQm=new ArrayList<>();
       
         List<IraIncidentQuestionRelation> Questions=query.getQuestions(incidentId);
         
     
         for(IraIncidentQuestionRelation i:Questions)
         {
             if(i.getCanBeRepeated()=='Y')
             {
                 repeatedQm.add(i.getQuestionId());
                 System.out.println("hello says jeswin");
             System.out.println(i.getQuestionId().getQuestionId());
             }
              if(mode.equals("Create"))
              {
                 if(i.getIsMandatory()=='Y')
              {
          
                  IncidentReportQuestionnaire j=new IncidentReportQuestionnaire(maxQno,i.getQuestionId().getQuestionDescription(), "");
                  j.setQuestionId(i.getQuestionId());
                  mandatory.add(j);
             
                  maxQno++;
             
             
              }
              }
               
         }
        
    
     }
     public void removePerson(IraPersonsInvolved i)
     {
         persons.remove(i);
     }
          public void removeDoc(IraDocumentsAttached i)
     {
         docs.remove(i);
     }
               public void removeQues(IncidentReportQuestionnaire i)
     {
         previousQuestions.remove(i);
     }
                        public void removeQuesR(IncidentReportQuestionnaire i)
     {
         repeated.remove(i);
     }
     public void addRepeated()
     {
       
         if(questionId!=0)
         {
         IraQuestionMaster question=query.getQm(questionId);
         IncidentReportQuestionnaire j=new IncidentReportQuestionnaire(maxQno,question.getQuestionDescription(), "");
                  j.setQuestionId(question);
         repeated.add(j);
         maxQno++;
         }
     }
     
     public String commit()
     {
         
         System.out.println(incidentReports.getIsNewVersionRequired());
         incidentReports.setEditReport('Y');
    if(!mode.equals("Create"))
         mandatory=previousQuestions;
     
         List <IncidentReportQuestionnaire> IRQ=new ArrayList<>();
       for( IncidentReportQuestionnaire i:mandatory)
       {

           IRQ.add(i);
       }
        for(IncidentReportQuestionnaire i:repeated)
       {

           IRQ.add(i);
       }
        if(mode.equals("Create"))
            reportId=query.commitIrd(incidentReports);
        query.commitIRD(reportId,incidentReports, IRQ, persons, docs,mode);
        return "/ViewPendingReport";
   
     }
     public boolean check(IncidentReportQuestionnaire o)
     {
         int k=0;
         IraQuestionMaster i=o.getQuestionId();
        
         if(repeatedQm.contains(i))
         {
    
             if(query.getQforP(i,incidentId))
             {
                           
                 for(IncidentReportQuestionnaire j:previousQuestions)
                 {
                                
                     if(j.getQuestionId().equals(i))
                         k++;
                 }
                 if(k>1)
                     return true;
                  else
                     return false;
                 
             }
             else
                 return true;
         }
         return false;
     }
     public boolean checkFile()
     {
         if(file!=null)
         {
             return file.getFiles()!=null;
         }else
             return false;
     }
    
    
}
