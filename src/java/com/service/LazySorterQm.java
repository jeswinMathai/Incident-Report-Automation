package com.service;
 
import java.util.Comparator;
import org.primefaces.model.SortOrder;
import com.domain.Qm;

 
public class LazySorterQm implements Comparator<Qm> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorterQm(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Qm qm1, Qm qm2) {
        try {
            Object value1 = Qm.class.getField(this.sortField).get(qm1);
            Object value2 = Qm.class.getField(this.sortField).get(qm2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}