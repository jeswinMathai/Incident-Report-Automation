package com.service;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.domain.Qm;
 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyQmDataModel extends LazyDataModel<Qm> {
     
    private List<Qm> datasource;
     
    public LazyQmDataModel(List<Qm> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Qm getRowData(String rowKey) {
        for(Qm qm: datasource) {
            if(qm.getId()==Integer.parseInt(rowKey))
                return qm;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Qm qm) {
        return qm.getId();
    }
 
    @Override
    public List<Qm> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Qm> data = new ArrayList<Qm>();
 
        //filter
        for(Qm qm : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(qm.getClass().getField(filterProperty).get(qm));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(qm);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorterQm(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}