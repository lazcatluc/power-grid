package ro.powergrid.faces;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by adi on 6/21/14.
 */
public class FacesWrapper implements IFaces {
    public String getValue(String name){
        FacesContext fc = FacesContext.getCurrentInstance();

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String valueAsString = params.get("availableResources");
        return valueAsString;
    }
}
