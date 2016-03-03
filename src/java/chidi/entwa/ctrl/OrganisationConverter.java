/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.ent.Organisation;
import chidi.entwa.pers.OrganisationFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author chidi
 */
@FacesConverter("organisationConverter")
public class OrganisationConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        OrganisationBean organisationCtrl = (OrganisationBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "organisationBean");
        OrganisationFacade organisationFacade = organisationCtrl.getFacade();
        Long id = Long.decode(value);
        Organisation a = organisationFacade.find(id);
        return a;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Organisation) {
            //throw new Error("object is not of type Organisation");
            return value.toString();
        } else {
            throw new Error("object is not of type Organisation1");
        }
    }
    
}
