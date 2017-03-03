package com.service;
 
import java.util.Comparator;
import org.primefaces.model.SortOrder;
import com.domain.Rm;

 
public class LazySorterRm implements Comparator<Rm> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorterRm(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Rm rm1, Rm rm2) {
        try {
            Object value1 = Rm.class.getField(this.sortField).get(rm1);
            Object value2 = Rm.class.getField(this.sortField).get(rm2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}