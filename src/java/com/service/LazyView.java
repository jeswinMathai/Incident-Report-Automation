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
import com.domain.Im;
 
@ManagedBean(name="LazyView")
@ViewScoped
public class LazyView implements Serializable {
     
    private LazyDataModel<Im> lazyModel;
     
    private Im selectedIm;
    private int emt;

   
    private List<Im> selectedIms;
    private  List<Im> list;

    public void clear(int a)
    {
        int i=0,j=0;
        if(a==2)
            emt=0;
        if(selectedIms!=null)
        {
            for(Im im:selectedIms)
         {
            i++;
            
         }
        }
       
        if(i==1)
        {
            if(emt==1)
            {
            selectedIms=null;
            emt=0;
            j=1;
            }
        }
        if(j!=1)
        emt=i;
        System.out.println("hjsdfsdfdsfds");
        System.out.println(emt);
      
            
    }
    
    @ManagedProperty("#{imService}")
    private ImService service;
     
    @PostConstruct
    public void init() {
        list=service.createIms();
 
        emt=0;
        lazyModel = new LazyImDataModel(list);
    }
    public void message(String a)
    {
         FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", a));
    }
    public boolean exportIsEmpty()
    { int i=0;
        if(selectedIms!=null)
        {
            for(Im im:selectedIms)
         {
            i++;
            
         }
        }
        if(i!=0)
            return true;
        else
            return false;
    }
  

    public List<Im> getList() {
        return list;
    }
     public List<Im> getSelectedIms() {
        return selectedIms;
    }

    public void setSelectedIms(List<Im> selectedIms) {
        for(Im im:selectedIms)
         
        this.selectedIms = selectedIms;
    }

    public void setList(List<Im> list) {
        this.list = list;
    }
    
 
    public LazyDataModel<Im> getLazyModel() {
        return lazyModel;
    }
 
    public Im getSelectedIm() {
        return selectedIm;
    }
 
    public void setSelectedIm(Im selectedIm) {
        this.selectedIm = selectedIm;
    }
     
    public void setService(ImService service) {
        this.service = service;
    }
     public void add(Im i)
     {
        list.add(new Im(i.getId(),i.getName(),i.getIsActive(),"new"));
         lazyModel = new LazyImDataModel(list);
    
     }
     public int getId(int a)
     {
         int max=0;
         for(Im im:list)
         {
             if(im.getId()>max)
                 max=im.getId();
         }
         return max+1;
     }
     public void commitIm(String a)
     {
         if(a.equals("new"))
        service.commitIm(list);
         else
             service.commitImUpd(list);
           FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage("null", new FacesMessage("Successful",  "Changes have been comited"));
        init();
     }
     public void CheckId(Im i)
     {
         Boolean Stat=true;
         for(Im im:list)
         {
             if(im.getId()==i.getId())
                 Stat=false;   
            
         }
         if(Stat&&(!i.getName().equals("")))
             add(i);
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
        
         for(Im im:list)
         {
             if(im.getStatus().equals(a))
                 return false;
         }
         return true;
     }
    public void onRowSelect(SelectEvent event) {
      
    }
     
    public void onRowEdit(RowEditEvent event) {
        
        for(Im im:list)
         {
             if(im.getId()==((Im)event.getObject()).getId())
             {
                 im.setName(((Im)event.getObject()).getName());
                 im.setIsActive(((Im)event.getObject()).getIsActive());
                 im.setStatus("update");
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