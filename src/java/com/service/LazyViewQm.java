package com.service;


import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import com.domain.Qm;
 
@ManagedBean(name="LazyViewQm")
@ViewScoped
public class LazyViewQm implements Serializable {
     
    private LazyDataModel<Qm> lazyModel;
     
    private Qm selectedQm;
    private int emt;

   
    private List<Qm> selectedQms;
    private  List<Qm> list;
@ManagedProperty("#{qmService}")
QmService service;

    public void clear(int a)
    {
        int i=0,j=0;
        if(a==2)
            emt=0;
        if(selectedQms!=null)
        {
            for(Qm qm:selectedQms)
         {
            i++;
            
         }
        }
       
        if(i==1)
        {
            if(emt==1)
            {
            selectedQms=null;
            emt=0;
            j=1;
            }
        }
        if(j!=1)
        emt=i;
      
      
            
    }
    
  
     
    @PostConstruct
    public void init() {
        list=service.createQms();
 
        emt=0;
        lazyModel = new LazyQmDataModel(list);
    }
    public void message(String a)
    {
         FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", a));
    }
    public boolean exportIsEmpty()
    { int i=0;
        if(selectedQms!=null)
        {
            for(Qm qm:selectedQms)
         {
            i++;
            
         }
        }
        if(i!=0)
            return true;
        else
            return false;
    }
  

    public List<Qm> getList() {
        return list;
    }
     public List<Qm> getSelectedQms() {
        return selectedQms;
    }

    public void setSelectedQms(List<Qm> selectedQms) {
        for(Qm qm:selectedQms)
         
        this.selectedQms = selectedQms;
    }

    public void setList(List<Qm> list) {
        this.list = list;
    }
    
 
    public LazyDataModel<Qm> getLazyModel() {
        return lazyModel;
    }
 
    public Qm getSelectedQm() {
        return selectedQm;
    }
 
    public void setSelectedQm(Qm selectedQm) {
        this.selectedQm = selectedQm;
    }
     
    public void setService(QmService service) {
        this.service = service;
    }
     public void add(Qm q)
     {
        list.add(new Qm(q.getId(),q.getDescription(),q.getIsActive(),"new"));
         lazyModel = new LazyQmDataModel(list);
    
     }
     public int getId(int a)
     {
         int max=0;
         for(Qm qm:list)
         {
             if(qm.getId()>max)
                 max=qm.getId();
         }
         return max+1;
     }
     public void commitQm(String a)
     {
          FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_INFO,"Please Wait",""));
         if(a.equals("new"))
        service.commitQm(list);
         else
             service.commitQmUpd(list);
          
        context.addMessage("null", new FacesMessage("Successful",  "Changes have been comited"));
        init();
     }
     public void CheckId(Qm q)
     {
         Boolean Stat=true;
         for(Qm qm:list)
         {
             if(qm.getId()==q.getId())
                 Stat=false;   
            
         }
         if(Stat&&(!q.getDescription().equals("")))
             add(q);
         else if(!Stat)
         {
             RequestContext.getCurrentInstance().update("growl");
        FacesContext context=FacesContext.getCurrentInstance();
        
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Incident Id Exists!!"));
         }
         else{
             RequestContext.getCurrentInstance().update("growl");
        FacesContext context=FacesContext.getCurrentInstance();
        
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Incident Name required!!"));
             
             
         
     }
     }
     public boolean newRec(String a)
     {
        
         for(Qm qm:list)
         {
             if(qm.getStatus().equals(a))
                 return false;
         }
         return true;
     }
    public void onRowSelect(SelectEvent event) {
      
    }
     
    public void onRowEdit(RowEditEvent event) {
        
        for(Qm qm:list)
         {
             if(qm.getId()==((Qm)event.getObject()).getId())
             {
                 qm.setDescription(((Qm)event.getObject()).getDescription());
                 qm.setIsActive(((Qm)event.getObject()).getIsActive());
                 qm.setStatus("update");
             }
                   
            
         }
    }
        public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
     
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images"  + File.separator + "pdf.png";
         
        pdf.add(Image.getInstance(logo));
    }
     
    public void onRowCancel(RowEditEvent event) {
      
    }
}