/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DIVYA
 */
@ManagedBean(name="uploadFile")
@ApplicationScoped
public class UploadFile implements Serializable{
    private byte[] files;
    private String ext;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }
  
    
    public void fileUpload(FileUploadEvent event) throws IOException {
        System.out.println(event);

    UploadedFile file = event.getFile();

String a[]=file.getFileName().split("\\.");

//ext=exts[exts.length-1];
    
    ext=file.getContentType()+";."+a[a.length-1];
    
    

    byte[] foto = IOUtils.toByteArray(file.getInputstream());
    files=foto;
    System.out.println("hallllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllo");
 //application code
}

}
