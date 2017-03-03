package com.service;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.domain.Rm;
 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyRmDataModel extends LazyDataModel<Rm> {
     
    private List<Rm> datasource;
     
    public LazyRmDataModel(List<Rm> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Rm getRowData(String rowKey) {
        for(Rm rm: datasource) {
            if(rm.getId()==Integer.parseInt(rowKey))
                return rm;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Rm rm) {
        return rm.getId();
    }
 
    @Override
    public List<Rm> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Rm> data = new ArrayList<Rm>();
 
        //filter
        for(Rm rm : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(rm.getClass().getField(filterProperty).get(rm));
 
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
                data.add(rm);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorterRm(sortField, sortOrder));
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