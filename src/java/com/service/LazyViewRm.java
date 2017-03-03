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
import com.domain.Rm;
 
@ManagedBean(name="LazyViewRm")
@ViewScoped
public class LazyViewRm implements Serializable {
     
    private LazyDataModel<Rm> lazyModel;
     
    private Rm selectedRm;
    private int emt;

   
    private List<Rm> selectedRms;
    private  List<Rm> list;

    public void clear(int a)
    {
        int i=0,j=0;
        if(a==2)
            emt=0;
        if(selectedRms!=null)
        {
            for(Rm rm:selectedRms)
         {
            i++;
            
         }
        }
       
        if(i==1)
        {
            if(emt==1)
            {
            selectedRms=null;
            emt=0;
            j=1;
            }
        }
        if(j!=1)
        emt=i;
        System.out.println("hjsdfsdfdsfds");
        System.out.println(emt);
      
            
    }
    
    @ManagedProperty("#{rmService}")
    private RmService service;
     
    @PostConstruct
    public void init() {
        list=service.createRms();
 
        emt=0;
        lazyModel = new LazyRmDataModel(list);
    }
    public void message(String a)
    {
         FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", a));
    }
    public boolean exportIsEmpty()
    { int i=0;
        if(selectedRms!=null)
        {
            for(Rm rm:selectedRms)
         {
            i++;
            
         }
        }
        if(i!=0)
            return true;
        else
            return false;
    }
  

    public List<Rm> getList() {
        return list;
    }
     public List<Rm> getSelectedRms() {
        return selectedRms;
    }

    public void setSelectedRms(List<Rm> selectedRms) {
        for(Rm rm:selectedRms)
         
        this.selectedRms = selectedRms;
    }

    public void setList(List<Rm> list) {
        this.list = list;
    }
    
 
    public LazyDataModel<Rm> getLazyModel() {
        return lazyModel;
    }
 
    public Rm getSelectedRm() {
        return selectedRm;
    }
 
    public void setSelectedRm(Rm selectedRm) {
        this.selectedRm = selectedRm;
    }
     
    public void setService(RmService service) {
        this.service = service;
    }
     public void add(Rm r)
     {
        list.add(new Rm(r.getId(),r.getName(),r.getWeight(),r.getIsReportCreationAllowed(),r.getIsActive(),"new"));
         lazyModel = new LazyRmDataModel(list);
    
     }
     public int getId(int a)
     {
         int max=0;
         for(Rm rm:list)
         {
             if(rm.getId()>max)
                 max=rm.getId();
         }
         return max+1;
     }
     public void commitRm(String a)
     {
          FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_INFO,"Please Wait",""));
         if(a.equals("new"))
        service.commitRm(list);
         else
             service.commitRmUpd(list);
          
        context.addMessage("null", new FacesMessage("Successful",  "Changes have been comited"));
        init();
     }
     public void CheckId(Rm r)
     {
         Boolean Stat=true;
         for(Rm rm:list)
         {
             if(rm.getId()==r.getId())
                 Stat=false;   
            
         }
         if(Stat&&(!r.getName().equals("")&&r.getWeight()!=0))
             add(r);
         else if(!Stat)
         {
             RequestContext.getCurrentInstance().update("growl");
        FacesContext context=FacesContext.getCurrentInstance();
        
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Role Id Exists!!"));
         }
         else if(r.getName().equals("")){
             RequestContext.getCurrentInstance().update("growl");
        FacesContext context=FacesContext.getCurrentInstance();
        
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Role Name required!!"));
     }
         else
         {
              RequestContext.getCurrentInstance().update("growl");
              FacesContext context=FacesContext.getCurrentInstance();
        
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Role Weight required!!"));
         }
     }
     public boolean newRec(String a)
     {
        
         for(Rm rm:list)
         {
             if(rm.getStatus().equals(a))
                 return false;
         }
         return true;
     }
    public void onRowSelect(SelectEvent event) {
      
    }
     
    public void onRowEdit(RowEditEvent event) {
        
        for(Rm rm:list)
         {
             if(rm.getId()==((Rm)event.getObject()).getId())
             {
                 rm.setName(((Rm)event.getObject()).getName());
                 rm.setIsActive(((Rm)event.getObject()).getIsActive());
                 rm.setWeight(((Rm)event.getObject()).getWeight());
                 rm.setIsReportCreationAllowed(((Rm)event.getObject()).getIsReportCreationAllowed());
                 
                 rm.setStatus("update");
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