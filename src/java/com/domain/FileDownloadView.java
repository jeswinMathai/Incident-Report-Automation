/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;


 
import com.entity.IraDocumentsAttached;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean; 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class FileDownloadView {
     
    private StreamedContent file;
     
 
 
    public StreamedContent getFile(IraDocumentsAttached ida) {
        InputStream stream = new ByteArrayInputStream((byte[])ida.getDocument());
        String a[]=ida.getExt().split(";");
       
        file = new DefaultStreamedContent(stream, a[0], ida.getDocFileName().replace(' ', '_')+a[1]);
   
        return file;
    }
}