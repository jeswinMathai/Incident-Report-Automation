package com.service;
 
import java.util.Comparator;
import org.primefaces.model.SortOrder;
import com.domain.Im;

 
public class LazySorter implements Comparator<Im> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Im im1, Im im2) {
        try {
            Object value1 = Im.class.getField(this.sortField).get(im1);
            Object value2 = Im.class.getField(this.sortField).get(im2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}