package org.primefaces.showcase.view.data.datatable;
 
import com.entity.IraIncidentMaster;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import com.domain.Im;
import org.primefaces.showcase.service.CarService;
 
@ManagedBean(name="dtLazyView")
@ViewScoped
public class LazyView implements Serializable {
     
    private LazyDataModel<Im> lazyModel;
     
    private Im selectedCar;
     
    @ManagedProperty("#{carService}")
    private CarService service;
     
    @PostConstruct
    public void init() {
    }
 
    public LazyDataModel<Im> getLazyModel() {
        return lazyModel;
    }
 
    public Im getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Im selectedCar) {
        this.selectedCar = selectedCar;
    }
     
    public void setService(CarService service) {
        this.service = service;
    }
     
    public void onRowSelect(SelectEvent event) {
      
    }
}